package com.spring.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterReq {
    @NotNull(message = "用户名不能为空")
    private String loginValue;

    private String nickName;

    @NotNull(message = "密码不能为空")
    private String pd;
}
