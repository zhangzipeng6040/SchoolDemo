package com.atguigu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* * 多线程之间按顺序调用，实现A先干->B次之->C最后
         *
         * 三个线程启动，要求如下：
         *
         * AA打印5次，BB打印10次，CC打印15次
         * 接着
         * AA打印5次，BB打印10次，CC打印15次
         * ......来10轮*/
class ShareResource{
    private int flag = 1; //1=A 2=B 3=C
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    public Condition condition(int i){
        List<Condition> list = new ArrayList<>();
        list.add(c1);
        list.add(c2);
        list.add(c3);
        return list.get(i);
    }
    //判断是哪个进程
    public void print(int a, int b) throws InterruptedException {
        lock.lock();
        try {
            while (flag != a){
                condition(a-1).await();
            }
            for (int i = 0; i < b; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            if(flag == 3){
                flag = 1;
                condition(0).signal();
            }else {
                flag = a+1;
                condition(a).signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
public class ThreadOrderAccess {
    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();
        for (int i=1; i<=3;i++) {
            int a = i;
            int b = 5*i;
            new Thread(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        shareResource.print(a, b);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },""+i).start();
        }

    }
}
