package com.demo.springboot2.controller;

import com.demo.springboot2.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
public class WebFluxController {

    @GetMapping("/test1")
    @ResponseBody
    public Mono<User> hello() {
        User user = new User();
        user.setId("a");
        user.setAge(20);
        user.setName("艾希");
        return Mono.just(user);
    }
}
