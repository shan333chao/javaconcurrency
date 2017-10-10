package com.sunday.javathread.learn1.ThreadApi;

public class ThreadService {

    private Thread executeThread;
    private boolean finished = false;

    public void execute(Runnable task) {
        executeThread = new Thread() {
            @Override
            public void run() {
                Thread runner = new Thread(task);
                runner.setDaemon(true);
                runner.start();

                try {
                    runner.join();
                    finished = true;
                } catch (InterruptedException e) {
                    System.out.println("intrupted   finished");
                    //  e.printStackTrace();
                }
            }
        };
        executeThread.start();
    }

    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            long cost=(System.currentTimeMillis() - currentTime);
            System.out.println(cost);
            if (cost>= mills) {
                System.out.println("timeout!!!");
                executeThread.interrupt();
                break;
            }

            try {
                executeThread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("execute is intrupeted");
                break;
            }

        }
        finished=false;
    }
}
