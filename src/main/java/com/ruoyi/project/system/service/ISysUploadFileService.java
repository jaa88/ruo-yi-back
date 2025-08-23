package com.ruoyi.project.system.service;

import com.ruoyi.project.system.domain.SysConfig;
import com.ruoyi.project.system.domain.SysFunctionAndUploadFileRelation;
import com.ruoyi.project.system.domain.SysUploadFile;
import com.ruoyi.project.system.domain.queryandresponse.QuerySysFunctionAndUploadFileRelationParam;

import java.util.List;

/**
 * 参数配置 服务层
 * 
 * @author ruoyi
 */
public interface ISysUploadFileService
{
    void insertSysUploadFileList(List<SysUploadFile> list,String functionName,Long functionId);

    void deleteSysUploadFile(List<Long> list);

    void insertFunctionAndUploadFileRelation(List<SysFunctionAndUploadFileRelation> relationList);

    void deleteFunctionAndUploadFileRelation(List<SysFunctionAndUploadFileRelation> relationList);

    List<SysFunctionAndUploadFileRelation> selectFunctionAndUploadFileRelationList(QuerySysFunctionAndUploadFileRelationParam param);
}
