package com.ruoyi.project.projectmanage.service.impl;

import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuDataLog;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuTemplate;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuDataLogParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuTemplateParam;
import com.ruoyi.project.projectmanage.mapper.ProjectBaseMapper;
import com.ruoyi.project.projectmanage.mapper.ProjectLiuChengTuDataLogMapper;
import com.ruoyi.project.projectmanage.mapper.ProjectLiuChengTuTemplateMapper;
import com.ruoyi.project.projectmanage.service.IProjectBaseService;
import com.ruoyi.project.projectmanage.service.IProjectLiuChengTuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class ProjectLiuChengTuServiceImpl implements IProjectLiuChengTuService {

    @Resource
    private ProjectLiuChengTuTemplateMapper projectLiuChengTuTemplateMapper;
    @Resource
    private ProjectLiuChengTuDataLogMapper projectLiuChengTuDataLogMapper;
    @Resource
    private IProjectLiuChengTuService projectLiuChengTuService;

    @Override
    public int selectProjectLiuChengTuTemplateCount(QueryProjectLiuChengTuTemplateParam param) {
        return projectLiuChengTuTemplateMapper.selectProjectLiuChengTuTemplateCount(param);
    }

    @Override
    public List<ProjectLiuChengTuTemplate> selectProjectLiuChengTuTemplateList(QueryProjectLiuChengTuTemplateParam param) {
        List<ProjectLiuChengTuTemplate> list= projectLiuChengTuTemplateMapper.selectProjectLiuChengTuTemplateList(param);
        List<Long> idList=new ArrayList<>();
        for(ProjectLiuChengTuTemplate template:list){
            if(template.getCurrentLiuChengTuDataLogId()!=null){
                idList.add(template.getCurrentLiuChengTuDataLogId());
            }
        }
        if(idList.size()>0){
            Map<Long, ProjectLiuChengTuDataLog> idTargetProjectLiuChengTuDataLogMap=projectLiuChengTuService.selectIdTargetProjectLiuChengTuDataLogMap(idList);
            for(ProjectLiuChengTuTemplate template:list){
                if(idTargetProjectLiuChengTuDataLogMap.get(template.getCurrentLiuChengTuDataLogId())!=null){
                    template.setCellsJsonStr(idTargetProjectLiuChengTuDataLogMap.get(template.getCurrentLiuChengTuDataLogId()).getCurrentCellsJsonStr());
                }
            }
        }
        return list;
    }

    @Override
    public int selectProjectLiuChengTuDataLogCount(QueryProjectLiuChengTuDataLogParam param) {
        return projectLiuChengTuDataLogMapper.selectProjectLiuChengTuDataLogCount(param);
    }

    @Override
    public List<ProjectLiuChengTuDataLog> selectProjectLiuChengTuDataLogList(QueryProjectLiuChengTuDataLogParam param) {
        return projectLiuChengTuDataLogMapper.selectProjectLiuChengTuDataLogList(param);
    }

    @Override
    public void insertProjectLiuChengTuDataLog(ProjectLiuChengTuDataLog log) {
        projectLiuChengTuDataLogMapper.insertProjectLiuChengTuDataLog(log);
    }

    @Override
    public void insertProjectTemplate(ProjectLiuChengTuTemplate projectLiuChengTuTemplate) {
        projectLiuChengTuTemplateMapper.insertProjectTemplate(projectLiuChengTuTemplate);
    }

    @Override
    public void updateProjectTemplate(ProjectLiuChengTuTemplate projectLiuChengTuTemplate) {
        projectLiuChengTuTemplateMapper.updateProjectTemplate(projectLiuChengTuTemplate);
    }

    @Override
    public void deleteProjectTemplate(ProjectLiuChengTuTemplate projectLiuChengTuTemplate) {
        projectLiuChengTuTemplateMapper.deleteProjectTemplate(projectLiuChengTuTemplate);
    }

    @Override
    public ProjectLiuChengTuDataLog selectProjectLiuChengTuDataLogById(Long id) {
        return projectLiuChengTuDataLogMapper.selectProjectLiuChengTuDataLogById(id);
    }

    @Override
    public List<ProjectLiuChengTuDataLog> selectProjectLiuChengTuDataLogListByIdList(List<Long> idList) {
        return projectLiuChengTuDataLogMapper.selectProjectLiuChengTuDataLogListByIdList(idList);
    }

    @Override
    public Map<Long, ProjectLiuChengTuDataLog> selectIdTargetProjectLiuChengTuDataLogMap(List<Long> idList) {
        Map<Long, ProjectLiuChengTuDataLog> map=new HashMap<>();
        List<ProjectLiuChengTuDataLog> logList=projectLiuChengTuDataLogMapper.selectProjectLiuChengTuDataLogListByIdList(idList);
        for(ProjectLiuChengTuDataLog log:logList){
            map.put(log.getId(),log);
        }
        return map;
    }

    @Override
    public void updateProjectLiuChengTuDataLog(ProjectLiuChengTuDataLog log) {
        projectLiuChengTuDataLogMapper.updateProjectLiuChengTuDataLog(log);
    }
}
