package com.ruoyi.project.projectmanage.mapper;

import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuDataLog;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuTemplate;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuDataLogParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 流程图数据 数据层
 */
public interface ProjectLiuChengTuDataLogMapper
{
    public int selectProjectLiuChengTuDataLogCount(QueryProjectLiuChengTuDataLogParam param);

    public List<ProjectLiuChengTuDataLog> selectProjectLiuChengTuDataLogList(QueryProjectLiuChengTuDataLogParam param);

    void insertProjectLiuChengTuDataLog(ProjectLiuChengTuDataLog log);

    ProjectLiuChengTuDataLog selectProjectLiuChengTuDataLogById(Long id);

    List<ProjectLiuChengTuDataLog> selectProjectLiuChengTuDataLogListByIdList(@Param("idList") List<Long> idList);

    public void updateProjectLiuChengTuDataLog(ProjectLiuChengTuDataLog log);
}