package com.spring.demo.msg;

import com.spring.demo.enums.ResponseResultInfoEnum;

import javax.annotation.Nullable;

public final class WebResult<T> {

    private final WebControlData control;

    private final T data;

    private WebResult(WebControlData control, T data) {
        this.control = control;
        this.data = data;
    }

    /**
     * 返回一个默认成功对象， data为null
     */
    public static <R> WebResult<R> of() {
        return new WebResult<>(WebControlData.of(ResponseResultInfoEnum.NO_ERROR.getCode(), ResponseResultInfoEnum.NO_ERROR.getDescription()), null);
    }

    /**
     * 返回一个默认成功对象
     */
    public static <R> WebResult<R> of(R data) {
        return new WebResult<>(WebControlData.of(ResponseResultInfoEnum.NO_ERROR.getCode(), ResponseResultInfoEnum.NO_ERROR.getDescription()), data);
    }

    public static <R> WebResult<R> of(WebControlData control, @Nullable R data) {
        return new WebResult<>(control, data);
    }

    public static <R> WebResult<R> of(WebControlData control) {
        return new WebResult<>(control, null);
    }

    public static <R> WebResult<R> of(ResponseCode rspEnum) {
        return new WebResult<>(WebControlData.of(rspEnum.getCode(), rspEnum.getDescription()), null);
    }

    public static <R> WebResult<R> of(int error, String message) {
        return new WebResult<>(WebControlData.of(error, message), null);
    }

    public static <R> WebResult<R> of(int error, String message, R data) {
        return new WebResult<>(WebControlData.of(error, message), data);
    }

    public WebControlData getControl() {
        return control;
    }

    public T getData() {
        return data;
    }

    public boolean checkSuccess() {
        return this.control.getError() == ResponseResultInfoEnum.NO_ERROR.getCode();
    }
}
