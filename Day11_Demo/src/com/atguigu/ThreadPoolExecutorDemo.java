package com.atguigu;

import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket2{
    private int number =20;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出了" + (number--) + "\t 剩下:" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,//核心线程数
                5,//最大线程数
                1L,//线程最大空闲时间
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingQueue<>(3),//阻塞队列,可等待的最大线程数
                Executors.defaultThreadFactory(),//线程创建工厂
                new ThreadPoolExecutor.DiscardPolicy()//拒绝策略4   对拒绝任务直接无声抛弃，没有异常信息。
                //new ThreadPoolExecutor.DiscardOldestPolicy()//拒绝策略3 抛弃队列里面等待最久的一个线程,不返回异常信息
                //new ThreadPoolExecutor.CallerRunsPolicy()//拒绝策略2  在调用threadPool中运行被拒绝的任务
                //new ThreadPoolExecutor.AbortPolicy()//拒绝策略1    抛出java.util.concurrent.RejectedExecutionException异常
                );
        try {
            for (int i = 0; i < 13; i++) {
                final int finalI = i;
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t 受理业务"+"\t 客户号"+ finalI);
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
