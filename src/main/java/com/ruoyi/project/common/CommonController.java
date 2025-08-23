package com.ruoyi.project.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.api.CommonResult;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.project.projectmanage.domain.ProjectBase;
import com.ruoyi.project.system.domain.SysFunctionAndUploadFileRelation;
import com.ruoyi.project.system.domain.SysUploadFile;
import com.ruoyi.project.system.domain.queryandresponse.DeleteAndInsertSysFunctionAndUploadFileRelationParam;
import com.ruoyi.project.system.domain.queryandresponse.QuerySysFunctionAndUploadFileRelationParam;
import com.ruoyi.project.system.service.ISysUploadFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.config.RuoYiConfig;
import com.ruoyi.framework.config.ServerConfig;
import com.ruoyi.framework.web.domain.AjaxResult;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/common")
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;
    @Resource
    private ISysUploadFileService sysUploadFileService;

    private static final String FILE_DELIMETER = ",";


    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.checkAllowDownload(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = RuoYiConfig.getDownloadPath() + fileName;

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求（单个）
     */
    @PostMapping("/upload")
    public AjaxResult uploadFile(
            @RequestParam("file")MultipartFile file,
            @RequestParam(value = "baseDirStr", required = false) String baseDirStr,
            @RequestParam(value = "functionName", required = false) String functionName,
            @RequestParam(value = "functionId", required = false) Long functionId
    ) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file,baseDirStr!=null && !"".equals(baseDirStr),baseDirStr);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("url", url);
            ajax.put("fileName", fileName);
            ajax.put("newFileName", FileUtils.getName(fileName));
            ajax.put("originalFilename", file.getOriginalFilename());
            //存入库中文件库中
            SysUploadFile sysUploadFile=new SysUploadFile();
            sysUploadFile.setOriginalFileName(file.getOriginalFilename());
            sysUploadFile.setCurFilePathAndName(fileName);
            sysUploadFileService.insertSysUploadFileList(Arrays.asList(sysUploadFile),functionName,functionId);
            ajax.put("uploadFileId",sysUploadFile.getId());
            return ajax;
        }
        catch (Exception e)
        {
            log.error(e.toString());
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 删除并新增 功能与上传文件之间的关系
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/deleteAndInsertFunctionAndUploadFileRelation", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteAndInsertFunctionAndUploadFileRelation(@RequestBody DeleteAndInsertSysFunctionAndUploadFileRelationParam param)
    {
        if(param!=null && param.getFunctionName()!=null && param.getFunctionId()!=null && !"".equals(param.getFunctionName())){
            String functionName=param.getFunctionName();
            Long functionId=param.getFunctionId();

            LoginUser loginUser = SecurityUtils.getLoginUser();
            //先获取现有的
            QuerySysFunctionAndUploadFileRelationParam querySysFunctionAndUploadFileRelationParam=new QuerySysFunctionAndUploadFileRelationParam();
            querySysFunctionAndUploadFileRelationParam.setFunctionName(param.getFunctionName());
            querySysFunctionAndUploadFileRelationParam.setFunctionIdList(Arrays.asList(param.getFunctionId()));
            List<SysFunctionAndUploadFileRelation> existRelationList=sysUploadFileService.selectFunctionAndUploadFileRelationList(querySysFunctionAndUploadFileRelationParam);
            //现有的upLoadFileIdList
            List<Long> existUploadFileIdList=new ArrayList<>();
            for(SysFunctionAndUploadFileRelation relation:existRelationList){
                existUploadFileIdList.add(relation.getUploadFileId());
            }
            //传过来的
            List<Long> transferUploadFileIdList=param.getUploadFileIdList();
            //新增的
            List<SysFunctionAndUploadFileRelation> needInsertRelationList=new ArrayList<>();
            for(Long uploadFileId:transferUploadFileIdList){
                if(!existUploadFileIdList.contains(uploadFileId)){
                    SysFunctionAndUploadFileRelation relation=new SysFunctionAndUploadFileRelation();
                    relation.setCreateUserId(loginUser.getUserId());
                    relation.setUpdateUserId(loginUser.getUserId());
                    relation.setFunctionName(functionName);
                    relation.setFunctionId(functionId);
                    relation.setUploadFileId(uploadFileId);
                    needInsertRelationList.add(relation);
                }
            }
            //需要删除的
            List<SysFunctionAndUploadFileRelation> needDeleteRelationList=new ArrayList<>();
            for(SysFunctionAndUploadFileRelation relation:existRelationList){
                if(!transferUploadFileIdList.contains(relation.getUploadFileId())){
                    relation.setUpdateUserId(loginUser.getUserId());
                    needDeleteRelationList.add(relation);
                }
            }
            if(needDeleteRelationList.size()>0){
                sysUploadFileService.deleteFunctionAndUploadFileRelation(needDeleteRelationList);
            }
            if(needInsertRelationList.size()>0){
                sysUploadFileService.insertFunctionAndUploadFileRelation(needInsertRelationList);
            }
        }
        return CommonResult.success(null);
    }

    /**
     * 新增功能与上传文件之间的关系
     */
    //@PreAuthorize("@ss.hasPermi('project:base:list')")
    @RequestMapping(value = "/insertFunctionAndUploadFileRelation", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult deleteProjectBase(@RequestBody SysFunctionAndUploadFileRelation relation)
    {
        // 获取当前的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        relation.setUpdateUserId(loginUser.getUserId());
        relation.setCreateUserId(loginUser.getUserId());
        sysUploadFileService.insertFunctionAndUploadFileRelation(Arrays.asList(relation));
        return CommonResult.success(null);
    }


    /**
     * 通用上传请求（多个）
     */
    @PostMapping("/uploads")
    public AjaxResult uploadFiles(List<MultipartFile> files) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = RuoYiConfig.getUploadPath();
            List<String> urls = new ArrayList<String>();
            List<String> fileNames = new ArrayList<String>();
            List<String> newFileNames = new ArrayList<String>();
            List<String> originalFilenames = new ArrayList<String>();
            for (MultipartFile file : files)
            {
                // 上传并返回新文件名称
                String fileName = FileUploadUtils.upload(filePath, file,false,"");
                String url = serverConfig.getUrl() + fileName;
                urls.add(url);
                fileNames.add(fileName);
                newFileNames.add(FileUtils.getName(fileName));
                originalFilenames.add(file.getOriginalFilename());
            }
            AjaxResult ajax = AjaxResult.success();
            ajax.put("urls", StringUtils.join(urls, FILE_DELIMETER));
            ajax.put("fileNames", StringUtils.join(fileNames, FILE_DELIMETER));
            ajax.put("newFileNames", StringUtils.join(newFileNames, FILE_DELIMETER));
            ajax.put("originalFilenames", StringUtils.join(originalFilenames, FILE_DELIMETER));
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        try
        {
            if (!FileUtils.checkAllowDownload(resource))
            {
                throw new Exception(StringUtils.format("资源文件({})非法，不允许下载。 ", resource));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getProfile();
            // 数据库资源地址
            String downloadPath = localPath + FileUtils.stripPrefix(resource);
            // 下载名称
            String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, downloadName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }
}
