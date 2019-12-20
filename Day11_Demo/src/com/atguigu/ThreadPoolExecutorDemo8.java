package com.atguigu;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket80{
    private int count = 0;
    private int number =80;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"卖出了:"+(++count)+"还剩下:"+(--number));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadPoolExecutorDemo8 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(4),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Ticket80 ticket80 = new Ticket80();
        try {
            for (int i = 0; i < 80; i++) {
                threadPoolExecutor.execute(() -> {
                    ticket80.sale();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
