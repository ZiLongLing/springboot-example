package com.demo.springboot2.school;

import java.io.File;

public class Test {

    public static void main(String[] args) throws Exception {
        File temp = File.createTempFile("凌子龙", ".txt");
        System.out.println("文件路径: " + temp.getAbsolutePath());
        temp.deleteOnExit();
//        BufferedWriter out = new BufferedWriter(new FileWriter(temp));
//        out.write("aString");
        System.out.println("临时文件已创建:");
//        out.close();
    }
}
