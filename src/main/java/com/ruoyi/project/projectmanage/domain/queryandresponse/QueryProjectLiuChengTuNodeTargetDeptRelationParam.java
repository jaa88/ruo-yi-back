package com.ruoyi.project.projectmanage.domain.queryandresponse;

import com.ruoyi.common.api.BasePageParam;

import java.util.List;

public class QueryProjectLiuChengTuNodeTargetDeptRelationParam extends BasePageParam {
    private List<Long> projectBaseIdList;
    private Integer status;

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
