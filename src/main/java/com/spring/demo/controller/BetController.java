package com.spring.demo.controller;

import com.spring.demo.annotation.VerifyLogin;
import com.spring.demo.enums.BetStatusEnum;
import com.spring.demo.header.Header;
import com.spring.demo.msg.WebResult;
import com.spring.demo.po.*;
import com.spring.demo.query.BossInfoQuery;
import com.spring.demo.query.DungeonsInfoQuery;
import com.spring.demo.query.GoodsInfoQuery;
import com.spring.demo.request.StartBetReq;
import com.spring.demo.service.BookmakerRecordService;
import com.spring.demo.service.BossInfoService;
import com.spring.demo.service.DungeonsInfoService;
import com.spring.demo.service.GoodsInfoService;
import com.spring.demo.vo.BetConfigVo;
import com.spring.demo.vo.BossInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@VerifyLogin
public class BetController {
    @Resource
    private DungeonsInfoService dungeonsInfoService;
    @Resource
    private BossInfoService bossInfoService;
    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    private BookmakerRecordService bookmakerRecordService;

    @GetMapping("/betStartPage")
    public String betStartPage(Model model) {
        List<DungeonsInfo> dungeonsInfoList = dungeonsInfoService
                .getListByCondition(new DungeonsInfoQuery());
        model.addAttribute("dungeonsList", dungeonsInfoList);

        BossInfoQuery bossInfoQuery = new BossInfoQuery();
        bossInfoQuery.setDungeonsId(1L);
        List<BossInfo> bossInfoList = bossInfoService.getListByCondition(bossInfoQuery);

        List<BossInfoVo> bossInfoVoList = bossInfoList.stream().map(bossInfo -> {
            BossInfoVo bossInfoVo = new BossInfoVo();
            bossInfoVo.setId(bossInfo.getId());
            bossInfoVo.setBossName(bossInfo.getBossName());
            bossInfoVo.setDungeonsId(bossInfo.getDungeonsId());

            GoodsInfoQuery goodsInfoQuery = new GoodsInfoQuery();
            goodsInfoQuery.setBossId(bossInfo.getId());
            List<GoodsInfo> goodsInfoList = goodsInfoService.getListByCondition(goodsInfoQuery);
            bossInfoVo.setGoodsList(goodsInfoList);
            return bossInfoVo;
        }).collect(Collectors.toList());
        model.addAttribute("bossList", bossInfoVoList);
        return "betStartPage";
    }

    @GetMapping("/betStartSecondPage")
    public String betStartSecondPage(Model model) {
        List<DungeonsInfo> dungeonsInfoList = dungeonsInfoService
                .getListByCondition(new DungeonsInfoQuery());
        model.addAttribute("dungeonsList", dungeonsInfoList);

        BossInfoQuery bossInfoQuery = new BossInfoQuery();
        bossInfoQuery.setDungeonsId(1L);
        List<BossInfo> bossInfoList = bossInfoService.getListByCondition(bossInfoQuery);

        List<BossInfoVo> bossInfoVoList = bossInfoList.stream().map(bossInfo -> {
            BossInfoVo bossInfoVo = new BossInfoVo();
            bossInfoVo.setId(bossInfo.getId());
            bossInfoVo.setBossName(bossInfo.getBossName());
            bossInfoVo.setDungeonsId(bossInfo.getDungeonsId());

            GoodsInfoQuery goodsInfoQuery = new GoodsInfoQuery();
            goodsInfoQuery.setBossId(bossInfo.getId());
            List<GoodsInfo> goodsInfoList = goodsInfoService.getListByCondition(goodsInfoQuery);
            bossInfoVo.setGoodsList(goodsInfoList);
            return bossInfoVo;
        }).collect(Collectors.toList());
        model.addAttribute("bossList", bossInfoVoList);
        return "betStartSecondPage";
    }

    @GetMapping("/firstBetStart")
    public WebResult<Integer> startBet(Header header, @RequestBody StartBetReq startBetReq) {
        Long userId = header.getUserId();
        log.info("userId={}", userId);

        Long dungeonsId = startBetReq.getDungeonsId();
        String maxMoney = startBetReq.getMaxMoney();

        BookmakerRecord bookmakerRecord = new BookmakerRecord();
        bookmakerRecord.setDungeonsId(dungeonsId);
        bookmakerRecord.setBookmaker(userId);
        bookmakerRecord.setMaxMoney(maxMoney);
        bookmakerRecord.setBetStatus(BetStatusEnum.INIT.getStatus());
        bookmakerRecord.setCreateTime(new Date());
        bookmakerRecord.setCreateTime(new Date());
        bookmakerRecord.setDelFlag(0);

        int bookMakerId = bookmakerRecordService.saveSelective(bookmakerRecord);

        List<BetConfigVo> betConfigList = startBetReq.getBetConfigList();
        for (BetConfigVo betConfigVo : betConfigList) {
            BossBetConfig bossBetConfig = new BossBetConfig();
            bossBetConfig.setActivityId();
        }



        return WebResult.of(bookMakerId);
    }
}
