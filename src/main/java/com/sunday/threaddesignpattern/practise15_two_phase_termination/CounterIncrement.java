package com.sunday.threaddesignpattern.practise15_two_phase_termination;

import java.util.Random;

/**
 * Created by HenDiao on 2017/10/4.
 */
public class CounterIncrement extends Thread {
    private volatile boolean terminated = false;
    private int counter = 0;
    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        try {
            while (!terminated) {
                System.out.println(Thread.currentThread().getName() + " counter" + counter++);
                Thread.sleep(random.nextInt(1000));
            }
        } catch (InterruptedException e) {
          //  e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    private void clean() {
        System.out.println("do some clean work for the second phase,counter"+counter);

    }

    public void close() {
        this.terminated = true;
        this.interrupt();
    }
}
