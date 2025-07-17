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
}
