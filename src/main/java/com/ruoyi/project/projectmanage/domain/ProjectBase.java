package com.ruoyi.project.projectmanage.domain;

import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysUser;

import java.util.Date;
import java.util.List;

/*
* 模型基础类
* */
public class ProjectBase {
    private Long id;
    private String projectName;
    private Long currentLiuChengTuDataLogId;
    private Date createTime;
    private Long projectChargeUserId;
    private Long updateUserId;
    private Date updateTime;
    private String remark;
    private Integer deleteFlag;
    private String cellsJsonStr;
    private List<Long> canEditProjectDeptIdList;
    private List<SysDept> canEditProjectDeptList;
    //节点关系
    private List<ProjectLiuChengTuNodeTargetUserRelation> relationList;
    //目录配置
    private String contentsSetStr;

    public String getContentsSetStr() {
        return contentsSetStr;
    }

    public void setContentsSetStr(String contentsSetStr) {
        this.contentsSetStr = contentsSetStr;
    }

    public List<ProjectLiuChengTuNodeTargetUserRelation> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<ProjectLiuChengTuNodeTargetUserRelation> relationList) {
        this.relationList = relationList;
    }

    public List<Long> getCanEditProjectDeptIdList() {
        return canEditProjectDeptIdList;
    }

    public void setCanEditProjectDeptIdList(List<Long> canEditProjectDeptIdList) {
        this.canEditProjectDeptIdList = canEditProjectDeptIdList;
    }

    public List<SysDept> getCanEditProjectDeptList() {
        return canEditProjectDeptList;
    }

    public void setCanEditProjectDeptList(List<SysDept> canEditProjectDeptList) {
        this.canEditProjectDeptList = canEditProjectDeptList;
    }

    public String getCellsJsonStr() {
        return cellsJsonStr;
    }

    public void setCellsJsonStr(String cellsJsonStr) {
        this.cellsJsonStr = cellsJsonStr;
    }


    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Long getCurrentLiuChengTuDataLogId() {
        return currentLiuChengTuDataLogId;
    }

    public void setCurrentLiuChengTuDataLogId(Long currentLiuChengTuDataLogId) {
        this.currentLiuChengTuDataLogId = currentLiuChengTuDataLogId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getProjectChargeUserId() {
        return projectChargeUserId;
    }

    public void setProjectChargeUserId(Long projectChargeUserId) {
        this.projectChargeUserId = projectChargeUserId;
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
