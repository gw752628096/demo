package com.spring.demo.enums;

import java.util.HashMap;
import java.util.Map;

public enum BossEnum {
    BOSS1(1, "雷基·冬寒"),
    BOSS2(2, "安纳塞隆"),
    BOSS3(3, "卡兹洛加"),
    BOSS4(4, "阿兹加洛"),
    BOSS5(5, "阿克蒙德");

    private int bossId;
    private String bossName;

    public static final Map<Integer, BossEnum> init = init();

    BossEnum(int bossId, String bossName) {
        this.bossId = bossId;
        this.bossName = bossName;
    }

    public int getBossId() {
        return bossId;
    }

    public void setBossId(int bossId) {
        this.bossId = bossId;
    }

    public String getBossName() {
        return bossName;
    }

    public void setBossName(String bossName) {
        this.bossName = bossName;
    }

    private static Map<Integer, BossEnum> init() {
        Map<Integer, BossEnum> result = new HashMap<>();
        for (BossEnum value : BossEnum.values()) {
            result.put(value.getBossId(), value);
        }
        return result;
    }
}
