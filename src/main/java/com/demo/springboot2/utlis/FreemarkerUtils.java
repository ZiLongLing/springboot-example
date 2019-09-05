package com.demo.springboot2.utlis;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.Map;

public class FreemarkerUtils {
    private static String defaultCharacter = "UTF-8";
    private static Configuration cfg;

    private FreemarkerUtils() {
    }

    static {
        cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDefaultEncoding(defaultCharacter);
        cfg.setTagSyntax(Configuration.AUTO_DETECT_TAG_SYNTAX);
    }

    public static String processTemplate(String myTemplate, Map<String, Object> map) {
        String result = null;
        String name = "template";

        try {
            StringTemplateLoader stringTemplateLoader = new StringTemplateLoader();
            stringTemplateLoader.putTemplate(name, myTemplate);
            cfg.setTemplateLoader(stringTemplateLoader);
            Template template = cfg.getTemplate(name, defaultCharacter);
            StringWriter out = new StringWriter();
            template.process(map, out);
            out.flush();
            result = out.toString();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
