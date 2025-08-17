package com.ruoyi.project.projectmanage.domain.queryandresponse;

import com.ruoyi.common.api.BasePageParam;

import java.util.List;

public class QueryProjectLiuChengTuNodeTargetDeptRelationParam extends BasePageParam {
    private List<Long> projectBaseIdList;
    private Integer status;
    private Long chargeDeptId;
    private String taskName;

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
