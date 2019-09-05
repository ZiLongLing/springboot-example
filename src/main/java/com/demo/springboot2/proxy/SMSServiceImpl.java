package com.demo.springboot2.proxy;

public class SMSServiceImpl implements SMSService {
    @Override
    public void sendMessage() {
        System.out.println("【测试】您的验证码为：1234，5分钟内有效，请不要将验证码转发给他人。");
    }
}
