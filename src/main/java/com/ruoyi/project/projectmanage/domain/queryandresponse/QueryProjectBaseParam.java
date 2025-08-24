package com.ruoyi.project.projectmanage.domain.queryandresponse;

import com.ruoyi.common.api.BasePageParam;

public class QueryProjectBaseParam extends BasePageParam {
    private String projectName;
    private Long projectBaseId;
    private Integer xiangMuLeiXing;
    private Integer jianSheXingZhi;
    private Integer muQianJieDuan;

    public Integer getXiangMuLeiXing() {
        return xiangMuLeiXing;
    }

    public void setXiangMuLeiXing(Integer xiangMuLeiXing) {
        this.xiangMuLeiXing = xiangMuLeiXing;
    }

    public Integer getJianSheXingZhi() {
        return jianSheXingZhi;
    }

    public void setJianSheXingZhi(Integer jianSheXingZhi) {
        this.jianSheXingZhi = jianSheXingZhi;
    }

    public Integer getMuQianJieDuan() {
        return muQianJieDuan;
    }

    public void setMuQianJieDuan(Integer muQianJieDuan) {
        this.muQianJieDuan = muQianJieDuan;
    }

    public Long getProjectBaseId() {
        return projectBaseId;
    }

    public void setProjectBaseId(Long projectBaseId) {
        this.projectBaseId = projectBaseId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
