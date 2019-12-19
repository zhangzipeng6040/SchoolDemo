package com.atguigu;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket50{
    private int number = 40;
    private int count = 0;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number > 0){

                System.out.println(Thread.currentThread().getName()+"已卖出\t"+(++count)+"\t还剩\t"+(--number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ThreadPoolExecutorDemo5 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                1,
                3,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Ticket50 ticket50 = new Ticket50();
        try {
            for (int i = 0; i < 40; i++) {
                threadPool.execute(() -> {
                    ticket50.sale();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }


    }
}
