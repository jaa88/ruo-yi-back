package com.ruoyi.project.system.service.impl;

import com.ruoyi.common.constant.CacheConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.system.domain.SysConfig;
import com.ruoyi.project.system.domain.SysFunctionAndUploadFileRelation;
import com.ruoyi.project.system.domain.SysUploadFile;
import com.ruoyi.project.system.domain.queryandresponse.QuerySysFunctionAndUploadFileRelationParam;
import com.ruoyi.project.system.mapper.SysConfigMapper;
import com.ruoyi.project.system.mapper.SysFunctionAndUploadFileRelationMapper;
import com.ruoyi.project.system.mapper.SysUploadFileMapper;
import com.ruoyi.project.system.service.ISysConfigService;
import com.ruoyi.project.system.service.ISysUploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 参数配置 服务层实现
 * 
 * @author ruoyi
 */
@Service
public class SysUploadFileServiceImpl implements ISysUploadFileService
{
    @Resource
    private SysUploadFileMapper sysUploadFileMapper;
    @Resource
    private SysFunctionAndUploadFileRelationMapper sysFunctionAndUploadFileRelationMapper;

    @Override
    public void insertSysUploadFileList(List<SysUploadFile> list, String functionName, Long functionId) {
        if(list!=null && list.size()>0){
            // 获取当前的用户
            LoginUser loginUser = SecurityUtils.getLoginUser();
            for(SysUploadFile file:list){
                file.setCreateUserId(loginUser.getUserId());
                file.setUpdateUserId(loginUser.getUserId());
            }

            sysUploadFileMapper.insertSysUploadFileList(list);
            //如果功能与文件之间存在关系，则需要新增相关关系
            if(functionId!=null && functionName!=null && !"".equals(functionName.trim())){
                List<SysFunctionAndUploadFileRelation> relationList=new ArrayList<>(list.size());
                for(SysUploadFile file:list){
                    SysFunctionAndUploadFileRelation relation=new SysFunctionAndUploadFileRelation();
                    relation.setFunctionName(functionName);
                    relation.setFunctionId(functionId);
                    relation.setUploadFileId(file.getId());
                    relation.setCreateUserId(loginUser.getUserId());
                    relation.setUpdateUserId(loginUser.getUserId());
                }
                sysFunctionAndUploadFileRelationMapper.insertFunctionAndUploadFileRelation(relationList);
            }
        }
    }

    @Override
    public void deleteSysUploadFile(List<Long> list) {
        sysUploadFileMapper.deleteSysUploadFile(list);
    }

    @Override
    public void insertFunctionAndUploadFileRelation(List<SysFunctionAndUploadFileRelation> relationList) {
        sysFunctionAndUploadFileRelationMapper.insertFunctionAndUploadFileRelation(relationList);
    }

    @Override
    public void deleteFunctionAndUploadFileRelation(List<SysFunctionAndUploadFileRelation> relationList) {
        sysFunctionAndUploadFileRelationMapper.deleteFunctionAndUploadFileRelation(relationList);
    }

    @Override
    public List<SysFunctionAndUploadFileRelation> selectFunctionAndUploadFileRelationList(QuerySysFunctionAndUploadFileRelationParam param) {
        return sysFunctionAndUploadFileRelationMapper.selectFunctionAndUploadFileRelationList(param);
    }
}
