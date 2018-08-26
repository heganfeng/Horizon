package com.gouge.base;

/**
 * Created by Administrator on 2018/8/7.
 */
public  class HttpException extends RuntimeException{

    String code = "-1";

    public HttpException(String message) {
        super(message);
    }

    public HttpException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}