package com.llg.chatweather.data;

public class NetState {

    private String message;
    private boolean success = true;

    public NetState(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public NetState() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}