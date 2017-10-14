package com.sunday.juc;

import com.google.common.base.Stopwatch;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HenDiao on 2017/10/14.
 * 有很多后门的方法
 * 可以加载类替代classloader
 * 可以开辟释放内存
 * 底层是C++和汇编实现的
 * java是个安全的语言  阻止的程序员的愚蠢错误
 * 大多数都是内存管理
 * java不建议使用unsafe
 */
public class UnsafeTest {

    public static void main(String[] args) throws InterruptedException, NoSuchFieldException {
        //Unsafe unsafe = Unsafe.getUnsafe();
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe);
        /**
         * Stupid Counter result9970622
         Time passed in :390.8 ms

         Sync Counter result: 10000000
         Time passed in :654.6 ms

         Lock      Counter result: 10000000
         Time passed in :550.7 ms

         Atomic      Counter result: 10000000
         Time passed in :620.8 ms


         Unsafe  sun.misc.Unsafe@4b67cf4d
         Counter result: 0
         Time passed in :727.4 ms
         */
        ExecutorService service = Executors.newFixedThreadPool(1000);
        Counter counter = new UnsafeCounter();
        Stopwatch stopwatch = Stopwatch.createStarted();
        for (int i = 0; i < 1000; i++) {
            service.submit(new CounterRunable(counter, 10000));
        }
        service.shutdown();
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();
        System.out.println("Counter result: " + counter.getCounter());

        System.out.println("Time passed in :" + stopwatch.stop());
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

    static class StupiedCounter implements Counter {
        private long counter = 0;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class UnsafeCounter implements Counter {

        private volatile long counter = 0;
        private Unsafe unsafe;
        private long offset;

        public UnsafeCounter() throws NoSuchFieldException {
            this.unsafe = getUnsafe();
            offset = unsafe.objectFieldOffset(UnsafeCounter.class.getDeclaredField("counter"));
        }

        @Override
        public void increment() {
            long current = counter;
            while (!unsafe.compareAndSwapLong(this, offset, counter, current++)) {
                current = counter;
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class AtomicCounter implements Counter {
        private AtomicLong counter = new AtomicLong(0);
        ///默认不公平
        private final Lock lock = new ReentrantLock();

        @Override
        public void increment() {

            counter.incrementAndGet();

        }

        @Override
        public long getCounter() {
            return counter.get();
        }
    }


    static class LockStupiedCounter implements Counter {
        private long counter = 0;
        ///默认不公平
        private final Lock lock = new ReentrantLock();

        @Override
        public void increment() {
            try {
                lock.lock();
                counter++;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class SyncStupiedCounter implements Counter {
        private long counter = 0;

        @Override
        public synchronized void increment() {

            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    interface Counter {
        void increment();

        long getCounter();
    }

    static class CounterRunable implements Runnable {
        private Counter counter;
        private int num;

        public CounterRunable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i = 0; i < num; i++) {
                counter.increment();
            }
        }
    }
}
