package com.spring.demo.msg;

public interface RetCode {

    /**
     * code值
     *
     * @return
     */
    int code();

    /**
     * 默认返回信息
     *
     * @return
     */
    String defaultMsg();

}
