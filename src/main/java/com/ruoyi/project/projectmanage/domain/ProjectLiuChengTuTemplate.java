package com.ruoyi.project.projectmanage.domain;

import java.util.Date;

/*
* 流程图模板
* */
public class ProjectLiuChengTuTemplate {

    private Long id;
    private Long currentLiuChengTuDataLogId;
    private Long createUserId;
    private Date createTime;
    private Long updateUserId;
    private Date updateTime;
    private String templateName;
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCurrentLiuChengTuDataLogId() {
        return currentLiuChengTuDataLogId;
    }

    public void setCurrentLiuChengTuDataLogId(Long currentLiuChengTuDataLogId) {
        this.currentLiuChengTuDataLogId = currentLiuChengTuDataLogId;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
