package com.ruoyi.project.projectmanage.service;

import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuNodeTargetDeptRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuNodeTargetDeptRelationParam;

import java.util.List;

/**
 * 项目列表 服务层
 */
public interface IProjectLiuChengTuNodeTargetDeptRelationService
{
    void insertProjectLiuChengTuNodeTargetDeptRelationList(List<ProjectLiuChengTuNodeTargetDeptRelation> relationList);

    void deleteProjectLiuChengTuNodeTargetDeptRelationListByProjectBaseId(Long projectBaseId);

    List<ProjectLiuChengTuNodeTargetDeptRelation> selectProjectLiuChengTuNodeTargetDeptRelationList(QueryProjectLiuChengTuNodeTargetDeptRelationParam queryProjectLiuChengTuNodeTargetDeptRelationParam);

    int selectProjectLiuChengTuNodeTargetDeptRelationCount(QueryProjectLiuChengTuNodeTargetDeptRelationParam queryProjectLiuChengTuNodeTargetDeptRelationParam);
}
