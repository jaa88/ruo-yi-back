package com.ruoyi.project.projectmanage.service.impl;

import com.ruoyi.project.projectmanage.domain.ProjectBaseAndUserRelation;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuNode;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseAndUserRelationParam;
import com.ruoyi.project.projectmanage.mapper.ProjectBaseAndUserRelationMapper;
import com.ruoyi.project.projectmanage.mapper.ProjectLiuChengTuNodeMapper;
import com.ruoyi.project.projectmanage.service.IProjectBaseAndUserRelationService;
import com.ruoyi.project.projectmanage.service.IProjectLiuChengTuNodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class ProjectLiuChengTuNodeServiceImpl implements IProjectLiuChengTuNodeService {

    @Resource
    private ProjectLiuChengTuNodeMapper projectLiuChengTuNodeMapper;

    @Override
    public void insertProjectLiuChengTuNodeList(List<ProjectLiuChengTuNode> nodeList) {
        projectLiuChengTuNodeMapper.insertProjectLiuChengTuNodeList(nodeList);
    }

    @Override
    public void deleteProjectLiuChengTuNodeByProjectBaseId(Long projectBaseId) {
        projectLiuChengTuNodeMapper.deleteProjectLiuChengTuNodeByProjectBaseId(projectBaseId);
    }

    @Override
    public List<ProjectLiuChengTuNode> selectSimpleProjectLiuChengTuNodeListByProjectBaseIdList(List<Long> list) {
        return projectLiuChengTuNodeMapper.selectSimpleProjectLiuChengTuNodeListByProjectBaseIdList(list);
    }
}
