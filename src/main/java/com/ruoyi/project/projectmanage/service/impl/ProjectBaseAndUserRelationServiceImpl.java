package com.ruoyi.project.projectmanage.service.impl;

import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.ProjectBaseAndUserRelation;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuDataLog;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseAndUserRelationParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.projectmanage.mapper.ProjectBaseAndUserRelationMapper;
import com.ruoyi.project.projectmanage.mapper.ProjectBaseMapper;
import com.ruoyi.project.projectmanage.service.IProjectBaseAndUserRelationService;
import com.ruoyi.project.projectmanage.service.IProjectBaseService;
import com.ruoyi.project.projectmanage.service.IProjectLiuChengTuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class ProjectBaseAndUserRelationServiceImpl implements IProjectBaseAndUserRelationService {

    @Resource
    private ProjectBaseAndUserRelationMapper projectBaseAndUserRelationMapper;

    @Override
    public void insertProjectBaseAndUserRelationList(List<ProjectBaseAndUserRelation> relationList) {
        projectBaseAndUserRelationMapper.insertProjectBaseAndUserRelationList(relationList);
    }

    @Override
    public void deleteProjectBaseAndUserRelationByIdList(List<Long> idList) {
        projectBaseAndUserRelationMapper.deleteProjectBaseAndUserRelationByIdList(idList);
    }

    @Override
    public List<ProjectBaseAndUserRelation> selectProjectBaseAndUserRelationList(QueryProjectBaseAndUserRelationParam param) {
        return projectBaseAndUserRelationMapper.selectProjectBaseAndUserRelationList(param);
    }
}
