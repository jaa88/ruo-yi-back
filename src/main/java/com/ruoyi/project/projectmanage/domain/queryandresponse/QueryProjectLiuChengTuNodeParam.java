package com.ruoyi.project.projectmanage.domain.queryandresponse;

import com.ruoyi.common.api.BasePageParam;

/**
 *  查询用户列表
 */
public class QueryProjectLiuChengTuNodeParam extends BasePageParam {
    private Long projectBaseId;

    public Long getProjectBaseId() {
        return projectBaseId;
    }

    public void setProjectBaseId(Long projectBaseId) {
        this.projectBaseId = projectBaseId;
    }
}
