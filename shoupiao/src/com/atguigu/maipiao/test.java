package com.atguigu.maipiao;

class Out{
    public void sayHello(){
        System.out.println("你好");
    }
}
public class test {
    public static void main(String[] args) {
        Out out = new Out();
        new Thread(() -> { out.sayHello(); },"A").start();

    }
}
