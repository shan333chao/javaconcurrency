package com.sunday.juc;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by HenDiao on 2017/10/14.
 */
public class SimpleUnsafe {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IOException {
//        Simple simple=new Simple();
//     //
//        System.out.println( simple.get());
        //  Simple simple1 = Simple.class.newInstance();
//        Class.forName("com.sunday.juc.SimpleUnsafe$Simple");
        Unsafe unsafe = getUnsafe();
//        ///绕过初始化 直接开辟内存
//        Simple simple=  (Simple)unsafe.allocateInstance(Simple.class);
//        System.out.println(simple.get());
//        System.out.println(simple.getClass().getClassLoader());
//-----------------------------------------------------------------------------------
//        Guard guard=new Guard() ;
//        guard.work();
//
//        Field access_allowed = guard.getClass().getDeclaredField("ACCESS_ALLOWED");
//        ///根据字段名称 修改值无访问权限的成员变量
//        unsafe.putInt(guard,unsafe.objectFieldOffset(access_allowed),42);
//        guard.work();
//----------------------------------------------------------------------------


        ///通过defineClass  加载一个class
//        String classPath="D:\\A.class";
//        byte[] bytes =Files.readAllBytes(Paths.get(classPath));
//
//        Class  clazz=unsafe.defineClass(null,bytes,0,bytes.length,null,null);
//        int result=(Integer) clazz.getMethod("get").invoke(clazz.newInstance(),null);
//        System.out.println(result);
//--------------------------------------计算对象大小-----------------------------------

//        long l = sizeOf(new Simple());
//        System.out.println(l );
//-------------------------------------------破坏单例模式----------------------------------
        Singleton singleton1 = (Singleton) unsafe.allocateInstance(Singleton.class);
        Singleton singleton2 = (Singleton) unsafe.allocateInstance(Singleton.class);

        System.out.println("singleton1: "+ singleton1.getInstance().hashCode());
        System.out.println("singleton2: "+ singleton2.getInstance().hashCode());

//----------------------------------------------------------------
        String classPath="D:\\MyCode\\currency\\src\\main\\java\\Singleton2.class";
        byte[] bytes =Files.readAllBytes(Paths.get(classPath));

        Class  clazz=unsafe.defineClass(null,bytes,0,bytes.length,null,null);
        Singleton getInstance =(Singleton) clazz.getMethod("getInstance").invoke(clazz.newInstance(), null);
        Singleton getInstance2 =(Singleton) clazz.getMethod("getInstance").invoke(clazz.newInstance(), null);
        System.out.println("singleton1: "+ getInstance.getInstance().hashCode());
        System.out.println("singleton2: "+ getInstance2.getInstance().hashCode());
    }
    private static long sizeOf(Object object){
        Unsafe unsafe = getUnsafe();
        Set<Field> fields=new HashSet<Field>();
        Class c= object.getClass();
        while (c!=Object.class){
            Field[] declaredFields = c.getDeclaredFields();
            for (Field f:declaredFields){
                if ((f.getModifiers()& Modifier.STATIC)==0){
                    fields.add(f);
                }
            }
            c=c.getSuperclass();
        }
        long maxOffSize=0;
        for (Field f:fields){
            long offiset=unsafe.objectFieldOffset(f);
            if (offiset>maxOffSize){
                maxOffSize=offiset;
            }
        }

        return ((maxOffSize/8)+1)*8;

    }

    static  class Guard{
        private int ACCESS_ALLOWED=1;
        private boolean allow(){
            return 42==ACCESS_ALLOWED;
        }
        public void work(){
            if(allow()){
                System.out.println("I am working by allowed");

            }
        }
    }
    private static Unsafe getUnsafe() {

        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            return (Unsafe) f.get(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    static class Simple{
        private  long l=0;
        private  int i=10;
        private byte b=(byte)0x01;
        public Simple() {
            this.l = 1;
            System.out.println("===================");
        }

        private  long get(){
            return l;
        }
    }
}
