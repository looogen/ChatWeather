package com.llg.chatweather.http;

public class ResultException extends RuntimeException {

    public ResultException(String msg, String code) {
        super(msg, new Throwable(code));
    }

}
