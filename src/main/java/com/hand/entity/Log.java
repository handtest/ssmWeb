package com.hand.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Log implements Serializable {

    private Integer id;

    private Integer operationUserId;

    private String operationUsername;

    private String operationAddress;

    private String operationDescription;

    private String operationParam;

    private String operationIp;

    private Date operationDate;

    private List<Log> listLog;

    public List<Log> getListLog() {
        return listLog;
    }

    public void setListLog(List<Log> listLog) {
        this.listLog = listLog;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOperationUserId() {
        return operationUserId;
    }

    public void setOperationUserId(Integer operationUserId) {
        this.operationUserId = operationUserId;
    }

    public String getOperationUsername() {
        return operationUsername;
    }

    public void setOperationUsername(String operationUsername) {
        this.operationUsername = operationUsername == null ? null : operationUsername.trim();
    }

    public String getOperationAddress() {
        return operationAddress;
    }

    public void setOperationAddress(String operationAddress) {
        this.operationAddress = operationAddress == null ? null : operationAddress.trim();
    }

    public String getOperationDescription() {
        return operationDescription;
    }

    public void setOperationDescription(String operationDescription) {
        this.operationDescription = operationDescription == null ? null : operationDescription.trim();
    }

    public String getOperationParam() {
        return operationParam;
    }

    public void setOperationParam(String operationParam) {
        this.operationParam = operationParam == null ? null : operationParam.trim();
    }

    public String getOperationIp() {
        return operationIp;
    }

    public void setOperationIp(String operationIp) {
        this.operationIp = operationIp == null ? null : operationIp.trim();
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }
}