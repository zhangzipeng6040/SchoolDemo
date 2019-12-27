package com.atguigu;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticker10{
    private int number = 100;
    private int count = 0 ;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"现已卖出:"+(++count)+"\t还剩下:"+(--number));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
public class ThreadPoolExecutorDemo10 {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                3,//核心线程数
                6,//最大线程数
                1L,//等待时间
                TimeUnit.SECONDS,//等待时间的单位
                new LinkedBlockingQueue<>(4),//候补队列
                Executors.defaultThreadFactory(),//工厂模式
                new ThreadPoolExecutor.CallerRunsPolicy()//候补队列已满后的,任务的抛弃机制,该CALLER代表将任务返回至调用者处执
                );
        Ticker10 ticket = new Ticker10();
        for (int i = 0; i < 100; i++) {
            threadPoolExecutor.execute(()->{
                ticket.sale();
            });
        }
    }
}
