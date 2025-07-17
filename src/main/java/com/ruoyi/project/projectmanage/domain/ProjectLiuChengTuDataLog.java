package com.ruoyi.project.projectmanage.domain;

import java.util.Date;

/**
 * 流程图的数据，最新的数据也就是当前的数据，其他都是历史数据
 * */
public class ProjectLiuChengTuDataLog {
    private Long id;
    private Long projectBaseId;
    private Long projectLiuChengTuTemplateId;
    private String currentCellsJsonStr;
    private String lastCellsJsonStr;
    private Long operateUserId;
    private Date operateTime;

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

    public Long getProjectLiuChengTuTemplateId() {
        return projectLiuChengTuTemplateId;
    }

    public void setProjectLiuChengTuTemplateId(Long projectLiuChengTuTemplateId) {
        this.projectLiuChengTuTemplateId = projectLiuChengTuTemplateId;
    }

    public String getCurrentCellsJsonStr() {
        return currentCellsJsonStr;
    }

    public void setCurrentCellsJsonStr(String currentCellsJsonStr) {
        this.currentCellsJsonStr = currentCellsJsonStr;
    }

    public String getLastCellsJsonStr() {
        return lastCellsJsonStr;
    }

    public void setLastCellsJsonStr(String lastCellsJsonStr) {
        this.lastCellsJsonStr = lastCellsJsonStr;
    }

    public Long getOperateUserId() {
        return operateUserId;
    }

    public void setOperateUserId(Long operateUserId) {
        this.operateUserId = operateUserId;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
}
