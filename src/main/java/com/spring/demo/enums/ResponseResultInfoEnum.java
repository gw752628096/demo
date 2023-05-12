package com.spring.demo.enums;

import com.spring.demo.msg.ResponseCode;

public enum ResponseResultInfoEnum implements ResponseCode {
    NO_ERROR(0, "操作成功"),
    FAILED(1, "操作失败"),
    SYSTEM_ERROR(-2, "系统错误"),
    CHECK_ERROR(-4, "数据验证错误"),
    NO_EXIST_ERROR(-5, "数据不存在"),
    ERROR_AUTH_TOKEN(-2001, "token失效"),

    REGISTER_ALREADY(-1001, "当前用户已注册，请直接登录"),

    NICK_NAME_ERROR(-1002, "当前昵称已占用，请重新注册"),

    NOT_REGISTER_YET(-1003, "当前用户未注册，请注册后登录"),

    LOGIN_INFO_ERROR(-1004, "用户名或密码错误"),


    ;
    private Integer code;
    private String description;

    ResponseResultInfoEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isSuccess() {
        return this.code.equals(NO_ERROR.getCode());
    }
}
