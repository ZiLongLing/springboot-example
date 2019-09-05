package com.demo.springboot2.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 短信发送计费代理类 JDK的动态代理方式，只对接口有效
 */
public class MoneyCountInvocationHandler implements InvocationHandler {

    /**
     * 被代理的目标
     */
    private final Object target;

    /**
     * 短信费用总数
     */
    private Double moneyCount;

    public MoneyCountInvocationHandler(Object target) {
        this.target = target;
        this.moneyCount = 0.0;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(target, args);
        moneyCount += 0.1;
        System.out.println("发送短信成功，共花了：" + moneyCount + "元");
        return result;
    }
}
