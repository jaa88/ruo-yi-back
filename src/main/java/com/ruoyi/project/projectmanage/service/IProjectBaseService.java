package com.ruoyi.project.projectmanage.service;

import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.system.domain.SysConfig;

import java.util.List;

/**
 * 项目列表 服务层
 */
public interface IProjectBaseService
{

    public int selectProjectBaseCount(QueryProjectBaseParam param);

    public List<ProjectBase> selectProjectBaseList(QueryProjectBaseParam param);
}
