package com.sunday.javathread.learn1;

public class ThreadStack {
    public static   int counter=0;
    public static void main(String[] args) {

        new Thread(null, new Runnable() {
            @Override
            public void run() {
                try {
                    add(0);
                }catch (Error e){
                   System.out.println(counter);
                }

         }
            private      void add(int i){
                counter++;

                    add(i+1);

            }
        }, "test", 1<<24).start();

    }
}
