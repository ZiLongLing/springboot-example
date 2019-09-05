package com.demo.springboot2.proxy;

import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib 动态代理 这里引用spring的封装 代理基类生成子类
 */
public class CglibProxyFactory implements MethodInterceptor {
    /**
     * 被代理的对象
     */
    private Object target;

    /**
     * 短信费用总数
     */
    private Double moneyCount;

    /**
     * 动态生成一个新的类，使用无参构造函数创建指定的代理对象
     */
    public Object getProxyObject(Object target) {
        BeanGenerator generator = new BeanGenerator();
//        generator.addProperty();
        this.moneyCount = 0.0;
        //被代理对象
        this.target = target;
        //增强器，动态代码生成器
        Enhancer enhancer = new Enhancer();
        //设置生成类的父类，其实就是 被代理的类
        enhancer.setSuperclass(target.getClass());
        //回调, Callback 的实现类，这里我们使用了 MethodInterceptor 接口
        enhancer.setCallback(this);
        //创建代理对象
//        ProxyFactory
        return enhancer.create();
    }

    @Override
    public Object intercept(Object proxyObject, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object obj = methodProxy.invoke(target, args);
        moneyCount += 0.1;
        System.out.println("发送短信成功，共花了：" + moneyCount + "元");
        return obj;
    }
}
