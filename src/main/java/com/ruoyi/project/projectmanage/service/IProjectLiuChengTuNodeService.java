package com.ruoyi.project.projectmanage.service;

import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuNode;

import java.util.List;

/**
 * 项目列表 服务层
 */
public interface IProjectLiuChengTuNodeService
{
    void insertProjectLiuChengTuNodeList(List<ProjectLiuChengTuNode> nodeList);

    void deleteProjectLiuChengTuNodeByProjectBaseId(Long projectBaseId);
}
