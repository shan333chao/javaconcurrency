package com.sunday.javaclassloader.java.lang;

import java.util.Vector;

/**
 * Created by HenDiao on 2017/10/5.
 *
 *
 * 类加载的三个阶段
 * 1 加载：查找并且加载类的二进制数据
 * 2、链接：
 *          2.1 验证：确保被加载类的正确性
 *          2.2 准备：为类的静态变量分配内存，并将其初始化为默认值
 *          2.3 解析：把类中的符号引用转换为直接引用 （this,super 等等都为符号）
 * 3、初始化：为类的静态变量赋予正确的初始值
 * -------------------------------------------
 *|                 1、 Loading            |
 * ------------------------------------------
 *                        ↓↓
 * |---------------------------------------------
 * |             2、  Linking                   |
 * |    ----------------------------------       |
 * |   |              Verifying          |       |
 * |    ----------------------------------       |
 * |    ----------------------------------       |
 * |   |              Preparing          |       |
 * |    ----------------------------------       |                                              |
 * |    ————————————————         |
 * |   |         Resolving             |         |
 * |   ————————————————         |
 *|-------------------------------------------- |
 *                  ↓↓
 * ------------------------------------------
 *|            3、 Initialising             |
 *------------------------------------------
 *
 *
 * Java 程序对类的使用方式
 * 1.主动使用
 * 2、被动使用
 * 所有的Java虚拟机实现必须在每个类或者接口被java程序【首次主动使用时】才初始化他们，
 * 当然现代JVM有可能根据程序的上下文语义推断出接下来可能初始化谁
 *
 */
public class String {
    ///2.2链接 准备 将为静态变量 分配内存 为 2.3 做准备
    private  static  int i=1;
    private  int x;
    private  Object object=new Object();
    public String(int x) {
        this.x = x;
    }

    public int getValue() {
        //this 是通过句柄的方式来访问 还是指针的方式来访问？？？
        this.object.hashCode();
        return i;
    }
}
