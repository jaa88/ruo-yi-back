package com.ruoyi.project.system.mapper;

import com.ruoyi.project.system.domain.SysFunctionAndUploadFileRelation;
import com.ruoyi.project.system.domain.SysUploadFile;
import com.ruoyi.project.system.domain.queryandresponse.QuerySysFunctionAndUploadFileRelationParam;

import java.util.List;

/**
 * 项目列表 数据层
 */
public interface SysUploadFileMapper
{
    void insertSysUploadFileList(List<SysUploadFile> list);

    void deleteSysUploadFile(List<Long> list);
}