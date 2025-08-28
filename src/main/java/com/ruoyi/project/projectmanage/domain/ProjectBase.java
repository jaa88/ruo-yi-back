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
    private Long updateUserId;
    private Date updateTime;
    private String remark;
    private Integer deleteFlag;
    private String cellsJsonStr;
    private List<Long> deptIdList;
    private List<SysDept> deptList;
    //节点关系
    private List<ProjectLiuChengTuNodeTargetDeptRelation> relationList;
    //目录配置
    private String contentsSetStr;
    //项目代码
    private String xiangMuDaiMa;
    //目前阶段
    private Integer muQianJieDuan;
    //项目地址
    private String xiangMuDiZhi;
    //项目类型
    private Integer xiangMuLeiXing;
    //建设性质
    private Integer jianSheXingZhi;
    //总投资
    private String zongTouZi;
    //拟开工日期
    private Date niKaiGongRiQi;
    //拟完工日期
    private Date niWanGongRiQi;
    //建设单位
    private String jianSheDanWei;
    //项目负责人
    private String xiangMuFuZeRen;
    //联系方式
    private String lianXiFangShi;
    //主要建设内容
    private String zhuYaoJianSheNeiRong;
    //你新增用地情况
    private String niXinZenYongDiQingKuang;

    //20250828
    //文件 ，上述两个list的拼接，以*@*分割
    private String originFileNameListStr;
    private String curFilePathAndNameListStr;

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

    public String getZhuYaoJianSheNeiRong() {
        return zhuYaoJianSheNeiRong;
    }

    public void setZhuYaoJianSheNeiRong(String zhuYaoJianSheNeiRong) {
        this.zhuYaoJianSheNeiRong = zhuYaoJianSheNeiRong;
    }

    public String getNiXinZenYongDiQingKuang() {
        return niXinZenYongDiQingKuang;
    }

    public void setNiXinZenYongDiQingKuang(String niXinZenYongDiQingKuang) {
        this.niXinZenYongDiQingKuang = niXinZenYongDiQingKuang;
    }

    public String getXiangMuDaiMa() {
        return xiangMuDaiMa;
    }

    public void setXiangMuDaiMa(String xiangMuDaiMa) {
        this.xiangMuDaiMa = xiangMuDaiMa;
    }

    public String getXiangMuDiZhi() {
        return xiangMuDiZhi;
    }

    public void setXiangMuDiZhi(String xiangMuDiZhi) {
        this.xiangMuDiZhi = xiangMuDiZhi;
    }

    public String getZongTouZi() {
        return zongTouZi;
    }

    public void setZongTouZi(String zongTouZi) {
        this.zongTouZi = zongTouZi;
    }

    public String getJianSheDanWei() {
        return jianSheDanWei;
    }

    public void setJianSheDanWei(String jianSheDanWei) {
        this.jianSheDanWei = jianSheDanWei;
    }

    public String getXiangMuFuZeRen() {
        return xiangMuFuZeRen;
    }

    public void setXiangMuFuZeRen(String xiangMuFuZeRen) {
        this.xiangMuFuZeRen = xiangMuFuZeRen;
    }

    public String getLianXiFangShi() {
        return lianXiFangShi;
    }

    public void setLianXiFangShi(String lianXiFangShi) {
        this.lianXiFangShi = lianXiFangShi;
    }

    public String getContentsSetStr() {
        return contentsSetStr;
    }

    public void setContentsSetStr(String contentsSetStr) {
        this.contentsSetStr = contentsSetStr;
    }

    public List<ProjectLiuChengTuNodeTargetDeptRelation> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<ProjectLiuChengTuNodeTargetDeptRelation> relationList) {
        this.relationList = relationList;
    }

    public List<Long> getDeptIdList() {
        return deptIdList;
    }

    public void setDeptIdList(List<Long> deptIdList) {
        this.deptIdList = deptIdList;
    }

    public List<SysDept> getDeptList() {
        return deptList;
    }

    public void setDeptList(List<SysDept> deptList) {
        this.deptList = deptList;
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

    public Integer getMuQianJieDuan() {
        return muQianJieDuan;
    }

    public void setMuQianJieDuan(Integer muQianJieDuan) {
        this.muQianJieDuan = muQianJieDuan;
    }

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

    public Date getNiKaiGongRiQi() {
        return niKaiGongRiQi;
    }

    public void setNiKaiGongRiQi(Date niKaiGongRiQi) {
        this.niKaiGongRiQi = niKaiGongRiQi;
    }

    public Date getNiWanGongRiQi() {
        return niWanGongRiQi;
    }

    public void setNiWanGongRiQi(Date niWanGongRiQi) {
        this.niWanGongRiQi = niWanGongRiQi;
    }


}
