package com.ruoyi.project.system.domain;

import com.ruoyi.common.xss.Xss;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 带router的通知公告
 *
 */
public class RouterSysNotice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private String noticeContent;
    private String routerLink;

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(String routerLink) {
        this.routerLink = routerLink;
    }
}
