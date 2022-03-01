package com.spring.demo.vo;

public class UserInfoVo {
    /**
     * id
     */
    private Long id;

    /**
     * 用户名
     */
    private String loginValue;

    /**
     * 登录类型
     */
    private String nickName;

    public UserInfoVo() {
    }

    public UserInfoVo(Long id, String loginValue, String nickName) {
        this.id = id;
        this.loginValue = loginValue;
        this.nickName = nickName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginValue() {
        return loginValue;
    }

    public void setLoginValue(String loginValue) {
        this.loginValue = loginValue;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
