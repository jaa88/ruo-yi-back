package com.ruoyi.project.projectmanage.controller;

import com.ruoyi.common.api.CommonResult;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuDataLog;
import com.ruoyi.project.projectmanage.domain.ProjectLiuChengTuTemplate;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuDataLogParam;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectLiuChengTuTemplateParam;
import com.ruoyi.project.projectmanage.service.IProjectBaseService;
import com.ruoyi.project.projectmanage.service.IProjectLiuChengTuService;
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
@RequestMapping("/project/liuChengTu")
public class ProjectLiuChengTuController extends BaseController
{
    @Resource
    private IProjectLiuChengTuService projectLiuChengTuService;
    @Resource
    private IProjectBaseService projectBaseService;

    /**
     * 获取模板列表
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/selectProjectLiuChengTuTemplateList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult selectProjectLiuChengTuTemplateList(@RequestBody QueryProjectLiuChengTuTemplateParam param)
    {
        int totalCount=projectLiuChengTuService.selectProjectLiuChengTuTemplateCount(param);
        List<ProjectLiuChengTuTemplate> projectLiuChengTuTemplateList=new ArrayList<>();
        if(totalCount>0){
            param.setStartIndex((param.getPageNum()-1)*param.getPageSize());
            projectLiuChengTuTemplateList=projectLiuChengTuService.selectProjectLiuChengTuTemplateList(param);
        }
        return CommonResult.success(projectLiuChengTuTemplateList,totalCount);
    }

    /**
     * 获取流程图数据列表
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/selectProjectLiuChengTuDataLogList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult selectProjectLiuChengTuDataLogList(@RequestBody QueryProjectLiuChengTuDataLogParam param)
    {
        int totalCount=projectLiuChengTuService.selectProjectLiuChengTuDataLogCount(param);
        List<ProjectLiuChengTuDataLog> list=new ArrayList<>();
        if(totalCount>0){
            param.setStartIndex((param.getPageNum()-1)*param.getPageSize());
            list=projectLiuChengTuService.selectProjectLiuChengTuDataLogList(param);
        }
        return CommonResult.success(list,totalCount);
    }

    /**
     * 获取流程图数据列表
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/insertLiuChengTuDataLog", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertLiuChengTuDataLog(@RequestBody ProjectLiuChengTuDataLog log)
    {
        //新增一条记录，同时，根据是baseId还是templateId,来更新对应记录
        Long userId=getUserId();
        log.setOperateUserId(userId);
        projectLiuChengTuService.insertProjectLiuChengTuDataLog(log);
        //模板id不为空
        if(log.getProjectLiuChengTuTemplateId()!=null){
            ProjectLiuChengTuTemplate projectLiuChengTuTemplate=new ProjectLiuChengTuTemplate();
            projectLiuChengTuTemplate.setId(log.getProjectLiuChengTuTemplateId());
            projectLiuChengTuTemplate.setCurrentLiuChengTuDataLogId(log.getId());
            projectLiuChengTuTemplate.setUpdateUserId(userId);
            projectLiuChengTuService.updateProjectTemplate(projectLiuChengTuTemplate);
        }
        //项目id不为空
        if(log.getProjectBaseId()!=null){
            ProjectBase projectBase=new ProjectBase();
            projectBase.setId(log.getProjectBaseId());
            projectBase.setCurrentLiuChengTuDataLogId(log.getId());
            projectBase.setUpdateUserId(userId);
            projectBaseService.updateProjectBase(projectBase);
        }
        return CommonResult.success(null);
    }

    /**
     * 新增模板
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/insertProjectTemplate", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult insertProjectTemplate(@RequestBody ProjectLiuChengTuTemplate projectLiuChengTuTemplate)
    {
        projectLiuChengTuTemplate.setUpdateUserId(getUserId());
        projectLiuChengTuTemplate.setCreateUserId(getUserId());
        projectLiuChengTuService.insertProjectTemplate(projectLiuChengTuTemplate);
        return CommonResult.success(null);
    }

    /**
     * 更新模板
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/updateProjectTemplate", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateProjectBase(@RequestBody ProjectLiuChengTuTemplate projectLiuChengTuTemplate)
    {
        projectLiuChengTuTemplate.setUpdateUserId(getUserId());
        projectLiuChengTuService.updateProjectTemplate(projectLiuChengTuTemplate);
        return CommonResult.success(null);
    }

    /**
     * 删除模板
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/deleteProjectTemplate", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteProjectTemplate(@RequestBody ProjectLiuChengTuTemplate projectLiuChengTuTemplate)
    {
        projectLiuChengTuTemplate.setUpdateUserId(getUserId());
        projectLiuChengTuService.deleteProjectTemplate(projectLiuChengTuTemplate);
        return CommonResult.success(null);
    }

}
