package com.axzhengxin.dto;

import java.util.Date;

public class DinnerNum {
    private Integer id;

    private Integer dinnernum;

    private Date date;

    private String bak;

    private String bak1;

    private String bak2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDinnernum() {
        return dinnernum;
    }

    public void setDinnernum(Integer dinnernum) {
        this.dinnernum = dinnernum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak == null ? null : bak.trim();
    }

    public String getBak1() {
        return bak1;
    }

    public void setBak1(String bak1) {
        this.bak1 = bak1 == null ? null : bak1.trim();
    }

    public String getBak2() {
        return bak2;
    }

    public void setBak2(String bak2) {
        this.bak2 = bak2 == null ? null : bak2.trim();
    }
}