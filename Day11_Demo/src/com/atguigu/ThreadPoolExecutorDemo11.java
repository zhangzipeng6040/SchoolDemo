package com.atguigu;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket10{
    private int number = 100;
    private int count = 0;
    Lock lock = new ReentrantLock();
    public void sales(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"已卖出:"+(++count)+"\t还剩下+"+(--number));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadPoolExecutorDemo11 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                6,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Ticket10 ticket10 = new Ticket10();
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(()->{
                ticket10.sales();
            });
        }
    }
}
