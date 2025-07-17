package com.ruoyi.project.projectmanage.domain.queryandresponse;

import com.ruoyi.common.api.BasePageParam;

public class QueryProjectLiuChengTuDataLogParam extends BasePageParam {
    private Long projectBaseId;
    private Long projectLiuChengTuTemplateId;

    public Long getProjectBaseId() {
        return projectBaseId;
    }

    public void setProjectBaseId(Long projectBaseId) {
        this.projectBaseId = projectBaseId;
    }

    public Long getProjectLiuChengTuTemplateId() {
        return projectLiuChengTuTemplateId;
    }

    public void setProjectLiuChengTuTemplateId(Long projectLiuChengTuTemplateId) {
        this.projectLiuChengTuTemplateId = projectLiuChengTuTemplateId;
    }
}
