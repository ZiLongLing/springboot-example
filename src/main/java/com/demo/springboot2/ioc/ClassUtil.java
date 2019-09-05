package com.demo.springboot2.ioc;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 获取某个包下面的所有类信息
 */
public class ClassUtil {

    /**
     * 取得某个接口下所有实现这个接口的类
     */
    public static List<Class> getAllClassByInterface(Class c) {
        List<Class> resultClassList = null;
        if (c.isInterface()) {
            // 获取包名
            String packageName = c.getPackage().getName();
        }
        return null;
    }

    public static List<Class<?>> getPackageClasses(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        // 是否循环迭代
        boolean recursive = true;
        // 获取包的名字 并进行替换
        String packageDirName = packageName.replace('.', '/');
        // 定义一个枚举的集合 并进行循环来处理这个目录下的things
        Enumeration<URL> dirs;
        return null;
    }
}
