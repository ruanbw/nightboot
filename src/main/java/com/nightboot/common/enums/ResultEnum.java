package com.nightboot.common.enums;

public enum ResultEnum {

    SUCCESS(0, "success"),
    FAIL(-1, "fail"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404,"not found");

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
