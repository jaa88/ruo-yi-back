package com.ruoyi.project.system.domain.queryandresponse;

import java.util.List;

public class DeleteAndInsertSysFunctionAndUploadFileRelationParam {
    private String functionName;
    private Long functionId;
    private List<Long> uploadFileIdList;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public List<Long> getUploadFileIdList() {
        return uploadFileIdList;
    }

    public void setUploadFileIdList(List<Long> uploadFileIdList) {
        this.uploadFileIdList = uploadFileIdList;
    }
}
