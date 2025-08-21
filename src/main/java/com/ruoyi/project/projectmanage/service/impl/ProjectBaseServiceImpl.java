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
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseAndDeptRelationParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuNodeTargetDeptRelationParam;
import com.ruoyi.project.projectmanage.mapper.ProjectBaseMapper;
import com.ruoyi.project.projectmanage.service.*;
import com.ruoyi.project.system.domain.SysConfig;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.mapper.SysConfigMapper;
import com.ruoyi.project.system.service.ISysConfigService;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.tool.gen.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.text.ParseException;
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
    private IProjectBaseAndDeptRelationService projectBaseAndDeptRelationService;
    @Resource
    private ISysDeptService sysUserService;
    @Resource
    private IProjectLiuChengTuNodeService projectLiuChengTuNodeService;
    @Resource
    private IProjectLiuChengTuNodeTargetDeptRelationService projectLiuChengTuNodeTargetDeptRelationService;

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
        Map<Long,SysDept> sysDeptMap=sysUserService.selectAllDeptMap();
        List<Long> projectIdList=new ArrayList<>();
        for(ProjectBase projectBase:list){
            projectIdList.add(projectBase.getId());
        }
        QueryProjectBaseAndDeptRelationParam queryProjectBaseAndDeptRelationParam=new QueryProjectBaseAndDeptRelationParam();
        queryProjectBaseAndDeptRelationParam.setProjectBaseIdList(projectIdList);
        List<ProjectBaseAndDeptRelation> ProjectBaseAndDeptRelationList=projectBaseAndDeptRelationService.selectProjectBaseAndDeptRelationList(queryProjectBaseAndDeptRelationParam);
        if(ProjectBaseAndDeptRelationList.size()>0 && sysDeptMap.size()>0){
            Map<Long,List<SysDept>> projectBaseIdTargetDeptListMap=new HashMap<>();
            Map<Long,List<Long>> projectBaseIdTargetDeptIdListMap=new HashMap<>();
            for(ProjectBaseAndDeptRelation relation:ProjectBaseAndDeptRelationList){
                List<SysDept> deptList=projectBaseIdTargetDeptListMap.get(relation.getProjectBaseId());
                List<Long> deptIdList=projectBaseIdTargetDeptIdListMap.get(relation.getProjectBaseId());
                if(deptList==null){
                    deptList=new ArrayList<>();
                    deptIdList=new ArrayList<>();
                }
                if(sysDeptMap.get(relation.getDeptId())!=null){
                    deptList.add(sysDeptMap.get(relation.getDeptId()));
                    deptIdList.add(relation.getDeptId());
                }
                projectBaseIdTargetDeptListMap.put(relation.getProjectBaseId(),deptList);
                projectBaseIdTargetDeptIdListMap.put(relation.getProjectBaseId(),deptIdList);
            }
            //补充
            for(ProjectBase projectBase:list){
                Long projectBaseId=projectBase.getId();
                projectBase.setCanEditProjectDeptList(projectBaseIdTargetDeptListMap.get(projectBaseId));
                projectBase.setCanEditProjectDeptIdList(projectBaseIdTargetDeptIdListMap.get(projectBaseId));
            }
        }
        //补充流程图中的节点与部门之间的关系，目前没有用到 0816
        supplyProjectLiuChengTuTargetDeptRelation(list);
        return list;
    }

    @Override
    @Transactional
    public void updateProjectBase(ProjectBase projectBase,boolean onlyUpdateLiuChengTuDataLogFlag) {
        if(onlyUpdateLiuChengTuDataLogFlag){
            //只是动了流程图的话，相关节点的关系进行删除、新增
            insertProjectLiuChengTuNodeListByProjectBase(projectBase,projectBase.getUpdateUserId());
        }else{
            //目前的deptIdList
            List<Long> curCanEditProjectDeptIdList=projectBase.getCanEditProjectDeptIdList();
            //获取数据库中的canEditProjectDeptIdList
            Set<Long> dbCanEditProjectUserIdSet=getDbCanEditProjectDeptIdSet(projectBase.getId());
            //需要新增的权限
            List<ProjectBaseAndDeptRelation> newRelationList=getNewProjectBaseAndDeptRelationList(curCanEditProjectDeptIdList,dbCanEditProjectUserIdSet,projectBase.getUpdateUserId(),projectBase.getId());
            //需要删除的权限
            List<Long> needDeleteRelationIdLsit=getNeedDeleteRelationIdList(curCanEditProjectDeptIdList,projectBase.getId());
            //执行新增操作
            if(newRelationList.size()>0){
                projectBaseAndDeptRelationService.insertProjectBaseAndDeptRelationList(newRelationList);
            }
            //执行删除操作
            if(needDeleteRelationIdLsit.size()>0){
                projectBaseAndDeptRelationService.deleteProjectBaseAndDeptRelationByIdList(needDeleteRelationIdLsit);
            }
        }
        projectBaseMapper.updateProjectBase(projectBase);
    }

    @Override
    @Transactional
    public void insertProjectBase(ProjectBase projectBase) {
        projectBaseMapper.insertProjectBase(projectBase);
        //新增成功后，会有id返回过来
        List<Long> deptIdList=projectBase.getCanEditProjectDeptIdList();
        if(deptIdList!=null && deptIdList.size()>0){
            List<ProjectBaseAndDeptRelation> relationList=new ArrayList<>();
            for(Long deptId:deptIdList){
                ProjectBaseAndDeptRelation relation=new ProjectBaseAndDeptRelation();
                relation.setProjectBaseId(projectBase.getId());
                relation.setDeptId(deptId);
                relation.setCreateUserId(projectBase.getUpdateUserId());
                relation.setUpdateUserId(projectBase.getUpdateUserId());
                relationList.add(relation);
            }
            projectBaseAndDeptRelationService.insertProjectBaseAndDeptRelationList(relationList);
        }
    }

    @Override
    @Transactional
    public void deleteProjectBase(ProjectBase projectBase) {
        projectBaseMapper.deleteProjectBase(projectBase);
        //删除所有的节点
        projectLiuChengTuNodeService.deleteProjectLiuChengTuNodeByProjectBaseId(projectBase.getId());
        //删除所有的节点关系部门
        projectLiuChengTuNodeTargetDeptRelationService.deleteProjectLiuChengTuNodeTargetDeptRelationListByProjectBaseId(projectBase.getId());
    }

    //获取某项目中已存在的项目-部门关系
    private Set<Long> getDbCanEditProjectDeptIdSet(Long projectBaseId){
        QueryProjectBaseAndDeptRelationParam queryProjectBaseAndDeptRelationParam=new QueryProjectBaseAndDeptRelationParam();
        queryProjectBaseAndDeptRelationParam.setProjectBaseIdList(Arrays.asList(projectBaseId));
        List<ProjectBaseAndDeptRelation> relationList=projectBaseAndDeptRelationService.selectProjectBaseAndDeptRelationList(queryProjectBaseAndDeptRelationParam);
        List<Long> dbCanEditProjectDeptIdList=new ArrayList<>();
        for(ProjectBaseAndDeptRelation relation:relationList){
            dbCanEditProjectDeptIdList.add(relation.getDeptId());
        }
        return new HashSet<>(dbCanEditProjectDeptIdList);
    }

    //获取要删除的权限
    private List<Long> getNeedDeleteRelationIdList(List<Long> curCanEditProjectDeptIdList,Long projectBaseId){
        QueryProjectBaseAndDeptRelationParam queryProjectBaseAndDeptRelationParam=new QueryProjectBaseAndDeptRelationParam();
        queryProjectBaseAndDeptRelationParam.setProjectBaseIdList(Arrays.asList(projectBaseId));
        List<ProjectBaseAndDeptRelation> relationList=projectBaseAndDeptRelationService.selectProjectBaseAndDeptRelationList(queryProjectBaseAndDeptRelationParam);
        List<Long> needDeleteRelationIdLsit=new ArrayList<>();
        for(ProjectBaseAndDeptRelation relation:relationList){
            if(!curCanEditProjectDeptIdList.contains(relation.getDeptId())){
                needDeleteRelationIdLsit.add(relation.getId());
            }
        }
        return needDeleteRelationIdLsit;
    }

    //获取新增的权限
    private List<ProjectBaseAndDeptRelation> getNewProjectBaseAndDeptRelationList(List<Long> curCanEditProjectDeptIdList,Set<Long> dbCanEditProjectUserIdSet,Long curUserId,Long projectBaseId){
        List<ProjectBaseAndDeptRelation> newProjectBaseAndDeptRelationList=new ArrayList<>();
        if(curCanEditProjectDeptIdList!=null){
            for(Long deptId:curCanEditProjectDeptIdList){
                if(!dbCanEditProjectUserIdSet.contains(deptId)){
                    ProjectBaseAndDeptRelation relation=new ProjectBaseAndDeptRelation();
                    relation.setUpdateUserId(curUserId);
                    relation.setCreateUserId(curUserId);
                    relation.setProjectBaseId(projectBaseId);
                    relation.setDeptId(deptId);
                    newProjectBaseAndDeptRelationList.add(relation);
                }
            }
        }
        return newProjectBaseAndDeptRelationList;
    }

    //新增节点
    private void insertProjectLiuChengTuNodeListByProjectBase(ProjectBase projectBase,Long operateUserId){
        //插入之前，先进行删除操作
        //删除所有的节点
        projectLiuChengTuNodeService.deleteProjectLiuChengTuNodeByProjectBaseId(projectBase.getId());
        //删除所有的节点与部门之间的关系
        projectLiuChengTuNodeTargetDeptRelationService.deleteProjectLiuChengTuNodeTargetDeptRelationListByProjectBaseId(projectBase.getId());
        Long projectBaseId=projectBase.getId();
        String cellsJsonStr=projectBase.getCellsJsonStr();
        //10没什么意义，只是区分json字符串
        if(cellsJsonStr!=null && cellsJsonStr.length()>10){
            //生成节点信息
            List<ProjectLiuChengTuNode> nodeList=generateProjectLiuChengTuNodeList(cellsJsonStr,projectBaseId,operateUserId);
            //执行新增操作
            if(nodeList.size()>0){
                projectLiuChengTuNodeService.insertProjectLiuChengTuNodeList(nodeList);
            }
            //再执行具体的关系人
            List<ProjectLiuChengTuNodeTargetDeptRelation> relationList=generateProjectLiuChengTuNodeTargetDeptRelationList(nodeList,projectBaseId,operateUserId);
            if(relationList.size()>0){
                projectLiuChengTuNodeTargetDeptRelationService.insertProjectLiuChengTuNodeTargetDeptRelationList(relationList);
            }
        }
    }

    //生成节点信息
    private List<ProjectLiuChengTuNode> generateProjectLiuChengTuNodeList( String cellsJsonStr,Long projectBaseId,Long operateUserId){
        List<ProjectLiuChengTuNode> nodeList=new ArrayList<>();
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
                //以下是从dataJsonStr中提取的东西
                node.setStatus(dataJSONObject.getInteger("status"));
                node.setTaskName(dataJSONObject.getString("taskName"));
                //转换开始时间
                String startTimeStr=dataJSONObject.getString("startTime");
                if(startTimeStr!=null && !"".equals(startTimeStr)){
                    try {
                        node.setStartTime(TimeUtil.geTimeFromStr(startTimeStr,"yyyy-MM-dd"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        node.setStartTime(null);
                    }
                }
                //转换预期结束时间
                String expectedEndTime=dataJSONObject.getString("expectedEndTime");
                if(expectedEndTime!=null && !"".equals(expectedEndTime)){
                    try {
                        node.setExpectedEndTime(TimeUtil.geTimeFromStr(expectedEndTime,"yyyy-MM-dd"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                        node.setExpectedEndTime(null);
                    }
                }
                //文件相关
                String originFileNameListStr=dataJSONObject.getString("originFileNameListStr");
                String curFilePathAndNameListStr=dataJSONObject.getString("curFilePathAndNameListStr");
                if(originFileNameListStr!=null && curFilePathAndNameListStr!=null && !"".equals(originFileNameListStr)){
                    node.setOriginFileNameListStr(originFileNameListStr);
                    node.setCurFilePathAndNameListStr(curFilePathAndNameListStr);
                }
                nodeList.add(node);
            }
        }
        return nodeList;
    }

    //生成节点与部门之间的关系
    private List<ProjectLiuChengTuNodeTargetDeptRelation> generateProjectLiuChengTuNodeTargetDeptRelationList(List<ProjectLiuChengTuNode> nodeList,Long projectBaseId,Long operateUserId){
        List<ProjectLiuChengTuNodeTargetDeptRelation> relationList=new ArrayList<>();
        for(ProjectLiuChengTuNode node:nodeList){
            JSONObject dataJsonObject=JSONObject.parseObject(node.getDataJsonStr());
            if(dataJsonObject!=null  && dataJsonObject.containsKey("chargeDeptIdList")){
                List<Long> chargeDeptIdList=dataJsonObject.getJSONArray("chargeDeptIdList").toJavaList(Long.class);
                if(chargeDeptIdList!=null && chargeDeptIdList.size()>0){
                    for(Long chargeDeptId:chargeDeptIdList){
                        ProjectLiuChengTuNodeTargetDeptRelation relation=new ProjectLiuChengTuNodeTargetDeptRelation();
                        relation.setProjectBaseId(projectBaseId);
                        relation.setNodeId(node.getId());
                        relation.setChargeDeptId(chargeDeptId);
                        relation.setUpdateUserId(operateUserId);
                        relationList.add(relation);
                    }
                }
            }
        }
        return relationList;
    }

    //补充项目流程图节点关系
    private void supplyProjectLiuChengTuTargetDeptRelation(List<ProjectBase> projectBaseList){
        List<Long> projectBaseIdList=new ArrayList<>();
        for(ProjectBase projectBase:projectBaseList){
            projectBaseIdList.add(projectBase.getId());
        }
        if(projectBaseIdList.size()>0){
            QueryProjectLiuChengTuNodeTargetDeptRelationParam param=new QueryProjectLiuChengTuNodeTargetDeptRelationParam();
            param.setProjectBaseIdList(projectBaseIdList);
            List<ProjectLiuChengTuNodeTargetDeptRelation> relationList=projectLiuChengTuNodeTargetDeptRelationService.selectProjectLiuChengTuNodeTargetDeptRelationList(param);
            Map<Long,List<ProjectLiuChengTuNodeTargetDeptRelation>> projectBaseIdTargetRelationListMap=new HashMap<>();
            for(ProjectLiuChengTuNodeTargetDeptRelation relation:relationList){
                List<ProjectLiuChengTuNodeTargetDeptRelation> targetRelationList=projectBaseIdTargetRelationListMap.get(relation.getProjectBaseId());
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
