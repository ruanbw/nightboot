package com.nightboot.common.enums;

public enum ResultEnum {

    SUCCESS(200, "success"),
    FAIL(-1, "fail"),
    UNAUTHORIZED(401, "unauthorized");

    private Integer code;
    private String msg;

    ResultEnum(Integer i, String success) {
        this.code = i;
        this.msg = success;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
