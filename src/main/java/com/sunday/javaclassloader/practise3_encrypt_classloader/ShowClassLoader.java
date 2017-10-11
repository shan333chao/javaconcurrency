package com.sunday.javaclassloader.practise3_encrypt_classloader;

import com.sunday.javaclassloader.practise2_create_classLoader.MyClassLoader;



import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by HenDiao on 2017/10/5.
 */
public class ShowClassLoader {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        Class<?> klass=Class.forName("practise1.SimpleObj");
        System.out.println(klass.getClassLoader());
        MyClassLoader  classLoader=new MyClassLoader("MyClassLoader");
        Class<?> clazz = classLoader.loadClass("practise2_create_classLoader.MyObject");
        System.out.println(clazz);
        System.out.println(clazz.getClassLoader());
        Object o = clazz.newInstance();
        Method hello = clazz.getMethod("Hello", new Class[]{});
        hello.invoke(o,new Object[]{});


        EncryptClassLoader encryptClassLoader=new EncryptClassLoader("EncryptClassLoader");
//
//        Class<?> encriptClazz= encryptClassLoader.loadClass("practise2_create_classLoader.MyObject");
//        System.out.println(encriptClazz);
//        System.out.println(encriptClazz.getClassLoader());
//        Object encriptO = encriptClazz.newInstance();
//        Method encriptHello = encriptClazz.getMethod("Hello", new Class[]{});
//        encriptHello.invoke(encriptO,new Object[]{});
    }
}
