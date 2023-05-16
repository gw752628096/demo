package com.spring.demo.enums;

public enum BetStatusEnum {
    INIT("1", "初始化"),
    BET_START("2", "开盘中"),
    BET_END("3", "已结束");
    private String status;
    private String desc;

    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    BetStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
