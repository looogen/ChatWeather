package com.llg.chatweather.http;

public class ErrorResponse {
    /**
     * errcode : 100
     * errmsg : appid错误!
     */
    private int errcode;
    private String errmsg;

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrcode() {
        return errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }
}
