package com.atguigu.maipiao;
interface Hello{
    public void hello();

}
public class test4 {
    public static void main(String[] args) {
        Hello hello = () -> {
            System.out.println("你好");
        };
        hello.hello();

    }

}
