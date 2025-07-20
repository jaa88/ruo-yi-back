package com.ruoyi.project.projectmanage.service.impl;

import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuNodeTargetUserRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuNodeTargetUserRelationParam;
import com.ruoyi.project.projectmanage.mapper.ProjectLiuChengTuNodeTargetUserRelationMapper;
import com.ruoyi.project.projectmanage.service.IProjectLiuChengTuNodeTargetUserRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class ProjectLiuChengTuNodeTargetUserRelationServiceImpl implements IProjectLiuChengTuNodeTargetUserRelationService {

    @Resource
    private ProjectLiuChengTuNodeTargetUserRelationMapper projectLiuChengTuNodeTargetUserRelationMapper;

    @Override
    public void insertProjectLiuChengTuNodeTargetUserRelationList(List<ProjectLiuChengTuNodeTargetUserRelation> relationList) {
        projectLiuChengTuNodeTargetUserRelationMapper.insertProjectLiuChengTuNodeTargetUserRelationList(relationList);
    }

    @Override
    public void deleteProjectLiuChengTuNodeTargetUserRelationListByProjectBaseId(Long projectBaseId) {
        projectLiuChengTuNodeTargetUserRelationMapper.deleteProjectLiuChengTuNodeTargetUserRelationListByProjectBaseId(projectBaseId);
    }

    @Override
    public List<ProjectLiuChengTuNodeTargetUserRelation> selectProjectLiuChengTuNodeTargetUserRelationList(QueryProjectLiuChengTuNodeTargetUserRelationParam queryProjectLiuChengTuNodeTargetUserRelationParam) {
        return projectLiuChengTuNodeTargetUserRelationMapper.selectProjectLiuChengTuNodeTargetUserRelationList(queryProjectLiuChengTuNodeTargetUserRelationParam);
    }


}
