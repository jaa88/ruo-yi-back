package com.ruoyi.project.system.domain;

import java.util.Date;

public class SysUploadFile {
    private Long id;
    private String originalFileName;
    private String curFilePathAndName;
    private Date updateTime;
    private Date createTime;
    private Long createUserId;
    private Long updateUserId;
    //此处是记录软删除，但是物理存储上也将被删除
    private Integer deleteFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getCurFilePathAndName() {
        return curFilePathAndName;
    }

    public void setCurFilePathAndName(String curFilePathAndName) {
        this.curFilePathAndName = curFilePathAndName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}