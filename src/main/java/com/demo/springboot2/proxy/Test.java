package com.demo.springboot2.proxy;

import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        // JDK的动态代理方式
        SMSService smsService = new SMSServiceImpl();
        smsService = (SMSService) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{SMSService.class}, new MoneyCountInvocationHandler(smsService));
        smsService.sendMessage();
        smsService.sendMessage();
        smsService.sendMessage();
        smsService.sendMessage();
        smsService.sendMessage();

        // cglib方式
        SMSServiceImpl smsServiceImpl = new SMSServiceImpl();
        CglibProxyFactory cglibProxyFactory = new CglibProxyFactory();
        smsServiceImpl = (SMSServiceImpl) cglibProxyFactory.getProxyObject(smsServiceImpl);
        smsServiceImpl.sendMessage();
        smsServiceImpl.sendMessage();
        smsServiceImpl.sendMessage();
        smsServiceImpl.sendMessage();
        smsServiceImpl.sendMessage();
    }
}
