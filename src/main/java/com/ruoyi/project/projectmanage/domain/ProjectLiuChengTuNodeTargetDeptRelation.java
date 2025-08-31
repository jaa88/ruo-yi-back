package com.ruoyi.project.projectmanage.domain;

import com.ruoyi.project.system.domain.SysUser;

import java.util.Date;

/*
* 模型base 和 user之间的关系
* */
public class ProjectLiuChengTuNodeTargetDeptRelation {
    private Long id;
    private Long projectBaseId;
    private Long nodeId;
    private Long chargeDeptId;
    private SysUser chargeUser;
    private Long updateUserId;
    private Date updateTime;
    //补充对应项目名称、任务名称
    private String projectName;
    private String dataJsonStr;
    private String deptName;
    //节点内包含的的信息
    private Integer status;
    private String taskName;
    private Date startTime;
    private Date expectedEndTime;
    private String originFileNameListStr;
    private String curFilePathAndNameListStr;
    //添加实际结束时间，预期开始时间
    private Date endTime;
    private Date expectedStartTime;

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getExpectedStartTime() {
        return expectedStartTime;
    }

    public void setExpectedStartTime(Date expectedStartTime) {
        this.expectedStartTime = expectedStartTime;
    }

    public String getOriginFileNameListStr() {
        return originFileNameListStr;
    }

    public void setOriginFileNameListStr(String originFileNameListStr) {
        this.originFileNameListStr = originFileNameListStr;
    }

    public String getCurFilePathAndNameListStr() {
        return curFilePathAndNameListStr;
    }

    public void setCurFilePathAndNameListStr(String curFilePathAndNameListStr) {
        this.curFilePathAndNameListStr = curFilePathAndNameListStr;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getExpectedEndTime() {
        return expectedEndTime;
    }

    public void setExpectedEndTime(Date expectedEndTime) {
        this.expectedEndTime = expectedEndTime;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

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

    public Long getChargeDeptId() {
        return chargeDeptId;
    }

    public void setChargeDeptId(Long chargeDeptId) {
        this.chargeDeptId = chargeDeptId;
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
