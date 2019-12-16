package com.atguigu.maipiao;
class Out3{
    public void out3(){
        System.out.println("我不好");
    }
}
public class test3 {
    public static void main(String[] args) {
        Out out = new Out();
        Out2 out2 = new Out2();
        Out3 out3 = new Out3();

        //线程的写法
        new Thread(() -> {out.sayHello(); },"A").start();
        new Thread(() -> { out2.abs(1,2); },"B").start();
        new Thread(() -> { out3.out3();},"C").start();
    }
}
