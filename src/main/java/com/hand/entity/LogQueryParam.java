package com.hand.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by hanlu on 2017/7/19.
 */
public class LogQueryParam {
    private String userName;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date afterDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date beforeDate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = "%"+userName+"%";
    }

    public Date getAfterDate() {
        return afterDate;
    }

    public void setAfterDate(Date afterDate) {
        this.afterDate = afterDate;
    }

    public Date getBeforeDate() {
        return beforeDate;
    }

    public void setBeforeDate(Date beforeDate) {
        this.beforeDate = beforeDate;
    }
}
