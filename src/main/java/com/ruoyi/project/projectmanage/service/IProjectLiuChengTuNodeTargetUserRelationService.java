package com.ruoyi.project.projectmanage.service;

import com.ruoyi.project.projectmanage.domain.ProjectBaseAndUserRelation;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuNodeTargetUserRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuNodeTargetUserRelationParam;

import java.util.List;

/**
 * 项目列表 服务层
 */
public interface IProjectLiuChengTuNodeTargetUserRelationService
{
    void insertProjectLiuChengTuNodeTargetUserRelationList(List<ProjectLiuChengTuNodeTargetUserRelation> relationList);

    void deleteProjectLiuChengTuNodeTargetUserRelationListByProjectBaseId(Long projectBaseId);

    List<ProjectLiuChengTuNodeTargetUserRelation> selectProjectLiuChengTuNodeTargetUserRelationList(QueryProjectLiuChengTuNodeTargetUserRelationParam queryProjectLiuChengTuNodeTargetUserRelationParam);
}
