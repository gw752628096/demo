package com.spring.demo.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateTime;
import com.spring.demo.po.BetRecord;
import com.spring.demo.po.BossCalculateRecord;
import com.spring.demo.po.UserInfo;
import com.spring.demo.query.BetRecordQuery;
import com.spring.demo.query.BossCalculateRecordQuery;
import com.spring.demo.query.UserInfoQuery;
import com.spring.demo.request.CalculateBossReq;
import com.spring.demo.request.CalculateTotalReq;
import com.spring.demo.request.Goods;
import com.spring.demo.response.CalculateTotalResponse;
import com.spring.demo.service.BetRecordService;
import com.spring.demo.service.BossCalculateRecordService;
import com.spring.demo.service.UserInfoService;
import com.spring.demo.vo.BetRecordVo;
import com.spring.demo.vo.TotalBetRecordVo;
import com.spring.demo.vo.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Controller
public class ManageController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private BetRecordService betRecordService;
    @Resource
    private BossCalculateRecordService bossCalculateRecordService;

    /**
     * 开盘
     *
     * @param bookmaker
     * @return
     */
    @ResponseBody
    @PostMapping("/start")
    public String start(@RequestBody String bookmaker) {
        String currentActivityId = stringRedisTemplate.opsForValue().get(bookmaker + ".wow.activityId");
        String activitySwitch = stringRedisTemplate.opsForValue().get(currentActivityId + ".switch");
        if ("on".equals(activitySwitch)) {
            return "未结束下注不能重新开盘";
        }

        Long activityId = stringRedisTemplate.opsForValue().increment(bookmaker + ".wow.activityId");
        stringRedisTemplate.opsForValue().set(activityId + ".switch", "on", 1, TimeUnit.DAYS);
        log.info("{}开盘 activityId={}", bookmaker, activityId);
        return "竞猜链接：http://82.157.188.185/orderPage2?bookmaker=" + bookmaker;
    }

    /**
     * 开始压注
     *
     * @param bookmaker
     * @return
     */
    @ResponseBody
    @PostMapping("/startBet")
    public String startBet(@RequestBody String bookmaker) {
        String activityId = stringRedisTemplate.opsForValue().get(bookmaker + ".wow.activityId");
        if (NumberUtils.toLong(activityId, 0) <= 0) {
            return "请先开盘";
        }

        stringRedisTemplate.opsForValue().set(activityId + ".switch", "on", 1, TimeUnit.DAYS);
        log.info("{}开始压注 activityId={}", bookmaker, activityId);
        return "开始下注";
    }

    /**
     * 停止压注
     *
     * @param bookmaker
     * @return
     */
    @ResponseBody
    @PostMapping("/endBet")
    public String endBet(@RequestBody String bookmaker) {
        String activityId = stringRedisTemplate.opsForValue().get(bookmaker + ".wow.activityId");
        if (NumberUtils.toLong(activityId, 0) <= 0) {
            return "请先开盘";
        }

        stringRedisTemplate.opsForValue().set(activityId + ".switch", "off", 1, TimeUnit.DAYS);
        log.info("{}封盘 activityId={}", bookmaker, activityId);
        return "结束下注";
    }

    @ResponseBody
    @PostMapping("/calculateBoss")
    public String calculateBoss(@RequestBody CalculateBossReq calculateBossReq) {
        Integer bossId = calculateBossReq.getBossId();
        String bookmaker = calculateBossReq.getBookmaker();
        String activityId = stringRedisTemplate.opsForValue().get(bookmaker + ".wow.activityId");

        String activitySwitch = stringRedisTemplate.opsForValue().get(activityId + ".switch");
        if ("on".equals(activitySwitch)) {
            return "未结束下注无法结算";
        }

        BossCalculateRecordQuery query = new BossCalculateRecordQuery();
        query.setBookmaker(bookmaker);
        query.setActivityId(activityId);
        query.setBossId(bossId);
        query.setDelFlag(0);
        List<BossCalculateRecord> bossCalculateRecordList = bossCalculateRecordService.getListByCondition(query);
        if (CollectionUtil.isNotEmpty(bossCalculateRecordList)) {
            return "当前boss已结算！";
        }

        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey("calculate.record." + bookmaker + "." + activityId + "." + bossId))) {
            return "当前boss已结算！";
        }

        Set<Integer> calculateGoods = calculateBossReq.getGoodsList().stream().map(Goods::getId).collect(Collectors.toSet());
        BetRecordQuery betRecordQuery = new BetRecordQuery();
        betRecordQuery.setActivityId(activityId);
        betRecordQuery.setBookmaker(bookmaker);
        betRecordQuery.setBossId(bossId);
        List<BetRecord> betRecordList = betRecordService.getListByCondition(betRecordQuery);

        if (CollectionUtil.isEmpty(betRecordList)) {
            return "无可结算记录";
        }

        for (BetRecord betRecord : betRecordList) {
            int goodsId = betRecord.getGoodsId();
            if (!calculateGoods.contains(goodsId)) {
                //没有压中
                int betMoney = NumberUtils.toInt(betRecord.getMoney());
                betRecord.setCalculateMoney(String.valueOf(-betMoney));
                betRecordService.updateByPrimaryKeySelective(betRecord);
                continue;
            }

            // TODO: 2023/5/12 结算比例
            //压中
            int betMoney = NumberUtils.toInt(betRecord.getMoney());
            betRecord.setCalculateMoney(String.valueOf((betMoney * 3)));
            betRecordService.updateByPrimaryKeySelective(betRecord);
        }

        saveCalculateRecord(calculateBossReq, bossId, bookmaker, activityId);

        return "结算成功，可打开下注开关";
    }

    @ResponseBody
    @PostMapping("/calculateTotal")
    public CalculateTotalResponse calculateTotal(@RequestBody CalculateTotalReq calculateTotalReq) {
        String bookmaker = calculateTotalReq.getBookmaker();
        String activityId = stringRedisTemplate.opsForValue().get(bookmaker + ".wow.activityId");

        String activitySwitch = stringRedisTemplate.opsForValue().get(activityId + ".switch");
        if ("on".equals(activitySwitch)) {
            CalculateTotalResponse calculateTotalResponse = new CalculateTotalResponse();
            calculateTotalResponse.setCode(-1);
            calculateTotalResponse.setMessage("未结束下注无法结算");
            return calculateTotalResponse;
        }

        BetRecordQuery betRecordQuery = new BetRecordQuery();
        betRecordQuery.setActivityId(activityId);
        betRecordQuery.setBookmaker(bookmaker);
        List<BetRecord> betRecordList = betRecordService.getListByCondition(betRecordQuery);

        if (CollectionUtil.isEmpty(betRecordList)) {
            CalculateTotalResponse calculateTotalResponse = new CalculateTotalResponse();
            calculateTotalResponse.setCode(-1);
            calculateTotalResponse.setMessage("未结束下注无法结算");
            return calculateTotalResponse;
        }

        Map<Long, String> nickNameMap = new HashMap<>();
        Map<String, Integer> result = new HashMap<>();
        Integer total = 0;
        for (BetRecord betRecord : betRecordList) {
            Long userId = betRecord.getUserId();
            if (!nickNameMap.containsKey(userId)) {
                UserInfo userInfo = userInfoService.getByPrimaryKey(userId);
                nickNameMap.put(userInfo.getId(), userInfo.getNickName());
            }

            String nickName = nickNameMap.get(userId);
            if (result.containsKey(nickName)) {
                Integer calculateMoney = result.get(nickName) + NumberUtils.toInt(betRecord.getCalculateMoney());
                result.put(nickName, calculateMoney);
            } else {
                result.put(nickName, NumberUtils.toInt(betRecord.getCalculateMoney()));
            }
        }

        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            total += entry.getValue();
        }

        result.put("总计", total);
        List<TotalBetRecordVo> totalRecordList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : result.entrySet()) {
            TotalBetRecordVo totalBetRecordVo = new TotalBetRecordVo();
            totalBetRecordVo.setNickName(entry.getKey());
            totalBetRecordVo.setCalculateMoney(entry.getValue());
            totalRecordList.add(totalBetRecordVo);
        }

        CalculateTotalResponse calculateTotalResponse = new CalculateTotalResponse();
        calculateTotalResponse.setCode(0);
        calculateTotalResponse.setMessage("成功");
        calculateTotalResponse.setTotalBetRecordVoList(totalRecordList);
        return calculateTotalResponse;
    }

    private void saveCalculateRecord(CalculateBossReq calculateBossReq, Integer bossId, String bookmaker, String activityId) {
        stringRedisTemplate.opsForValue().set("calculate.record." + bookmaker + "." + activityId + "." + bossId, "true", 1, TimeUnit.DAYS);

        for (Goods goods : calculateBossReq.getGoodsList()) {
            BossCalculateRecord record = new BossCalculateRecord();
            record.setBookmaker(bookmaker);
            record.setActivityId(activityId);
            record.setBossId(bossId);
            record.setGoodsId(goods.getId());
            record.setCreateTime(new Date());
            record.setUpdateTime(new Date());
            record.setDelFlag(0);
            bossCalculateRecordService.saveSelective(record);
        }
    }

    /**
     * 竞猜页面
     *
     * @param model
     * @param bookmaker
     * @return
     */
    @GetMapping("/orderPage")
    public String toOrderPage(Model model, String bookmaker) {
        String activityId = stringRedisTemplate.opsForValue().get(bookmaker + ".wow.activityId");
        model.addAttribute("activityId", activityId);
        model.addAttribute("bookmaker", bookmaker);

        return "orderPage2";
    }

    /**
     * 竞猜页面
     *
     * @param model
     * @param bookmaker
     * @return
     */
    @GetMapping("/orderPage2")
    public String toOrderPage2(Model model, String bookmaker) {
        String activityId = stringRedisTemplate.opsForValue().get(bookmaker + ".wow.activityId");
        model.addAttribute("activityId", activityId);
        model.addAttribute("bookmaker", bookmaker);

        UserInfoQuery query = new UserInfoQuery();
        query.setDelFlag(0);
        List<UserInfo> list = userInfoService.getListByCondition(query);
        List<UserInfoVo> result = list.stream()
                .map(userInfo -> BeanUtil.copyProperties(userInfo, UserInfoVo.class))
                .collect(Collectors.toList());

        model.addAttribute("allUserList", result);

        return "orderPage2";
    }

    /**
     * 管理页面
     *
     * @param model
     * @param bookmaker
     * @return
     */
    @GetMapping("/managePage")
    public String toManagePage(Model model, String bookmaker) {
        String activityId = stringRedisTemplate.opsForValue().get(bookmaker + ".wow.activityId");
        if (NumberUtils.toLong(activityId, 0) <= 0) {
            return null;
        }

        BetRecordQuery betRecordQuery = new BetRecordQuery();
        betRecordQuery.setActivityId(activityId);
        betRecordQuery.setBookmaker(bookmaker);
        List<BetRecord> betRecordList = betRecordService.getListByCondition(betRecordQuery);

        Map<Long, String> userNickNameMap = new HashMap<>();
        List<BetRecordVo> list = betRecordList.stream().map(betRecord -> {
            BetRecordVo betRecordVo = new BetRecordVo();

//            betRecordVo.setBossName(BossEnum.init.get(betRecord.getBossId()).getBossName());
//            betRecordVo.setGoodName(GoodEnum.init.get(betRecord.getGoodsId()).getGoodName());
            betRecordVo.setMoney(betRecord.getMoney());
            betRecordVo.setTime(DateTime.of(betRecord.getCreateTime()).toString("yyyy-MM-dd HH:mm:ss"));
            betRecordVo.setCalculateMoney(betRecord.getCalculateMoney());
            Long userId = betRecord.getUserId();
            if (userNickNameMap.get(userId) == null) {
                UserInfo userInfo = userInfoService.getByPrimaryKey(userId);
                userNickNameMap.putIfAbsent(userInfo.getId(), userInfo.getNickName());
                betRecordVo.setNickName(userInfo.getNickName());
            } else {
                betRecordVo.setNickName(userNickNameMap.get(userId));
            }
            return betRecordVo;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);
        return "managePage";
    }
}
