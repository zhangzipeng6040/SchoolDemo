package com.atguigu;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
//Semaphore 是一个计数信号量，必须由获取它的线程释放。
//常用于限制可以访问某些资源的线程数量，例如通过 Semaphore 限流。
public class SemaphoreDemo {
    public static void main(String[] args) {
        //信号量,限制访问的数量
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                boolean flag = false;
                try {
                    //获取许可
                    semaphore.acquire();
                    flag = true;
                    System.out.println(Thread.currentThread().getName()+"\t抢到车位");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName()+"\t离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (flag) {
                        //释放
                        semaphore.release();
                    }
                }
            },String.valueOf(i)).start();
        }
    }
}
