package com.atguigu;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket40{
    private int number = 30;
    private int count = 0;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+"卖出了\t"+(++count)+"还剩\t"+(--number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadPoolExecutorDemo4 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                3,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Ticket40 ticket40 = new Ticket40();
        try {
            for (int i = 0; i < 30; i++) {
                threadPool.execute(()->{
                    ticket40.sale();
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
