package com.atguigu;



import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{

    private int number = 50 ;
    Lock lock = new ReentrantLock();
    public void sale(){
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出" + (number--) + "\t 剩下:" + number);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
public class NewShouPiao {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Ticket ticket = new Ticket();
        try {
            for (int i = 0; i < 50; i++) {
                executorService.execute(()->{ticket.sale();});
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
