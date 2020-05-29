package com.llg.chatweather.data.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * create by loogen on 2019-3-24
 *
 * 数据请求基类
 */
public class BaseBean<T> {
    private List<T> results;
    private String status;
    @SerializedName("status_code")
    private String statusCode;

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
