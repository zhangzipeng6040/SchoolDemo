package com.atguigu;


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket30{
    private int count = 0;
    private int number = 20;
    Lock lock = new ReentrantLock();
    public void sale(){
       lock.lock();
       try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+"卖出\t"+(++count)+"\t还剩"+(--number));
            }
       } catch (Exception e) {
           e.printStackTrace();
       } finally {
           lock.unlock();
       }

    }
}
public class ThreadPoolExecutorDemo3 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                5,
                8,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(5),//阻塞队列的等待线程数
                Executors.defaultThreadFactory(),//线程的工厂模板
                new ThreadPoolExecutor.CallerRunsPolicy()//阻塞队列已满后,将任务返回至调用者处执行
                //new ThreadPoolExecutor.AbortPolicy()阻塞队列已满后进行抛异常
                //new ThreadPoolExecutor.DiscardPolicy()阻塞队列已满后,抛弃等待的线程,不返回异常
                //new ThreadPoolExecutor.DiscardOldestPolicy()阻塞队列已满后,抛弃等待最久的线程,不返回异常
                );


            Ticket30 ticket= new Ticket30();
            for (int i = 0; i < 20; i++) {
                threadPool.execute(()->{
                    ticket.sale();
                });
            }

            threadPool.shutdown();

    }
}
