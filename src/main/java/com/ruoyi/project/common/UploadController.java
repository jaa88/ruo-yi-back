package com.ruoyi.project.common;

import com.ruoyi.common.api.CommonResult;
import com.ruoyi.project.system.domain.queryandresponse.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/upload")
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);
    //上传文件
    @RequestMapping("/uploadFile")
    @ResponseBody
    public CommonResult uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        logger.info("importFile()");
        Map<String, String> reMap = new HashMap<>();
        if (file.isEmpty()) {
            return CommonResult.failed("文件为空");
        }
        if(file.getOriginalFilename()!=null){
            String fullFileName=file.getOriginalFilename();
            String suffixName=fullFileName.substring(fullFileName.lastIndexOf(".")+1);
            logger.info("上传的后缀名为:" + suffixName);
            // 文件上传路径  应该改为服务器路径
            String filePathPre =getBeginFilePath();
            String relativePath=getYearAndMonthStr()+"/"+new Date().getTime()+"."+suffixName;
            File dest = new File(filePathPre+relativePath);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                UploadFileResponse uploadFileResponse=new UploadFileResponse();
                uploadFileResponse.setFileName(fullFileName);
                uploadFileResponse.setFilePath(relativePath);
                uploadFileResponse.setSuffixName(suffixName);
                return CommonResult.success(uploadFileResponse);
            } catch (Exception e) {
                logger.info(e.toString());
            }
        }

        return CommonResult.success(null);
    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) throws UnsupportedEncodingException {
        Resource file = loadFileAsResource(fileName);
        String encodeFileName= URLEncoder.encode(file.getFilename(),"UTF-8");
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + encodeFileName + "\"")
                .body(file);
    }

    private Resource loadFileAsResource(String fileName) {
        String filePathPre =getBeginFilePath();
        Path fileStorageLocation = Paths.get(filePathPre);
        try {
            Path filePath = fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("Could not find the file " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading file " + fileName, e);
        }
    }

    //获取一开始的路径
    private String getBeginFilePath(){
        String dockerBegin="/var/local";
        //判断var目录是否存在，如果存在就是服务器
        File folder=new File(dockerBegin);
        if(folder.exists() && folder.isDirectory()){
            return dockerBegin+"/";
        }else{
            return "e:/jytest/";
        }
    }

    //获取中间的路径
    private String getYearAndMonthStr(){
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        String year=""+calendar.get(Calendar.YEAR);
        int monthInt=calendar.get(Calendar.MONTH)+1;
        return year+(monthInt>9?monthInt:"0"+monthInt);
    }
}
