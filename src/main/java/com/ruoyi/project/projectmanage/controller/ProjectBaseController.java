package com.ruoyi.project.projectmanage.controller;

import com.ruoyi.common.api.CommonResult;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.projectmanage.service.IProjectBaseService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 公告 信息操作处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/project/base")
public class ProjectBaseController extends BaseController
{
    @Resource
    private IProjectBaseService projectBaseService;

    /**
     * 获取项目列表
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/selectProjectBaseList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult selectProjectBaseList(@RequestBody QueryProjectBaseParam param)
    {
        int totalCount=projectBaseService.selectProjectBaseCount(param);
        List<ProjectBase> projectBaseList=new ArrayList<>();
        if(totalCount>0){
            param.setStartIndex((param.getPageNum()-1)*param.getPageSize());
            projectBaseList=projectBaseService.selectProjectBaseList(param);
        }
        return CommonResult.success(projectBaseList,totalCount);
    }

    /**
     * 新增项目
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/insertProjectBase", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertProjectBase(@RequestBody ProjectBase projectBase)
    {
        projectBase.setUpdateUserId(getUserId());
        projectBaseService.insertProjectBase(projectBase);
        return CommonResult.success(null);
    }

    /**
     * 更新项目
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/updateProjectBase", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateProjectBase(@RequestBody ProjectBase projectBase)
    {
        projectBase.setUpdateUserId(getUserId());
        projectBaseService.updateProjectBase(projectBase,false);
        return CommonResult.success(null);
    }

    /**
     * 删除项目
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/deleteProjectBase", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteProjectBase(@RequestBody ProjectBase projectBase)
    {
        projectBase.setUpdateUserId(getUserId());
        projectBaseService.deleteProjectBase(projectBase);
        return CommonResult.success(null);
    }

}
