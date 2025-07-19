package com.ruoyi.project.projectmanage.controller;

import com.ruoyi.common.api.CommonResult;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseParam;
import com.ruoyi.project.projectmanage.service.IProjectBaseService;
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
@RequestMapping("/project/projectBaseAndUserRelation")
public class ProjectBaseAndUserRelationController extends BaseController
{
    @Resource
    private IProjectBaseService projectBaseService;

}
