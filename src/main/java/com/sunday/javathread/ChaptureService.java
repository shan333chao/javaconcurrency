package com.sunday.javathread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class ChaptureService {
    final static LinkedList<Controller>  linkedList=new LinkedList<Controller>();
    private  static  final  int MAX_WORKER=10;
    public static void main(String[] args) {
        List<Thread> worker=new ArrayList<>();
        List<String> list=new ArrayList<>(10);

        IntStream.range(1,1000).forEach(i->{
            list.add("M"+i);
        });
        list.stream().map(ChaptureService::createCaptureThread).forEach(t->{
            worker.add(t);
            t.start();

        });
        worker.stream().forEach(t->{
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Optional.of("All of capture work finished").ifPresent(System.out::println);

    }

    private  static  Thread createCaptureThread(String name){
        return new Thread(name){
            @Override
            public void run() {
            Optional.of("The worker "+Thread.currentThread().getName()+"begin capture data").ifPresent(System.out::println);
            synchronized (linkedList){
                while (linkedList.size()>MAX_WORKER){
                    try {
                        linkedList.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                linkedList.addLast(new Controller());
                Optional.of("The worker "+Thread.currentThread().getName()+" is working    ").ifPresent(System.out::println);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (linkedList){
                    Optional.of("The worker "+Thread.currentThread().getName()+" is end    ").ifPresent(System.out::println);
                    linkedList.removeFirst();
                    linkedList.notifyAll();
                }
            }
            }
        };
    }

    private  static  class  Controller{


    }
}
