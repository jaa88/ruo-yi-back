package com.ruoyi.project.projectmanage.domain;

import com.ruoyi.project.system.domain.SysUser;

import java.util.Date;
import java.util.List;

/*
* 模型base 对应的node
* */
public class ProjectLiuChengTuNode {
    private Long id;
    private Long projectBaseId;
    private String graphNodeId;
    private String dataJsonStr;
    private Long updateUserId;
    private Date updateTime;
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getGraphNodeId() {
        return graphNodeId;
    }

    public void setGraphNodeId(String graphNodeId) {
        this.graphNodeId = graphNodeId;
    }

    public String getDataJsonStr() {
        return dataJsonStr;
    }

    public void setDataJsonStr(String dataJsonStr) {
        this.dataJsonStr = dataJsonStr;
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
