package com.ruoyi.project.projectmanage.mapper;

import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuTemplate;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuTemplateParam;

import java.util.List;

/**
 * 流程图模板 数据层
 */
public interface ProjectLiuChengTuTemplateMapper
{
    public int selectProjectLiuChengTuTemplateCount(QueryProjectLiuChengTuTemplateParam param);

    public List<ProjectLiuChengTuTemplate> selectProjectLiuChengTuTemplateList(QueryProjectLiuChengTuTemplateParam param);

    void updateProjectLiuChengTuTemplate(ProjectLiuChengTuTemplate projectLiuChengTuTemplate);
}