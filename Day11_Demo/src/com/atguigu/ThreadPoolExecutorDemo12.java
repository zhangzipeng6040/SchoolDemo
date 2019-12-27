package com.atguigu;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket11{
    private int number = 110;
    private int count = 0;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"已卖出"+(++count)+"\t还剩下:"+(--number));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadPoolExecutorDemo12 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,+
                6,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        Ticket11 ticket11 = new Ticket11();
        for (int i = 0; i < 110; i++) {
            threadPoolExecutor.execute(()->{
              ticket11.sale();
            });
        }
    }
}
