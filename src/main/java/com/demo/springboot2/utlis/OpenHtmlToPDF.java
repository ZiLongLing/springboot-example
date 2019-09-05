package com.demo.springboot2.utlis;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class OpenHtmlToPDF {

    private static Logger log = LoggerFactory.getLogger(OpenHtmlToPDF.class);

    public static void generatePdf(String html, OutputStream os) {
        try {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            addFont(builder);
            builder.withHtmlContent(html, ROOT_PATH);
            //第一个参数是html页面  第二个参数 是一个全空的文件 里面什么都没有 但是后缀一定要是.htm  作用类似于一个画板 如果不添加这个参数或者置为null 则html文件中的图片 不会转化 所以一定需要加
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readTxt(String path) throws UnsupportedEncodingException {
//        try {
//            final InputStream is = new FileInputStream(path);
//            final StringBuilder sb = new StringBuilder();
//            final Reader r = new InputStreamReader(is, StandardCharsets.UTF_8);
//            char[] buf = new char[1024];
//            int amt = r.read(buf);
//            while (amt > 0) {
//                sb.append(buf, 0, amt);
//                amt = r.read(buf);
//            }
//            return sb.toString();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
        byte[] strBuffer = null;
        try {
            File file = new File(path);
            InputStream is = new FileInputStream(file);
            int size = is.available();
            strBuffer = new byte[size];
            is.read(strBuffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(strBuffer, "UTF-8");
    }

    private static final String FONT_DIR = "/static/fonts/";

    public static final String ROOT_PATH;

    static {
        String property = System.getProperty("user.dir");
        ROOT_PATH = property + File.separator + "root.htm";
    }

    private static List<String> fonts;

    public static void initFonts() {
        String classResourceName = OpenHtmlToPDF.class.getName().replace(".", "/") + ".class";
        String protocol = OpenHtmlToPDF.class.getClassLoader().getResource(classResourceName).getProtocol();
        try {
            boolean isJar = "jar".equals(protocol);
            String regex = isJar ? "(.*)static/fonts/(.*)" : "(.*)static\\\\fonts\\\\(.*)";
            String[] resources = OpenHtmlToPDF.getResources(HtmlToPdfUtils.class, regex);
            if (resources.length > 0) {
                fonts = new ArrayList<>(resources.length);
                for (int i = 0; i < resources.length; i++) {
                    String resource = resources[i];
                    int index = resource.lastIndexOf(isJar ? "/" : "\\") + 1;
                    fonts.add(resource.substring(index, resource.length()));
                }
            }
        } catch (IOException e) {
            log.info("初始化已有字体失败！");
            e.printStackTrace();
        }
        InputStream is = OpenHtmlToPDF.class.getResourceAsStream("/static/root.htm");
        OutputStream os;
        try {
            os = new FileOutputStream(ROOT_PATH);
            int i;
            byte[] buf = new byte[1024];
            try {
                while ((i = is.read(buf)) != -1) {
                    os.write(buf, 0, i);
                }
            } finally {
                is.close();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加字体库
     *
     * @param builder
     */
    public static void addFont(PdfRendererBuilder builder) {
//        String dir = "E:\\home\\pdf\\static\\fonts\\";
//        File f = new File(dir);
//        if (f.isDirectory()) {
//            File[] files = f.listFiles(new FilenameFilter() {
//                @Override
//                public boolean accept(File dir, String name) {
//                    String lower = name.toLowerCase();
////                    lower.endsWith(".otf") ||  对otf库的字体支持有问题，暂时屏蔽
//                    return lower.endsWith(".ttf") || lower.endsWith(".ttc");
//                }
//            });
//            for (File subFile : files) {
//                String fontFamily = subFile.getName().substring(0, subFile.getName().lastIndexOf("."));
//                builder.useFont(subFile, fontFamily);
//            }
//        }
        log.info(String.valueOf(fonts.size()));
        log.info(FONT_DIR);
        for (String font : fonts) {
            log.info(font);
            InputStream is = OpenHtmlToPDF.class.getResourceAsStream(FONT_DIR + font);
            String fontFamily = font.substring(0, font.indexOf("."));
            builder.useFont(() -> is, fontFamily);
        }
    }

    /**
     * 获取目录下所有的文件
     *
     * @param dir
     * @return
     */
    public static List<File> enumerateDir(File dir) {
        List fileList = new ArrayList();
        if (dir == null) {
            return null;
        } else if (dir.isDirectory()) {
            File[] subFiles = dir.listFiles();
            for (File subFile : subFiles) {
                fileList.add(subFile);
                if (subFile.isDirectory()) {
                    fileList.addAll(enumerateDir(subFile));
                }
            }
        } else {
            fileList.add(dir);
        }
        return fileList;
    }

    /**
     * 获取resources下匹配的文件路径数组
     *
     * @param clazz
     * @param name  正则匹配规则
     * @return
     * @throws IOException
     */
    public static String[] getResources(Class clazz, String name) throws IOException {
        Set<String> resources = new HashSet<>();
        String classResourceName = clazz.getName().replace(".", "/") + ".class";
        URL classResourceURL = clazz.getClassLoader().getResource(classResourceName);
        String classResourcePath = classResourceURL.getPath();
        if (classResourceURL.getProtocol().equals("file")) {
            // 开发环境里class和resource同位于target/classes目录下
            String classesDirPath = classResourcePath.substring(classResourcePath.indexOf("/") + 1, classResourcePath.indexOf(classResourceName));
            File classesDir = new File(classesDirPath);
            List<File> files = enumerateDir(classesDir);
            for (File file : files) {
                String resourceName = file.getAbsolutePath();
                resourceName = resourceName.substring(classesDirPath.length());
                resourceName = resourceName.replace("//", "/");
                if (!file.isDirectory() && resourceName.matches(name)) {
                    resources.add(resourceName);
                }
            }
        } else if (classResourceURL.getProtocol().equals("jar")) {
            // 打包成jar包时,class和resource同位于jar包里
            String jarPath = classResourcePath.substring(classResourcePath.indexOf("/"), classResourceURL.getPath().indexOf("!"));
            log.info("=====" + jarPath);
            try {
                JarFile jarFile = new JarFile(URLDecoder.decode(jarPath, "UTF-8"));
                Enumeration<JarEntry> jarEntries = jarFile.entries();
                while (jarEntries.hasMoreElements()) {
                    JarEntry jarEntry = jarEntries.nextElement();
                    String resourceName = jarEntry.getName();
                    if (resourceName.matches(name) && !jarEntry.isDirectory()) {
                        resources.add(resourceName);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return resources.toArray(new String[0]);
    }
}