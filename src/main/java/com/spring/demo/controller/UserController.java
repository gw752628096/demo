package com.spring.demo.controller;

import com.spring.demo.annotation.VerifyLogin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@VerifyLogin
public class UserController {

    @GetMapping("/toMyPage")
    public String toMyPage() {
        // TODO: 2023/5/12 开盘列表
        return "myPage";
    }
}
