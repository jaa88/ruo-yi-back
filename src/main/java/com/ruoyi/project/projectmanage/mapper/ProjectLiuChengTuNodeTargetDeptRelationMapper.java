package com.ruoyi.project.projectmanage.mapper;

import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuNodeTargetDeptRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuNodeTargetDeptRelationParam;

import java.util.List;

/**
 * 项目列表 数据层
 */
public interface ProjectLiuChengTuNodeTargetDeptRelationMapper
{
    void insertProjectLiuChengTuNodeTargetDeptRelationList(List<ProjectLiuChengTuNodeTargetDeptRelation> relationList);

    void deleteProjectLiuChengTuNodeTargetDeptRelationListByProjectBaseId(Long projectBaseId);

    List<ProjectLiuChengTuNodeTargetDeptRelation> selectProjectLiuChengTuNodeTargetDeptRelationList(QueryProjectLiuChengTuNodeTargetDeptRelationParam queryProjectLiuChengTuNodeTargetDeptRelationParam);
}