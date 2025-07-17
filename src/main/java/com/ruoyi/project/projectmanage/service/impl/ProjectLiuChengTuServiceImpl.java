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
import java.util.List;

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

    @Override
    public int selectProjectLiuChengTuTemplateCount(QueryProjectLiuChengTuTemplateParam param) {
        return projectLiuChengTuTemplateMapper.selectProjectLiuChengTuTemplateCount(param);
    }

    @Override
    public List<ProjectLiuChengTuTemplate> selectProjectLiuChengTuTemplateList(QueryProjectLiuChengTuTemplateParam param) {
        return projectLiuChengTuTemplateMapper.selectProjectLiuChengTuTemplateList(param);
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
    public void updateProjectLiuChengTuTemplate(ProjectLiuChengTuTemplate projectLiuChengTuTemplate) {
        projectLiuChengTuTemplateMapper.updateProjectLiuChengTuTemplate(projectLiuChengTuTemplate);
    }
}
