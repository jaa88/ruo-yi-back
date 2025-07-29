package com.ruoyi.project.projectmanage.mapper;

import com.ruoyi.project.projectmanage.domain.ProjectBaseAndDeptRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseAndDeptRelationParam;

import java.util.List;

/**
 * 项目列表 数据层
 */
public interface ProjectBaseAndDeptRelationMapper
{
    void insertProjectBaseAndDeptRelationList(List<ProjectBaseAndDeptRelation> relationList);

    void deleteProjectBaseAndDeptRelationByIdList(List<Long> idList);

    List<ProjectBaseAndDeptRelation> selectProjectBaseAndDeptRelationList(QueryProjectBaseAndDeptRelationParam param);
}