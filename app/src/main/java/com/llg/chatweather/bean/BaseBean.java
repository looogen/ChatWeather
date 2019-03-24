package com.llg.chatweather.bean;

import java.util.List;

/**
 * create by loogen on 2019-3-24
 */
public class BaseBean<T> {
    private List<T> results;
    private String status;
    private String status_code;

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

    public String getStatus_code() {
        return status_code;
    }

    public void setStatus_code(String status_code) {
        this.status_code = status_code;
    }

}
