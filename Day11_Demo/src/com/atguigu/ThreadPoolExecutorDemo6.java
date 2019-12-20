package com.atguigu;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 并发修改异常   java.util.ConcurrentModification
 * 栈溢出异常    java.lang.StackOverflowError
 * 堆溢出异常    java.lang.OutOfMemoryError
 * 阻塞队列满时抛出的异常 java.util.concurrent.RejectedExecutionException
 */
class Ticket60{
    private int number = 60;
    private int count = 0;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName()+"卖出了\t"+(++count)+"\t还剩\t"+(--number));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadPoolExecutorDemo6 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                2,
                6,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
                );
        Ticket60 ticket60 = new Ticket60();
        try {
            for (int i = 0; i < 60; i++) {
                threadPool.execute(()->{
                    ticket60.sale();
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }

    }
}
