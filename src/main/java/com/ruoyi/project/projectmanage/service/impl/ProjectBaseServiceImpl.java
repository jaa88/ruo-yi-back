package com.ruoyi.project.projectmanage.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.project.projectmanage.domain.*;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseAndUserRelationParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuNodeTargetUserRelationParam;
import com.ruoyi.project.projectmanage.mapper.ProjectBaseMapper;
import com.ruoyi.project.projectmanage.service.*;
import com.ruoyi.project.system.domain.SysConfig;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.mapper.SysConfigMapper;
import com.ruoyi.project.system.service.ISysConfigService;
import com.ruoyi.project.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class ProjectBaseServiceImpl implements IProjectBaseService {

    @Resource
    private ProjectBaseMapper projectBaseMapper;
    @Resource
    private IProjectLiuChengTuService projectLiuChengTuService;
    @Resource
    private IProjectBaseAndUserRelationService projectBaseAndUserRelationService;
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private IProjectLiuChengTuNodeService projectLiuChengTuNodeService;
    @Resource
    private IProjectLiuChengTuNodeTargetUserRelationService projectLiuChengTuNodeTargetUserRelationService;

    @Override
    public int selectProjectBaseCount(QueryProjectBaseParam param) {
        return projectBaseMapper.selectProjectBaseCount(param);
    }

    @Override
    public List<ProjectBase> selectProjectBaseList(QueryProjectBaseParam param) {
        List<ProjectBase> list= projectBaseMapper.selectProjectBaseList(param);
        //补充流程图数据
        List<Long> currentLiuChengTuDataIdList=new ArrayList<>();
        for(ProjectBase projectBase:list){
            if(projectBase.getCurrentLiuChengTuDataLogId()!=null){
                currentLiuChengTuDataIdList.add(projectBase.getCurrentLiuChengTuDataLogId());
            }
        }
        if(currentLiuChengTuDataIdList.size()>0){
            Map<Long, ProjectLiuChengTuDataLog> idTargetProjectLiuChengTuDataLogMap=projectLiuChengTuService.selectIdTargetProjectLiuChengTuDataLogMap(currentLiuChengTuDataIdList);
            for(ProjectBase projectBase:list){
                if(idTargetProjectLiuChengTuDataLogMap.get(projectBase.getCurrentLiuChengTuDataLogId())!=null){
                    projectBase.setCellsJsonStr(idTargetProjectLiuChengTuDataLogMap.get(projectBase.getCurrentLiuChengTuDataLogId()).getCurrentCellsJsonStr());
                }
            }
        }
        //补充流程图与用户之间的关系
        Map<Long,SysUser> sysUserMap=sysUserService.selectAllUserMap();
        List<Long> projectIdList=new ArrayList<>();
        for(ProjectBase projectBase:list){
            projectIdList.add(projectBase.getId());
        }
        QueryProjectBaseAndUserRelationParam queryProjectBaseAndUserRelationParam=new QueryProjectBaseAndUserRelationParam();
        queryProjectBaseAndUserRelationParam.setProjectBaseIdList(projectIdList);
        List<ProjectBaseAndUserRelation> projectBaseAndUserRelationList=projectBaseAndUserRelationService.selectProjectBaseAndUserRelationList(queryProjectBaseAndUserRelationParam);
        if(projectBaseAndUserRelationList.size()>0 && sysUserMap.size()>0){
            Map<Long,List<SysUser>> projectBaseIdTargetUserListMap=new HashMap<>();
            Map<Long,List<Long>> projectBaseIdTargetUserIdListMap=new HashMap<>();
            for(ProjectBaseAndUserRelation relation:projectBaseAndUserRelationList){
                List<SysUser> userList=projectBaseIdTargetUserListMap.get(relation.getProjectBaseId());
                List<Long> userIdList=projectBaseIdTargetUserIdListMap.get(relation.getProjectBaseId());
                if(userList==null){
                    userList=new ArrayList<>();
                    userIdList=new ArrayList<>();
                }
                if(sysUserMap.get(relation.getUserId())!=null){
                    userList.add(sysUserMap.get(relation.getUserId()));
                    userIdList.add(relation.getUserId());
                }
                projectBaseIdTargetUserListMap.put(relation.getProjectBaseId(),userList);
                projectBaseIdTargetUserIdListMap.put(relation.getProjectBaseId(),userIdList);
            }
            //补充
            for(ProjectBase projectBase:list){
                Long projectBaseId=projectBase.getId();
                projectBase.setCanEditProjectUserList(projectBaseIdTargetUserListMap.get(projectBaseId));
                projectBase.setCanEditProjectUserIdList(projectBaseIdTargetUserIdListMap.get(projectBaseId));
            }
        }
        return list;
    }

    @Override
    @Transactional
    public void updateProjectBase(ProjectBase projectBase,boolean onlyUpdateLiuChengTuDataLogFlag) {
        if(!onlyUpdateLiuChengTuDataLogFlag){
            //目前的userIdList
            List<Long> curCanEditProjectUserIdList=projectBase.getCanEditProjectUserIdList();
            //获取数据库中的canEditProjectUserIdList
            Set<Long> dbCanEditProjectUserIdSet=getDbCanEditProjectUserIdSet(projectBase.getId());
            //需要新增的权限
            List<ProjectBaseAndUserRelation> newRelationList=getNewProjectBaseAndUserRelationList(curCanEditProjectUserIdList,dbCanEditProjectUserIdSet,projectBase.getUpdateUserId(),projectBase.getId());
            //需要删除的权限
            List<Long> needDeleteRelationIdLsit=getNeedDeleteRelationIdList(curCanEditProjectUserIdList,projectBase.getId());
            //执行新增操作
            if(newRelationList.size()>0){
                projectBaseAndUserRelationService.insertProjectBaseAndUserRelationList(newRelationList);
            }
            //执行删除操作
            if(needDeleteRelationIdLsit.size()>0){
                projectBaseAndUserRelationService.deleteProjectBaseAndUserRelationByIdList(needDeleteRelationIdLsit);
            }
        }else{
            //只是动了流程图的话，相关节点的关系进行删除、新增
            insertProjectLiuChengTuNodeListByProjectBase(projectBase,projectBase.getUpdateUserId());
        }
        projectBaseMapper.updateProjectBase(projectBase);
    }

    @Override
    @Transactional
    public void insertProjectBase(ProjectBase projectBase) {
        projectBaseMapper.insertProjectBase(projectBase);
        //新增成功后，会有id返回过来
        List<Long> userIdList=projectBase.getCanEditProjectUserIdList();
        if(userIdList!=null && userIdList.size()>0){
            List<ProjectBaseAndUserRelation> relationList=new ArrayList<>();
            for(Long userId:userIdList){
                ProjectBaseAndUserRelation relation=new ProjectBaseAndUserRelation();
                relation.setProjectBaseId(projectBase.getId());
                relation.setUserId(userId);
                relation.setCreateUserId(projectBase.getUpdateUserId());
                relation.setUpdateUserId(projectBase.getUpdateUserId());
                relationList.add(relation);
            }
            projectBaseAndUserRelationService.insertProjectBaseAndUserRelationList(relationList);
        }
    }

    @Override
    @Transactional
    public void deleteProjectBase(ProjectBase projectBase) {
        projectBaseMapper.deleteProjectBase(projectBase);
        //删除所有的节点
        projectLiuChengTuNodeService.deleteProjectLiuChengTuNodeByProjectBaseId(projectBase.getId());
        //删除所有的节点关系人
        projectLiuChengTuNodeTargetUserRelationService.deleteProjectLiuChengTuNodeTargetUserRelationListByProjectBaseId(projectBase.getId());
    }

    //获取某项目中已存在的项目-人员关系
    private Set<Long> getDbCanEditProjectUserIdSet(Long projectBaseId){
        QueryProjectBaseAndUserRelationParam queryProjectBaseAndUserRelationParam=new QueryProjectBaseAndUserRelationParam();
        queryProjectBaseAndUserRelationParam.setProjectBaseIdList(Arrays.asList(projectBaseId));
        List<ProjectBaseAndUserRelation> relationList=projectBaseAndUserRelationService.selectProjectBaseAndUserRelationList(queryProjectBaseAndUserRelationParam);
        List<Long> dbCanEditProjectUserIdList=new ArrayList<>();
        for(ProjectBaseAndUserRelation relation:relationList){
            dbCanEditProjectUserIdList.add(relation.getUserId());
        }
        return new HashSet<>(dbCanEditProjectUserIdList);
    }

    //获取要删除的权限
    private List<Long> getNeedDeleteRelationIdList(List<Long> curCanEditProjectUserIdList,Long projectBaseId){
        QueryProjectBaseAndUserRelationParam queryProjectBaseAndUserRelationParam=new QueryProjectBaseAndUserRelationParam();
        queryProjectBaseAndUserRelationParam.setProjectBaseIdList(Arrays.asList(projectBaseId));
        List<ProjectBaseAndUserRelation> relationList=projectBaseAndUserRelationService.selectProjectBaseAndUserRelationList(queryProjectBaseAndUserRelationParam);
        List<Long> needDeleteRelationIdLsit=new ArrayList<>();
        for(ProjectBaseAndUserRelation relation:relationList){
            if(!curCanEditProjectUserIdList.contains(relation.getUserId())){
                needDeleteRelationIdLsit.add(relation.getId());
            }
        }
        return needDeleteRelationIdLsit;
    }

    //获取新增的权限
    private List<ProjectBaseAndUserRelation> getNewProjectBaseAndUserRelationList(List<Long> curCanEditProjectUserIdList,Set<Long> dbCanEditProjectUserIdSet,Long curUserId,Long projectBaseId){
        List<ProjectBaseAndUserRelation> newProjectBaseAndUserRelationList=new ArrayList<>();
        for(Long userId:curCanEditProjectUserIdList){
            if(!dbCanEditProjectUserIdSet.contains(userId)){
                ProjectBaseAndUserRelation relation=new ProjectBaseAndUserRelation();
                relation.setUpdateUserId(curUserId);
                relation.setCreateUserId(curUserId);
                relation.setProjectBaseId(projectBaseId);
                relation.setUserId(userId);
                newProjectBaseAndUserRelationList.add(relation);
            }
        }
        return newProjectBaseAndUserRelationList;
    }

    //新增节点
    private void insertProjectLiuChengTuNodeListByProjectBase(ProjectBase projectBase,Long operateUserId){
        //插入之前，先进行删除操作
        //删除所有的节点
        projectLiuChengTuNodeService.deleteProjectLiuChengTuNodeByProjectBaseId(projectBase.getId());
        //删除所有的节点关系人
        projectLiuChengTuNodeTargetUserRelationService.deleteProjectLiuChengTuNodeTargetUserRelationListByProjectBaseId(projectBase.getId());

        Long projectBaseId=projectBase.getId();
        String cellsJsonStr=projectBase.getCellsJsonStr();
        List<ProjectLiuChengTuNode> nodeList=new ArrayList<>();
        List<ProjectLiuChengTuNodeTargetUserRelation> relationList=new ArrayList<>();

        //10没什么意义，只是区分json字符串
        if(cellsJsonStr!=null && cellsJsonStr.length()>10){
            JSONArray jsonObjectList = JSON.parseArray(cellsJsonStr);
            for (int i = 0; i < jsonObjectList.size(); i++) {
                JSONObject jsonObject=jsonObjectList.getJSONObject(i);
                String shape=jsonObject.getString("shape");
                //是Node类型，则加入节点啥的
                if(shape!=null && shape.lastIndexOf("Node")>-1){
                    String graphNodeId=jsonObject.getString("id");
                    JSONObject dataJSONObject=jsonObject.getJSONObject("data");
                    String dataJsonStr=dataJSONObject.toString();
                    ProjectLiuChengTuNode node=new ProjectLiuChengTuNode();
                    node.setProjectBaseId(projectBaseId);
                    node.setDataJsonStr(dataJsonStr);
                    node.setGraphNodeId(graphNodeId);
                    node.setUpdateUserId(operateUserId);
                    node.setStatus(dataJSONObject.getInteger("status"));
                    nodeList.add(node);
                }
            }
            //执行新增操作
            projectLiuChengTuNodeService.insertProjectLiuChengTuNodeList(nodeList);
            //再执行具体的关系人
            for(ProjectLiuChengTuNode node:nodeList){
                JSONObject dataJsonObject=JSONObject.parseObject(node.getDataJsonStr());
                if(dataJsonObject!=null  && dataJsonObject.containsKey("chargeUserIdList")){
                    List<Long> chargeUserIdList=dataJsonObject.getJSONArray("chargeUserIdList").toJavaList(Long.class);
                    if(chargeUserIdList!=null && chargeUserIdList.size()>0){
                        for(Long chargeUserId:chargeUserIdList){
                            ProjectLiuChengTuNodeTargetUserRelation relation=new ProjectLiuChengTuNodeTargetUserRelation();
                            relation.setProjectBaseId(projectBaseId);
                            relation.setNodeId(node.getId());
                            relation.setChargeUserId(chargeUserId);
                            relation.setUpdateUserId(operateUserId);
                            relationList.add(relation);
                        }
                    }
                }
            }
            if(relationList.size()>0){
                projectLiuChengTuNodeTargetUserRelationService.insertProjectLiuChengTuNodeTargetUserRelationList(relationList);
            }
        }
    }

    //补充项目流程图节点关系
    private void supplyProjectLiuChengTuTargetUserRelation(List<ProjectBase> projectBaseList){
        List<Long> projectBaseIdList=new ArrayList<>();
        for(ProjectBase projectBase:projectBaseList){
            projectBaseIdList.add(projectBase.getId());
        }
        if(projectBaseIdList.size()>0){
            QueryProjectLiuChengTuNodeTargetUserRelationParam param=new QueryProjectLiuChengTuNodeTargetUserRelationParam();
            param.setProjectBaseIdList(projectBaseIdList);
            List<ProjectLiuChengTuNodeTargetUserRelation> relationList=projectLiuChengTuNodeTargetUserRelationService.selectProjectLiuChengTuNodeTargetUserRelationList(param);
            Map<Long,List<ProjectLiuChengTuNodeTargetUserRelation>> projectBaseIdTargetRelationListMap=new HashMap<>();
            for(ProjectLiuChengTuNodeTargetUserRelation relation:relationList){
                List<ProjectLiuChengTuNodeTargetUserRelation> targetRelationList=projectBaseIdTargetRelationListMap.get(relation.getProjectBaseId());
                if(targetRelationList==null){
                    targetRelationList=new ArrayList<>();
                }
                targetRelationList.add(relation);
                projectBaseIdTargetRelationListMap.put(relation.getProjectBaseId(), targetRelationList);
            }
            //便利项目
            for(ProjectBase projectBase:projectBaseList){
                Long curProjectBaseId=projectBase.getId();
                projectBase.setRelationList(projectBaseIdTargetRelationListMap.get(curProjectBaseId));
            }
        }
    }
}
