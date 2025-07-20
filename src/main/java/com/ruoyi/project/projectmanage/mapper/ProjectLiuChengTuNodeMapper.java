package com.ruoyi.project.projectmanage.mapper;

import com.ruoyi.project.projectmanage.domain.ProjectBaseAndUserRelation;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuNode;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseAndUserRelationParam;

import java.util.List;

/**
 * 项目列表 数据层
 */
public interface ProjectLiuChengTuNodeMapper
{
    void insertProjectLiuChengTuNodeList(List<ProjectLiuChengTuNode> nodeList);

    void deleteProjectLiuChengTuNodeByProjectBaseId(Long projectBaseId);
}