package com.spring.demo.controller;

import com.spring.demo.po.BetRecord;
import com.spring.demo.request.ConfirmReq;
import com.spring.demo.request.Goods;
import com.spring.demo.service.BetRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Controller
public class ConfirmController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private BetRecordService betRecordService;

    @ResponseBody
    @PostMapping("/confirm")
    public String confirm(@RequestBody ConfirmReq confirmReq) {
        String activityId = stringRedisTemplate.opsForValue().get(confirmReq.getBookmaker() + ".wow.activityId");
        long applyActivityId = NumberUtils.toLong(confirmReq.getActivityId(), -1);
        long currentActivityId = NumberUtils.toLong(activityId, 0L);
        if (applyActivityId < currentActivityId) {
            return "当前已封盘";
        }

        Long orderUserId = confirmReq.getOrderUserId();
        if (null == orderUserId || orderUserId <= 0) {
            orderUserId = confirmReq.getUserId();
        }
        int chooseBoss = confirmReq.getChooseBoss();
        String bookmaker = confirmReq.getBookmaker();

        Goods one = confirmReq.getOne();
        Goods two = confirmReq.getTwo();
        Goods three = confirmReq.getThree();

        saveBetRecord(bookmaker, applyActivityId, orderUserId, chooseBoss, one, two, three);

        // TODO: 2022/2/25 返回结算页面地址
        return "success";
    }

    private void saveBetRecord(String bookmaker, long applyActivityId, Long userId, int chooseBoss, Goods one, Goods two, Goods three) {
        if (one != null && one.getMoney() != null && one.getMoney() > 0) {
            BetRecord betRecord1 = new BetRecord();
            betRecord1.setUserId(userId);
            betRecord1.setBookmaker(bookmaker);
            betRecord1.setActivityId(String.valueOf(applyActivityId));
            betRecord1.setBossId(chooseBoss);
            betRecord1.setGoodsId(one.getId());
            betRecord1.setMoney(one.getMoney().toString());
            betRecord1.setCreateTime(new Date());
            betRecord1.setDelFlag(0);
            betRecordService.saveSelective(betRecord1);
        }

        if (two != null && two.getMoney() != null && two.getMoney() > 0) {
            BetRecord betRecord2 = new BetRecord();
            betRecord2.setUserId(userId);
            betRecord2.setBookmaker(bookmaker);
            betRecord2.setActivityId(String.valueOf(applyActivityId));
            betRecord2.setBossId(chooseBoss);
            betRecord2.setGoodsId(two.getId());
            betRecord2.setMoney(two.getMoney().toString());
            betRecord2.setCreateTime(new Date());
            betRecord2.setDelFlag(0);
            betRecordService.saveSelective(betRecord2);
        }

        if (three != null && three.getMoney() != null && three.getMoney() > 0) {
            BetRecord betRecord3 = new BetRecord();
            betRecord3.setUserId(userId);
            betRecord3.setBookmaker(bookmaker);
            betRecord3.setActivityId(String.valueOf(applyActivityId));
            betRecord3.setBossId(chooseBoss);
            betRecord3.setGoodsId(three.getId());
            betRecord3.setMoney(three.getMoney().toString());
            betRecord3.setCreateTime(new Date());
            betRecord3.setDelFlag(0);
            betRecordService.saveSelective(betRecord3);
        }
    }
}
