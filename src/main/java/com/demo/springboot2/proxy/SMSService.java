package com.demo.springboot2.proxy;

/**
 * 短信验证码接口
 */
public interface SMSService {

    /**
     * 发送短信验证码
     */
    void sendMessage();
}
