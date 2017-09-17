package com.sunday.concurrency.pritase3;

public class VolitaleTest {
    /**
     * volatile不能保证原子性
     * volatile 只能保证内存 可见性和 有序性
     * 一旦一个共享变量被volatile修饰
     * 1、保证了不同线程的可见性
     * 2、进制对其进行重排序、也就是保证了有序性,
     *      保证重排序时候不会吧后面的指令放在屏障的前面，也不会吧前面的后面
     *      强制对缓存的修改操作立刻写入主内存
     *      如果是写入操作，他会导致其他cpu中的缓存失效
     * 3、并未保证原子性
     *
     * volatile 使用场景
     *
     * 1 状态可变量标记
     * volatile boolean start =true；
     * while（start）{
     *
     * }
     * void close（）{
     *     start=false；
     * }
     *
     *
     * 给总线加锁：LOCK
     *    数据总线  地址总线  控制总线
     *
     * 2.CPU 高速缓存一致性协议
     *          Intel  MESI  保证每一个缓存中使用的共享变量的副本是一样的
     *      核心思想：1、CPU对内存写入时 如果发现该变量被内存共享（也就是说在其他cpu也存在该变量的副本），它会发出一个信号，通知其他cpu该变量的缓存无效
     *                2、当其他 cpu访问该变量的时候，会重新到主内存进行获取
     *
     *  3个并发编程的基本概念
     *  1、原子性 Atomic  一个或多个操作 要么都成功 要么都失败，中间不能由于任何因素中断
     *      对基本数据类型的变量读取和复制是保证了原子性的  要么都成功 要么都失败 这些操作都是不可被中断的
     *      i=10
     *      cache 10 - memery 10
     *      ------------------
     *      a=10; 原子性
     *      a=b;   不满足 1 read a ; 2 assign b
     *      c++;   不满足  1 read c ; 2 add c ; 3 assign to c
     *      c=c+1; 不满足  1 read c ; 2 add c ; 3 assign to c
     *
     *
     *  2、可见性  Visiable
     *  使用volatite 关键字保证可见性，当变量被修改时么  会立即修改到主内存中，让其他线程读取的时候直接从主内存中拿区数据，
     *  而不是到该线程的缓存中拿数据
     *
     *
     *
     *
     *  Thread- 1
     *  int i=0   int j=10;
     *
     *  Thread -2
     *
     * 3、 有序性 Order
     *
     * java在 编译和运行过程中， 允许对变量进行重新排序，
     * java语言中天生帮我们保证了一些有序性原则  happens-before 原则 （relationship）
     * 3.1 在一个线程内代码的执行顺序 编写在前面的发生在 编写在后面的
     * 3.2 锁的原则 unlock必须发生在lock之后 ，（只有加了锁才能解锁）
     * 3.3 volatile 修饰的变量，对一个变量写入操作先于对变量的读取操作
     * 3.4 传递规则  操作A先于B，B先于C 那么A肯定先于C
     * 3.5 线程启动规则，start 方法肯定先于线程执行(run 方法)操作
     * 3.6 线程的中断规则 interrupt 这个动作，必须发生在捕获该动作之前（sleep 或者wait 时候调用中断之后才能到你执行中断的地方去
     *  ， 不能捕获了异常后 才去中断）
     *  3.7 对象销毁规则，对象的初始化必须发生在finalize之前
     *  3.8 线程终结规则，线程所有的操作必须发生在对这个线程销毁之前
     *
     * JMM  java 内存模型
     */
    private  volatile   static  int INI_VALUE=0;
    private  final  static  int MAX_LIMIT=50;
    public static void main(String[] args) {

        new Thread(()->{
            int local=INI_VALUE;
            while (local<MAX_LIMIT){
                if (local!=INI_VALUE){
                    System.out.printf("the value update to [%d] \n",INI_VALUE);
                    local=INI_VALUE;
                }
            }
        },"reader").start();
        new Thread(()->{
            int localvalue=INI_VALUE;
            while (INI_VALUE<MAX_LIMIT){
                System.out.printf(" update the  value  to [%d] \n",++localvalue);
                INI_VALUE=localvalue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"updater").start();
    }
}
