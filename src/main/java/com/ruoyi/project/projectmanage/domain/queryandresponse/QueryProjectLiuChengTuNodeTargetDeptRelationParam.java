package com.ruoyi.project.projectmanage.domain.queryandresponse;

import com.ruoyi.common.api.BasePageParam;

import java.util.List;

public class QueryProjectLiuChengTuNodeTargetDeptRelationParam extends BasePageParam {
    private List<Long> projectBaseIdList;
    private Integer status;
    private List<Integer> statusList;
    private Long chargeDeptId;
    private String taskName;
    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Long getChargeDeptId() {
        return chargeDeptId;
    }

    public void setChargeDeptId(Long chargeDeptId) {
        this.chargeDeptId = chargeDeptId;
    }

    public List<Long> getProjectBaseIdList() {
        return projectBaseIdList;
    }

    public void setProjectBaseIdList(List<Long> projectBaseIdList) {
        this.projectBaseIdList = projectBaseIdList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
