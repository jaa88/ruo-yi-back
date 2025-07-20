package com.ruoyi.project.projectmanage.domain;

import com.ruoyi.project.system.domain.SysUser;

import java.util.Date;
import java.util.List;

/*
* 模型base 和 user之间的关系
* */
public class ProjectLiuChengTuNodeTargetUserRelation {
    private Long id;
    private Long projectBaseId;
    private Long nodeId;
    private Long chargeUserId;
    private SysUser chargeUser;
    private Long updateUserId;
    private Date updateTime;
    //补充对应项目名称、任务名称
    private String projectName;
    private String taskName;
    private String dataJsonStr;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDataJsonStr() {
        return dataJsonStr;
    }

    public void setDataJsonStr(String dataJsonStr) {
        this.dataJsonStr = dataJsonStr;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProjectBaseId() {
        return projectBaseId;
    }

    public void setProjectBaseId(Long projectBaseId) {
        this.projectBaseId = projectBaseId;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Long getChargeUserId() {
        return chargeUserId;
    }

    public void setChargeUserId(Long chargeUserId) {
        this.chargeUserId = chargeUserId;
    }

    public SysUser getChargeUser() {
        return chargeUser;
    }

    public void setChargeUser(SysUser chargeUser) {
        this.chargeUser = chargeUser;
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
