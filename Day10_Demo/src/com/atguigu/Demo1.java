package com.atguigu;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Demo1 {
    public static void main(String[] args) {
        /*//Map集合之不安全的线程,应采用concurrenthashmap
        Map<String, String> map = new ConcurrentHashMap<>();
        //Map<String, String > map = new HashMap<>();
        for (int i = 0; i < 40; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0,6));
                System.out.println(map);
            },String.valueOf(i)).start();

        }*/
        //为了解决list与set集合多线程不安全的问题,应采用 写实赋值 copyonwritearraylist
        List<String> list = new CopyOnWriteArrayList<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0,6));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }
}
