package com.ruoyi.project.projectmanage.mapper;

import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.system.domain.SysConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 项目列表 数据层
 */
public interface ProjectBaseMapper
{
    public int selectProjectBaseCount(QueryProjectBaseParam param);

    public List<ProjectBase> selectProjectBaseList(QueryProjectBaseParam param);

    //更新项目
    void updateProjectBase(ProjectBase projectBase);
}