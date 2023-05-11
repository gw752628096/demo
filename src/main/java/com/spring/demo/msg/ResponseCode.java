package com.spring.demo.msg;

/**
 * 公共返回枚举
 *
 * @author Ge Hui
 */
public interface ResponseCode {

    /**
     * 获取返回值的Code
     *
     * @return code
     */
    Integer getCode();

    /**
     * 获取返回值的描述
     *
     * @return des
     */
    String getDescription();

}
