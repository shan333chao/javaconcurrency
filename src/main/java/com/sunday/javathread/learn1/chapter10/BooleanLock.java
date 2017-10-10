package com.sunday.javathread.learn1.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BooleanLock implements Lock {
    //the init Value is true indicated the lock is have be got
    //The initValue it false indicated the lock free(other thread can get this)
    private  boolean intValue;
    private  final Collection<Thread> blockThreadCollection =new ArrayList<>();
    private  Thread currentThread;
    public  BooleanLock(){
        this.intValue=false;
    }
    @Override
    public synchronized void lock() throws InterruptedException {
        while(intValue){
            blockThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockThreadCollection.remove(Thread.currentThread());
        this.intValue=true;
        currentThread=Thread.currentThread();
    }



    @Override
    public synchronized void unlock() {
        if(currentThread==Thread.currentThread()){
        this.intValue=false;
        System.out.println(Thread.currentThread()+"release the lock monitor.");
        this.notifyAll();}

    }
    @Override
    public synchronized void lock(long mills)throws  InterruptedException,TimeOutException{
        if(mills<=0){
            lock();

        }
        long hasRemaining =mills;
        long endTime=System.currentTimeMillis()+mills;
        while(intValue){
            if(hasRemaining<=0){
                throw  new TimeOutException("Time out");
            }
            blockThreadCollection.add(Thread.currentThread());
            hasRemaining=System.currentTimeMillis()-endTime;
        }
        this.intValue=true;
        this.currentThread=Thread.currentThread();
    }
    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection( blockThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return  blockThreadCollection.size();
    }
}
