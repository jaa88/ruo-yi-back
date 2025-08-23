package com.ruoyi.project.system.domain.queryandresponse;

import java.util.List;

public class QuerySysFunctionAndUploadFileRelationParam {
    private String functionName;
    private List<Long> functionIdList;
    private Integer deleteFlag;

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public List<Long> getFunctionIdList() {
        return functionIdList;
    }

    public void setFunctionIdList(List<Long> functionIdList) {
        this.functionIdList = functionIdList;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
