package com.atguigu.maipiao;
class Out2 {
    public void abs(int a,int b){
        System.out.println(a+b);
    }
}
public class test2  {
    public static void main(String[] args) {
        Out2 out2 = new Out2();
        Out out = new Out();
        new Thread(() -> { out.sayHello(); },"A").start();
        new Thread(() -> {out2.abs(5,6); },"B").start();
    }
}
