package com.spring.demo.controller;

import com.spring.demo.component.UserCookieComponent;
import com.spring.demo.enums.ResponseResultInfoEnum;
import com.spring.demo.msg.WebResult;
import com.spring.demo.po.UserInfo;
import com.spring.demo.request.LoginReq;
import com.spring.demo.request.RegisterReq;
import com.spring.demo.service.UserInfoService;
import com.spring.demo.utils.AuthTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("enter")
public class LoginController {
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserCookieComponent userCookieComponent;

    private static final Long EXP = 30 * 24 * 60 * 60 * 1000L;

    @ResponseBody
    @PostMapping("/register")
    public WebResult<String> register(@RequestBody RegisterReq registerReq) {
        UserInfo loginValueInfo = userInfoService.getByLoginValue(registerReq.getLoginValue());
        if (null != loginValueInfo) {
            return WebResult.of(ResponseResultInfoEnum.REGISTER_ALREADY);
        }

        UserInfo nickNameInfo = userInfoService.getByNickName(registerReq.getNickName());
        if (null != nickNameInfo) {
            return WebResult.of(ResponseResultInfoEnum.NICK_NAME_ERROR);
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setLoginValue(registerReq.getLoginValue());
        userInfo.setNickName(registerReq.getNickName());
        userInfo.setPassword(registerReq.getPassword());
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());
        userInfo.setDelFlag(0);
        userInfoService.saveSelective(userInfo);

        String encodeKey = stringRedisTemplate.opsForValue().get("login.token.encodedKey");
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userInfo.getId());
        payload.put("nickName", userInfo.getNickName());
        payload.put("exp", String.valueOf(System.currentTimeMillis() + EXP));
        String token = AuthTokenUtils.generateToken(payload, encodeKey);

        //token写入cookie
        userCookieComponent.addLoginCookie(token);

        return WebResult.of(token);
    }

    @ResponseBody
    @PostMapping("/login")
    public WebResult<String> login(@RequestBody LoginReq loginReq) {
        UserInfo loginValueInfo = userInfoService.getByLoginValue(loginReq.getLoginValue());
        if (null == loginValueInfo) {
            return WebResult.of(ResponseResultInfoEnum.NOT_REGISTER_YET);
        }

        if (!loginReq.getPassword().equals(loginValueInfo.getPassword())) {
            return WebResult.of(ResponseResultInfoEnum.LOGIN_INFO_ERROR);
        }

        String encodeKey = stringRedisTemplate.opsForValue().get("login.token.encodedKey");
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", loginValueInfo.getId());
        payload.put("nickName", loginValueInfo.getNickName());
        payload.put("exp", String.valueOf(System.currentTimeMillis() + EXP));
        String token = AuthTokenUtils.generateToken(payload, encodeKey);

        //token写入cookie
        userCookieComponent.addLoginCookie(token);
        return WebResult.of(token);
    }
}
