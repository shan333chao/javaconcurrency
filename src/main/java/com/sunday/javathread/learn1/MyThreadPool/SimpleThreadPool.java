package com.sunday.javathread.learn1.MyThreadPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Created by HenDiao on 2017/9/5.
 */
public class SimpleThreadPool extends  Thread{


    private int size;
    private int queuesize;
    private final static int DEFAULT_SIZE = 10;
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();
    private static volatile int seq=0;
    private static final String THREAD_PREFIX = "SANDY_THREAD_POOL-";
    private static final ThreadGroup group = new ThreadGroup("pool_group");
    private final static List<WorkerTask> Thread_QUEUE = new ArrayList<>();
    public final static DiscardPolicy DFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("DFAULT_DISCARD the task");
    };
    private volatile boolean distoryed = false;
    private final DiscardPolicy discardPolicy;
    ///线程池初始化大小
    private  int min;
    ///线程池大于预定最小线程
    private int max;
    ////线程池最大线程数
    private  int active;

    public SimpleThreadPool() {
        this(DEFAULT_SIZE, DEFAULT_TASK_QUEUE_SIZE, DFAULT_DISCARD_POLICY);
    }


    public SimpleThreadPool(int size, int queuesize, DiscardPolicy discardPolicy) {
        this.size = size;
        this.queuesize = queuesize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        IntStream.range(0, size).forEach(s -> {
            createWoekTask();
        });
    }

    public void submit(Runnable runnable) {
        if (distoryed) {
            throw new IllegalStateException("The thread poll already destory and not allow summit task");
        }
        synchronized (TASK_QUEUE) {
            if (TASK_QUEUE.size() > queuesize) {
                System.out.println("queue is full");
                discardPolicy.discard();

            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }

    }

    @Override
    public void run() {
        super.run();
    }


    private void createWoekTask() {
        WorkerTask task = new WorkerTask(group, THREAD_PREFIX + (seq++));
        task.start();
        Thread_QUEUE.add(task);
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    public void shutdown() throws InterruptedException {
        System.out.println(TASK_QUEUE.size());
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        int initVal = Thread_QUEUE.size();
        while (initVal > 0) {
            for (WorkerTask task : Thread_QUEUE) {
                if (task.getTaskState() == TaskState.BLOCKED) {
                    task.interrupt();
                    task.close();
                    System.out.println(task.getName());
                    System.out.println( initVal--); ;
                } else {
                    Thread.sleep(10);
                }
            }
        }
        System.out.println(group.activeCount());
        this.distoryed = true;
        System.out.println("the thread poll disposed.");
    }

    public int getQueueSize() {
        return this.queuesize;
    }

    public int getSize() {
        return size;
    }

    public boolean isDestory() {
        return this.distoryed;
    }

    public static class DiscardException extends RuntimeException {
        public DiscardException(String msg) {
            super(msg);
        }

    }

    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    private static class WorkerTask extends Thread {
        //volatile 内存可见
        private volatile TaskState taskState = TaskState.FREE;

        public TaskState getTaskState() {
            return this.taskState;
        }

        public WorkerTask(ThreadGroup threadGroup, String name) {
            super(threadGroup, name);
        }

        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {

                    while (TASK_QUEUE.isEmpty()) {
                        try {
                            taskState = TaskState.BLOCKED;
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {

                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                }

                if (runnable != null) {

                    taskState = TaskState.RUNNING;
                    runnable.run();
                    taskState = TaskState.FREE;
                }
            }
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }
    }

    public static void main(String[] args) throws InterruptedException {

        SimpleThreadPool pool = new SimpleThreadPool(6, 10, SimpleThreadPool.DFAULT_DISCARD_POLICY);

        for (int s = 0; s < 40; s++) {
            pool.submit(() -> {
                System.out.println("The service task is " + Thread.currentThread() + " started");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("The service task is" + Thread.currentThread() + " done!");
            });
        }
        pool.shutdown();
        Thread.sleep(1000);

        pool.submit(() -> {
            System.out.println("args = [" + 111111111 + "]");
        });
    }

}
