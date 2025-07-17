package com.ruoyi.project.projectmanage.domain;

import java.util.Date;

/*
* 模型基础类
* */
public class ProjectBase {
    private Long id;
    private String projectName;
    private Long currentLiuChengTuDataLogId;
    private Date createTime;
    private Long projectChargeUserId;
    private Long updateUserId;
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getCurrentLiuChengTuDataLogId() {
        return currentLiuChengTuDataLogId;
    }

    public void setCurrentLiuChengTuDataLogId(Long currentLiuChengTuDataLogId) {
        this.currentLiuChengTuDataLogId = currentLiuChengTuDataLogId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProjectChargeUserId() {
        return projectChargeUserId;
    }

    public void setProjectChargeUserId(Long projectChargeUserId) {
        this.projectChargeUserId = projectChargeUserId;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
