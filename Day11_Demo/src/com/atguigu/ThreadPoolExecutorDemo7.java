package com.atguigu;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket70{
    private int number = 70;
    private int count = 0;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if(number > 0){
                System.out.println(Thread.currentThread().getName()+"卖了\t"+(++count)+"\t还剩\t"+(--number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadPoolExecutorDemo7 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                3,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Ticket70 ticket70 = new Ticket70();
        try {
            for (int i = 0; i < 70; i++) {
                threadPoolExecutor.execute(() -> {
                    ticket70.sale();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }
    }
}
