package com.demo.springboot2.utlis;

import com.openhtmltopdf.extend.FSSupplier;
import com.openhtmltopdf.outputdevice.helper.BaseRendererBuilder;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;

public class HtmlToPdfUtils {

    private static Logger log = LoggerFactory.getLogger(HtmlToPdfUtils.class);

    public static void generatePdf(OutputStream os, String htmlStr, String fontPath, String fontFamily, String baseUri) {
        try {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            //下面这个方法是要自己指定字体文件,不然转出的pdf文件中 中文会变成####
            //fontFamily参数一定要和html中的字体名一样！！否则失效
            builder.useFont(new FSSupplier<InputStream>() {
                @Override
                public InputStream supply() {
                    return HtmlToPdfUtils.class.getResourceAsStream(fontPath);
                }
            }, fontFamily, 400, BaseRendererBuilder.FontStyle.NORMAL, true);
            //第一个参数是html页面  第二个参数 是一个全空的文件 里面什么都没有 但是后缀一定要是。htm  作用类似于一个画板 如果不添加这个参数或者置为null 则html文件中的图片 不会转化 所以一定需要加
            builder.withHtmlContent(htmlStr, baseUri);
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            log.error("PDF生成失败！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        PdfRendererBuilder builder = new PdfRendererBuilder();
//        builder.useFastMode();
//        OpenHtmlToPDF.addFont(builder);
//        builder.withHtmlContent(OpenHtmlToPDF.readTxt(HtmlToPdfUtils.class.getResource("/static/test.html").getPath()), HtmlToPdfUtils.class.getResource("/static/root.htm").getPath());
//        try (OutputStream os = new FileOutputStream("E:/test.pdf")) {
//            builder.toStream(os);
//            builder.run();
//            os.close();
//        } catch (Exception e) {
//            log.error("PDF生成失败！");
//            e.printStackTrace();
//        }
//        try {
//            String[] resources = OpenHtmlToPDF.getResources(HtmlToPdfUtils.class, "(.*)static[\\\\|/]fonts[\\\\|/](.*)");
//            for (String resource : resources) {
//                int index1 = resource.lastIndexOf("\\") + 1;
//                int index2 = resource.lastIndexOf(".");
//                System.out.println(resource);
//                System.out.println(resource.substring(index1, resource.length()));
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        boolean matches = "BOOT-INF/classes/static/fonts/SimSun.ttf".matches("(.*)static[\\\\|/]fonts[\\\\|/](.*)");
        System.out.println(matches);
        boolean matches1 = "static\\fonts\\SimSun.ttf".matches("(.*)static\\\\fonts\\\\(.*)");
        System.out.println(matches1);
        System.out.println(System.getProperty("user.dir"));
    }
}
