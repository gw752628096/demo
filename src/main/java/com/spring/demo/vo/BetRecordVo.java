package com.spring.demo.vo;

public class BetRecordVo {
    private String bossName;
    private String goodName;
    private String money;
    private String time;
    private String nickName;
    private String calculateMoney;

    public BetRecordVo() {
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCalculateMoney() {
        return calculateMoney;
    }

    public void setCalculateMoney(String calculateMoney) {
        this.calculateMoney = calculateMoney;
    }
}
