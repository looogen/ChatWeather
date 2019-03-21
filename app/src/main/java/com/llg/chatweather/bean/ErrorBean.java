package com.llg.chatweather.bean;

/**
 * create by loogen on 2019-3-21
 */
public class ErrorBean {
    /**
     * status : The location can not be found.
     * status_code : AP010010
     */
    private String status;
    private String status_code;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }
}
