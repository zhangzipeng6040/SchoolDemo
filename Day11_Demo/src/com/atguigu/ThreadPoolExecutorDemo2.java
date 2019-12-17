package com.atguigu;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket3{
    private int number = 10;
    Lock lock = new ReentrantLock();
    public void sale2(){
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()+"卖出了"+(--number)+"\t 还剩"+number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
public class ThreadPoolExecutorDemo2 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,//固定的核心线程数
                5,//最大线程数
                1L,//空闲线程的等待时间
                TimeUnit.SECONDS,//时间单位
                new LinkedBlockingQueue<>(3),//阻塞队列的等待任务数
                Executors.defaultThreadFactory(),
                //new ThreadPoolExecutor.AbortPolicy()//阻塞队列已满后的拒绝策略,抛出异常 RejectedExecutionException
                new ThreadPoolExecutor.CallerRunsPolicy()//拒绝策略,调用threadPool的调用者来运行被拒绝的任务
        );
        try {
            Ticket3 ticket3 = new Ticket3();
            for (int i = 0; i < 10; i++) {
                threadPool.execute(() -> {
                    ticket3.sale2();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}

