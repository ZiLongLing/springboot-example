package com.demo.springboot2.ioc;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IocService {
    String name() default "";
}
