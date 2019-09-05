package com.demo.springboot2.controller;

import com.demo.springboot2.utlis.HtmlToPdfUtils;
import com.demo.springboot2.utlis.OpenHtmlToPDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@Controller
public class PdfController {
    private static Logger log = LoggerFactory.getLogger(PdfController.class);

    @RequestMapping("/create-pdf")
    public void createPdf(HttpServletResponse response) throws IOException {
        LocalDateTime start = LocalDateTime.now();
        response.setCharacterEncoding("UTF-8");
//        response.setContentType("application/octet-stream");
//        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode("测试.pdf", "UTF-8"));
        String html = OpenHtmlToPDF.readTxt("E:\\IdeaProject\\springboot2\\src\\main\\resources\\static\\test.html");
            HtmlToPdfUtils.generatePdf(response.getOutputStream(), html, "/static/fonts/FangSong.ttf", "FangSong", "E:\\IdeaProject\\springboot2\\src\\main\\resources\\static\\root.htm");
            OpenHtmlToPDF.generatePdf(html, response.getOutputStream());
        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        log.info("消耗时间:" + duration.toMillis() + "毫秒");
    }

    @RequestMapping("/test123")
    @ResponseBody
    public Object test() {
        return "爱杀几个手机阿红睡觉话，爱上是否合格分好";
    }

//    @RequestMapping("create-pdf")
//    public void createPdf(HttpServletResponse response, @RequestParam String html) throws FileNotFoundException, DocumentException {
//        Document document = new Document();
//        PdfWriter.getInstance(document, new FileOutputStream("D:/test.pdf"));
//        document.open();
//        document.add(new Paragraph("测试生成PDF"));
//        document.close();
//    }
}
