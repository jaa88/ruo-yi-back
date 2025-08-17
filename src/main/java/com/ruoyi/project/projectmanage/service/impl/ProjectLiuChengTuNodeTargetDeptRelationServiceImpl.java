package com.ruoyi.project.projectmanage.service.impl;

import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuNodeTargetDeptRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuNodeTargetDeptRelationParam;
import com.ruoyi.project.projectmanage.mapper.ProjectLiuChengTuNodeTargetDeptRelationMapper;
import com.ruoyi.project.projectmanage.service.IProjectLiuChengTuNodeTargetDeptRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class ProjectLiuChengTuNodeTargetDeptRelationServiceImpl implements IProjectLiuChengTuNodeTargetDeptRelationService {

    @Resource
    private ProjectLiuChengTuNodeTargetDeptRelationMapper projectLiuChengTuNodeTargetDeptRelationMapper;

    @Override
    public void insertProjectLiuChengTuNodeTargetDeptRelationList(List<ProjectLiuChengTuNodeTargetDeptRelation> relationList) {
        projectLiuChengTuNodeTargetDeptRelationMapper.insertProjectLiuChengTuNodeTargetDeptRelationList(relationList);
    }

    @Override
    public void deleteProjectLiuChengTuNodeTargetDeptRelationListByProjectBaseId(Long projectBaseId) {
        projectLiuChengTuNodeTargetDeptRelationMapper.deleteProjectLiuChengTuNodeTargetDeptRelationListByProjectBaseId(projectBaseId);
    }

    @Override
    public List<ProjectLiuChengTuNodeTargetDeptRelation> selectProjectLiuChengTuNodeTargetDeptRelationList(QueryProjectLiuChengTuNodeTargetDeptRelationParam queryProjectLiuChengTuNodeTargetDeptRelationParam) {
        List<ProjectLiuChengTuNodeTargetDeptRelation> relationList= projectLiuChengTuNodeTargetDeptRelationMapper.selectProjectLiuChengTuNodeTargetDeptRelationList(queryProjectLiuChengTuNodeTargetDeptRelationParam);
        return relationList;
    }

    @Override
    public int selectProjectLiuChengTuNodeTargetDeptRelationCount(QueryProjectLiuChengTuNodeTargetDeptRelationParam queryProjectLiuChengTuNodeTargetDeptRelationParam) {
        return projectLiuChengTuNodeTargetDeptRelationMapper.selectProjectLiuChengTuNodeTargetDeptRelationCount(queryProjectLiuChengTuNodeTargetDeptRelationParam);
    }
}
