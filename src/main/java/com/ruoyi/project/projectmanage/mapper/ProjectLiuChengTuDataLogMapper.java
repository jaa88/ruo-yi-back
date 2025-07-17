package com.ruoyi.project.projectmanage.mapper;

import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuDataLog;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuTemplate;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuDataLogParam;

import java.util.List;

/**
 * 流程图数据 数据层
 */
public interface ProjectLiuChengTuDataLogMapper
{
    public int selectProjectLiuChengTuDataLogCount(QueryProjectLiuChengTuDataLogParam param);

    public List<ProjectLiuChengTuDataLog> selectProjectLiuChengTuDataLogList(QueryProjectLiuChengTuDataLogParam param);
}