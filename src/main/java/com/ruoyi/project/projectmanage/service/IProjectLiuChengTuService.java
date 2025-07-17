package com.ruoyi.project.projectmanage.service;

import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuDataLog;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuTemplate;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuDataLogParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuTemplateParam;

import java.util.List;

/**
 * 项目列表 服务层
 */
public interface IProjectLiuChengTuService
{

    public int selectProjectLiuChengTuTemplateCount(QueryProjectLiuChengTuTemplateParam param);

    public List<ProjectLiuChengTuTemplate> selectProjectLiuChengTuTemplateList(QueryProjectLiuChengTuTemplateParam param);

    public int selectProjectLiuChengTuDataLogCount(QueryProjectLiuChengTuDataLogParam param);

    public List<ProjectLiuChengTuDataLog> selectProjectLiuChengTuDataLogList(QueryProjectLiuChengTuDataLogParam param);

    void insertProjectLiuChengTuDataLog(ProjectLiuChengTuDataLog log);
    //更新流程模板
    void updateProjectLiuChengTuTemplate(ProjectLiuChengTuTemplate projectLiuChengTuTemplate);
}
