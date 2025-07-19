package com.ruoyi.project.projectmanage.domain.queryandresponse;

import com.ruoyi.common.api.BasePageParam;

import java.util.List;

public class QueryProjectBaseAndUserRelationParam extends BasePageParam {
    private List<Long> projectBaseIdList;
    private List<Long> userIdList;

    public List<Long> getProjectBaseIdList() {
        return projectBaseIdList;
    }

    public void setProjectBaseIdList(List<Long> projectBaseIdList) {
        this.projectBaseIdList = projectBaseIdList;
    }

    public List<Long> getUserIdList() {
        return userIdList;
    }

    public void setUserIdList(List<Long> userIdList) {
        this.userIdList = userIdList;
    }
}
