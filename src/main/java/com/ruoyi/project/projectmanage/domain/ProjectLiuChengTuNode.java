package com.ruoyi.project.projectmanage.domain;

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
    //节点内包含的的信息
    private Integer status;
    private String taskName;
    private Date startTime;
    private Date endTime;
    private Date expectedStartTime;
    private Date expectedEndTime;
    //文件
    private List<String> originFileNameList;
    private List<String> curFilePathAndNameList;
    //文件 ，上述两个list的拼接，以*@*分割
    private String originFileNameListStr;
    private String curFilePathAndNameListStr;

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

    public List<String> getOriginFileNameList() {
        return originFileNameList;
    }

    public void setOriginFileNameList(List<String> originFileNameList) {
        this.originFileNameList = originFileNameList;
    }

    public List<String> getCurFilePathAndNameList() {
        return curFilePathAndNameList;
    }

    public void setCurFilePathAndNameList(List<String> curFilePathAndNameList) {
        this.curFilePathAndNameList = curFilePathAndNameList;
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

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

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
