package com.axzhengxin.dto;

import java.util.Date;

public class SendMailLog {
    private Integer id;

    private Date sendTime;

    private String sendUrl;

    private String sandName;

    private String fromMail;

    private String sandMail;

    private String isSuss;

    private Date openTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl == null ? null : sendUrl.trim();
    }

    public String getSandName() {
        return sandName;
    }

    public void setSandName(String sandName) {
        this.sandName = sandName == null ? null : sandName.trim();
    }

    public String getFromMail() {
        return fromMail;
    }

    public void setFromMail(String fromMail) {
        this.fromMail = fromMail == null ? null : fromMail.trim();
    }

    public String getSandMail() {
        return sandMail;
    }

    public void setSandMail(String sandMail) {
        this.sandMail = sandMail == null ? null : sandMail.trim();
    }

    public String getIsSuss() {
        return isSuss;
    }

    public void setIsSuss(String isSuss) {
        this.isSuss = isSuss == null ? null : isSuss.trim();
    }

    public Date getOpenTime() {
        return openTime;
    }

    public void setOpenTime(Date openTime) {
        this.openTime = openTime;
    }
}