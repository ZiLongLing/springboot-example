package com.demo.springboot2.utlis;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidUtils {
    public static String filePath = "F:\\TortoiseSVN_bin\\ms\\lagl-sh-pojo\\src\\main\\java\\com\\thunisoft\\laglshpojo\\pojo\\xsaj\\TXsAjTz.java";
    public static String outPath = "E:\\IdeaProject\\springboot2\\src\\main\\java\\com\\demo\\springboot2\\utlis\\valid\\";
    public static String sqlPath = "E:\\IdeaProject\\springboot2\\src\\main\\java\\com\\demo\\springboot2\\utlis\\table.sql";

    public static void main(String[] args) {
        String className = filePath.substring(filePath.lastIndexOf('\\') + 1, filePath.lastIndexOf('.'));
        String newClassName = className + "Valid";
        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null; //用于包装InputStreamReader,提高处理性能。因为BufferedReader有缓冲的，而InputStreamReader没有。
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;
        try {
            String sqlString = OpenHtmlToPDF.readTxt(sqlPath);
            String[] sqlFileds = sqlString.substring(sqlString.indexOf('(') + 1, sqlString.lastIndexOf(')')).split("\r\n");
            Map<String, String> fileds = getFileds(sqlFileds);
            String str = "";
            fis = new FileInputStream(filePath);// FileInputStream
            // 从文件系统中的某个文件中获取字节
            isr = new InputStreamReader(fis);// InputStreamReader 是字节流通向字符流的桥梁,
            br = new BufferedReader(isr);// 从字符输入流中读取文件中的内容,封装了一个new InputStreamReader的对象
            fos = new FileOutputStream(outPath + newClassName + ".java");
            osw = new OutputStreamWriter(fos, "UTF-8");
            bw = new BufferedWriter(osw);
            while ((str = br.readLine()) != null) {
                if (str.contains(className)) {
                    str = str.replace(className, newClassName);
                }
                String lenght = fileds.get(str.trim());
                if (lenght != null) {
                    bw.write("    " + lenght + "\r\n");
                }
                bw.write(str + "\r\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("找不到指定文件");
        } catch (IOException e) {
            System.out.println("读取文件失败");
        } finally {
            try {
                br.close();
                isr.close();
                fis.close();

                bw.close();
                osw.close();
                fos.close();
                // 关闭的时候最好按照先后顺序关闭最后开的先关闭所以先关s,再关n,最后关m
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static final Pattern FILED_TYPE_PATTERN = Pattern.compile("varchar[(]\\d+[)]");

    public static Map<String, String> getFileds(String[] var1) {
        Map<String, String> result = new HashMap<>(var1.length);
        for (String s : var1) {
            Matcher matcher = FILED_TYPE_PATTERN.matcher(s);
            String fileType = null;
            while (matcher.find()) {
                fileType = matcher.group();
            }
            if (fileType == null) {
                continue;
            }
            int index1 = s.indexOf('"');
            int index2 = s.indexOf('"', index1 + 1);
            if (index1 > 0 && index2 > 0 && index2 > index1) {
                String s1 = s.substring(index1 + 1, index2);
                int index3 = s1.indexOf("_");
                String s2 = toUpperCamelCase(index3 > 0 ? s1.substring(index3 + 1) : s1);
                String length = fileType.substring(fileType.indexOf('(') + 1, fileType.indexOf(')'));
                result.put("private String " + s2 + ";", "@Length(max = " + length + ")");
//                int index4 = s.indexOf(s1);
//                int index5 = s.indexOf(" ", index4);
//                int index6 = s.indexOf(" ", index5 + 1);
//                String s3 = s.substring(index5 + 1, index6);
                //s3.matches("varchar[(]\\d+[)]")

            }
        }
        return result;
    }

    public static String toUpperCamelCase(String var1) {
        String[] split = var1.split("_");
        if (split.length == 1) {
            return split[0];
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < split.length; i++) {
            if (i > 0) {
                String s1 = split[i].substring(0, 1);
                String s2 = split[i].substring(1, split[i].length());
                sb.append(s1.toUpperCase() + s2);
            } else {
                sb.append(split[i]);
            }
        }
        return sb.toString();
    }
}
