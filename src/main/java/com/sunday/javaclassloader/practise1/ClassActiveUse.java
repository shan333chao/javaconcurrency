package com.sunday.javaclassloader.practise1;


import java.util.Random;

/**
 * Created by HenDiao on 2017/10/5.
 *
 * 主动使用的分类
 *
 * 1、new，直接使用
 * 2、访问某个类或者接口的静态变量，或者对静态变量进行赋值操作
 * 3、调用静态方法
 * 4、反射某个类
 * 5、初始化一个子类
 * 6、启动类 (main)
 * 除此删除六个以外，其余的都是被动使用、不会导致类的初始化
 */
public class ClassActiveUse {

    public static void main(String[] args) throws ClassNotFoundException {

        //        Obj obj = new Obj();

//        System.out.println(I.x);

//        System.out.println(SubObj.a);
//        Obj.printSalary();
        ///定义引用数组不会初始化
        Obj[] ss=new Obj[10];
        System.out.println( SubObj.y);

        //     Class.forName("practise1.SubObj");

    }
}

class SubObj extends Obj {
    public static int age = 28;

    static {
        System.out.println("subobj init"+age);
    }
}

class Obj implements I {
    ///常量会在编译器件放在常量池中，不会初始化类
    public static  final int x=666;
    ///final修饰的复杂类型，在编译器件无法计算的出，会初始化类
    public static  final int y=new Random().nextInt(100);
    public static long salary = 100000L;

    static {
        System.out.println("Obj init");
    }

    public static void printSalary() {
        System.out.println(salary);
    }

}

//访问某个类或者接口的静态变量，，或者对该静态变量进行赋值操作
//1、对谋改革静态变量进行读写  ---- init Class
//2、对接口中静态变量进行读取  -----init interface
interface I {
    int a = 10;
}





