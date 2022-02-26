package com.spring.demo.enums;

import java.util.HashMap;
import java.util.Map;

public enum GoodEnum {
    GOOD1(1, "崩坏腕甲"),
    GOOD2(2, "嚎风护腕"),
    GOOD3(3, "黑暗秘密编年史"),
    GOOD4(4, "回春护腕"),
    GOOD5(5, "狂暴镣铐"),
    GOOD6(6, "染血肩铠"),
    GOOD7(7, "神圣精金护腕"),
    GOOD8(8, "寻路者护腕"),
    GOOD9(9, "殉难护腕"),
    GOOD10(10, "止水长靴"),
    GOOD11(11, "致命腕甲"),
    GOOD12(12, "追踪匕首"),
    GOOD13(13, "阿加多的腰带"),
    GOOD14(14, "安纳塞隆的束缚"),
    GOOD15(15, "不败的意志"),
    GOOD16(16, "大主教的便鞋"),
    GOOD17(17, "恶行之刃"),
    GOOD18(18, "疾步长靴"),
    GOOD19(19, "金色治疗胸甲"),
    GOOD20(20, "狂野之柱"),
    GOOD21(21, "魔化皮质便鞋"),
    GOOD22(22, "闪光的钢铁护肩"),
    GOOD23(23, "圣光壁垒"),
    GOOD24(24, "怨怒护肩"),
    GOOD25(25, "安格莉丝塔的腰带"),
    GOOD26(26, "刀锋怒火护肩"),
    GOOD27(27, "沸腾怒火腰带"),
    GOOD28(28, "黑羽之靴"),
    GOOD29(29, "卡兹洛加之心"),
    GOOD30(30, "蓝色山羊皮软靴"),
    GOOD31(31, "赎罪之锤"),
    GOOD32(32, "峡谷猎手腰带"),
    GOOD33(33, "新月腰带"),
    GOOD34(34, "驯兽者护肩"),
    GOOD35(35, "阳炎锁链护腿"),
    GOOD36(36, "元素导能护腿"),
    GOOD37(37, "防御者的荣耀"),
    GOOD38(38, "罗德里格的雨衣"),
    GOOD39(39, "秘密商人长裤"),
    GOOD40(40, "弯弓护腿"),
    GOOD41(41, "无尽的痛苦"),
    GOOD42(42, "希望束带"),
    GOOD43(43, "阿古斯的使徒"),
    GOOD44(44, "安东尼达斯的专注纹章盾"),
    GOOD45(45, "混乱风暴"),
    GOOD46(46, "净化节杖"),
    GOOD47(47, "救世胸恺"),
    GOOD48(48, "狂热追击锁甲"),
    GOOD49(49, "罗宁之袍"),
    GOOD50(50, "闪击强弓"),
    GOOD51(51, "无尽怒气腿甲"),
    GOOD52(52, "午夜胸甲"),
    GOOD53(53, "永恒护腿"),
    GOOD54(54, "灾变之刃");

    private int goodId;
    private String goodName;

    public static final Map<Integer, GoodEnum> init = init();

    GoodEnum(int goodId, String goodName) {
        this.goodId = goodId;
        this.goodName = goodName;
    }

    public int getGoodId() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    private static Map<Integer, GoodEnum> init() {
        Map<Integer, GoodEnum> result = new HashMap<>();
        for (GoodEnum value : GoodEnum.values()) {
            result.put(value.getGoodId(), value);
        }
        return result;
    }
}
