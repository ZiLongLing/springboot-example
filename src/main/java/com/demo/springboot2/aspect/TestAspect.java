package com.demo.springboot2.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspect {

    @Pointcut(value = "@args(org.springframework.validation.annotation.Validated) && within(com.demo.springboot2..*)")
    public void getAspect() {
    }

    @Before(value = "getAspect()")
    public void before(JoinPoint joinPoint) {
        System.out.println(joinPoint.getArgs());
    }
}
