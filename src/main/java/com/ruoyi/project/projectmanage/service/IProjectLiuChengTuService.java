package com.ruoyi.project.projectmanage.service;

import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuDataLog;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuTemplate;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuDataLogParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuTemplateParam;

import java.util.List;
import java.util.Map;

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
    //新增模板
    void insertProjectTemplate(ProjectLiuChengTuTemplate projectLiuChengTuTemplate);
    //更新流程模板
    void updateProjectTemplate(ProjectLiuChengTuTemplate projectLiuChengTuTemplate);
    //删除流程模板
    void deleteProjectTemplate(ProjectLiuChengTuTemplate projectLiuChengTuTemplate);

    ProjectLiuChengTuDataLog selectProjectLiuChengTuDataLogById(Long id);

    List<ProjectLiuChengTuDataLog> selectProjectLiuChengTuDataLogListByIdList(List<Long> idList);

    Map<Long,ProjectLiuChengTuDataLog> selectIdTargetProjectLiuChengTuDataLogMap(List<Long> idList);
}
