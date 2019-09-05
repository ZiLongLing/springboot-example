package com.demo.springboot2.controller;

import com.demo.springboot2.config.LongServerProperties;
import com.demo.springboot2.pojo.GroupA;
import com.demo.springboot2.pojo.User;
import com.demo.springboot2.pojo.ValidList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.groups.Default;

@Controller
@Validated
public class IndexController {

    @Autowired
    private LongServerProperties longServerProperties;

    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }

    @RequestMapping("/index")
    public ModelAndView index(String type) {
        int i = 10 / 0;
        return new ModelAndView("/index");
    }

    @RequestMapping("/properties")
    @ResponseBody
    public String properties() {
        return longServerProperties.getAuthor();
    }

    @RequestMapping("/test")
    @ResponseBody
    public String test(@Validated({GroupA.class, Default.class}) @RequestBody ValidList<User> users) {
        return "测试";
    }
}
