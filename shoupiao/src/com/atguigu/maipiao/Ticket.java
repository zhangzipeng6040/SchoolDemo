package com.atguigu.maipiao;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ticket {

        private int count = 30;
        private Lock lock = new ReentrantLock();

        public void sale() {
            lock.lock();
            try {
                if (count > 0) {
                    System.out.println(Thread.currentThread().getName() + "卖出了" + (count--) + "\t 剩下:" + count);
                }
            } finally {
                lock.unlock();
            }
//        if (count > 0){
//            System.out.println(Thread.currentThread().getName()+"卖出了"+(count--)+"\t 剩下:"+count);
//        }
        }

}
