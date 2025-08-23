package com.ruoyi.project.system.mapper;

import com.ruoyi.project.projectmanage.domain.ProjectBaseAndDeptRelation;
import com.ruoyi.project.projectmanage.domain.queryandresponse.QueryProjectBaseAndDeptRelationParam;
import com.ruoyi.project.system.domain.SysFunctionAndUploadFileRelation;
import com.ruoyi.project.system.domain.queryandresponse.QuerySysFunctionAndUploadFileRelationParam;

import java.util.List;

/**
 * 项目列表 数据层
 */
public interface SysFunctionAndUploadFileRelationMapper
{
    void insertFunctionAndUploadFileRelation(List<SysFunctionAndUploadFileRelation> relationList);

    void deleteFunctionAndUploadFileRelation(List<SysFunctionAndUploadFileRelation> list);

    List<SysFunctionAndUploadFileRelation> selectFunctionAndUploadFileRelationList(QuerySysFunctionAndUploadFileRelationParam param);
}