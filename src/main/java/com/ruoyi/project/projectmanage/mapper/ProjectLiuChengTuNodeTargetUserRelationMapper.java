package com.ruoyi.project.projectmanage.mapper;

import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuNodeTargetUserRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuNodeTargetUserRelationParam;

import java.util.List;

/**
 * 项目列表 数据层
 */
public interface ProjectLiuChengTuNodeTargetUserRelationMapper
{
    void insertProjectLiuChengTuNodeTargetUserRelationList(List<ProjectLiuChengTuNodeTargetUserRelation> relationList);

    void deleteProjectLiuChengTuNodeTargetUserRelationListByProjectBaseId(Long projectBaseId);

    List<ProjectLiuChengTuNodeTargetUserRelation> selectProjectLiuChengTuNodeTargetUserRelationList(QueryProjectLiuChengTuNodeTargetUserRelationParam queryProjectLiuChengTuNodeTargetUserRelationParam);
}