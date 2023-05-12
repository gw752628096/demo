package com.spring.demo.msg;

public final class WebControlData {

    private final long serverTime;

    private final int error;

    private final String message;

    private WebControlData(long serverTime, int error, String message) {
        this.serverTime = serverTime;
        this.error = error;
        this.message = message;
    }

    public static WebControlData of(RetCode retCode) {
        return new WebControlData(System.currentTimeMillis(), retCode.code(), retCode.defaultMsg());
    }

    public static WebControlData of(int error, String message) {
        return new WebControlData(System.currentTimeMillis(), error, message);
    }

    public long getServerTime() {
        return serverTime;
    }

    public int getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
