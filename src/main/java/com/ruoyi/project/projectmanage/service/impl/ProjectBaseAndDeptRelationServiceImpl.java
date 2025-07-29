package com.ruoyi.project.projectmanage.service.impl;

import com.ruoyi.project.projectmanage.domain.ProjectBaseAndDeptRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseAndDeptRelationParam;
import com.ruoyi.project.projectmanage.mapper.ProjectBaseAndDeptRelationMapper;
import com.ruoyi.project.projectmanage.service.IProjectBaseAndDeptRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class ProjectBaseAndDeptRelationServiceImpl implements IProjectBaseAndDeptRelationService {

    @Resource
    private ProjectBaseAndDeptRelationMapper projectBaseAndDeptRelationMapper;

    @Override
    public void insertProjectBaseAndDeptRelationList(List<ProjectBaseAndDeptRelation> relationList) {
        projectBaseAndDeptRelationMapper.insertProjectBaseAndDeptRelationList(relationList);
    }

    @Override
    public void deleteProjectBaseAndDeptRelationByIdList(List<Long> idList) {
        projectBaseAndDeptRelationMapper.deleteProjectBaseAndDeptRelationByIdList(idList);
    }

    @Override
    public List<ProjectBaseAndDeptRelation> selectProjectBaseAndDeptRelationList(QueryProjectBaseAndDeptRelationParam param) {
        return projectBaseAndDeptRelationMapper.selectProjectBaseAndDeptRelationList(param);
    }
}
