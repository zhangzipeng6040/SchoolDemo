package com.atguigu.maipiao;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//线程     操作    资料库
public class shoupiao {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        new Thread(() -> { for (int i = 0; i < 34; i++) ticket.sale(); },"A").start();
        new Thread(() -> { for (int i = 0; i < 34; i++) ticket.sale(); },"B").start();
        new Thread(() -> { for (int i = 0; i < 34; i++) ticket.sale(); },"C").start();

    }
}


