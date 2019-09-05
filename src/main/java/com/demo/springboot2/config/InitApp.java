package com.demo.springboot2.config;

import com.demo.springboot2.utlis.OpenHtmlToPDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitApp implements ApplicationRunner {

    private static Logger log = LoggerFactory.getLogger(InitApp.class);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("系统初始化成功...");
        log.error("错误异常信息...");
        log.warn("warn异常信息...");
        OpenHtmlToPDF.initFonts();
//        try {
//            String[] resources = OpenHtmlToPDF.getResources(HtmlToPdfUtils.class, "(.*)static[\\\\|/]fonts[\\\\|/](.*)");
//            for (String resource : resources) {
//                int index1 = resource.lastIndexOf("/") + 1;
//                int index2 = resource.lastIndexOf(".");
//                System.out.println(resource);
//                System.out.println(resource.substring(index1, index2));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
