package com.spring.demo.controller;

import com.spring.demo.component.UserCookieComponent;
import com.spring.demo.po.UserInfo;
import com.spring.demo.request.RegisterReq;
import com.spring.demo.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Controller
public class LoginController {
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserCookieComponent userCookieComponent;

    @ResponseBody
    @PostMapping("/register")
    public String register(@RequestBody RegisterReq registerReq) {
        UserInfo loginValueInfo = userInfoService.getByLoginValue(registerReq.getLoginValue());
        if (null != loginValueInfo) {
            return "当前用户已注册，请直接登录";
        }

        UserInfo nickNameInfo = userInfoService.getByNickName(registerReq.getNickName());
        if (null != nickNameInfo) {
            return "当前昵称已占用，请重新注册";
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setLoginValue(registerReq.getLoginValue());
        userInfo.setNickName(registerReq.getNickName());
        userInfo.setPd(registerReq.getPd());
        userInfo.setCreateTime(new Date());
        userInfo.setUpdateTime(new Date());
        userInfo.setDelFlag(0);
        userInfoService.saveSelective(userInfo);

        return "注册成功";
    }

    @ResponseBody
    @PostMapping("/login")
    public String login(@RequestBody RegisterReq registerReq) {
        UserInfo loginValueInfo = userInfoService.getByLoginValue(registerReq.getLoginValue());
        if (null == loginValueInfo) {
            return "当前用户未注册，请注册后登录";
        }

        if (!registerReq.getPd().equals(loginValueInfo.getPd())) {
            return "用户名或密码错误";
        }

        //userId写入cookie
        userCookieComponent.addLoginCookie(String.valueOf(loginValueInfo.getId()));
        return "登录成功";
    }
}
