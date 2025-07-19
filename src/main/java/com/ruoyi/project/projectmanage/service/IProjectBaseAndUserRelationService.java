package com.ruoyi.project.projectmanage.service;

import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.ProjectBaseAndUserRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseAndUserRelationParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;

import java.util.List;

/**
 * 项目列表 服务层
 */
public interface IProjectBaseAndUserRelationService
{

    void insertProjectBaseAndUserRelationList(List<ProjectBaseAndUserRelation> relationList);

    void deleteProjectBaseAndUserRelationByIdList(List<Long> idList);

    List<ProjectBaseAndUserRelation> selectProjectBaseAndUserRelationList(QueryProjectBaseAndUserRelationParam param);
}
