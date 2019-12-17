package com.atguigu;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //使用offer进行添加时,若阻塞队列已满,若在限定的时间内offer未完成执行,则自行中断运行,且返回false
        System.out.println(blockingQueue.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("c", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue.offer("d", 2L, TimeUnit.SECONDS));
        //使用poll进行移除时,若阻塞队列已空,在限定时间内poll未完成执行,则自行中断,且返回null
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));










        /*BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        //阻塞队列,使用put进行添加时,若队列已满,则队列会一直阻塞线程直到put执行结束或响应中断
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
//        System.out.println("44444");
//        blockingQueue.put("d");
//        System.out.println("55555");
        //阻塞队列,使用take()进行取出时,若队列已空,则队列会一直阻塞线程直到take执行结束或响应中断
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        System.out.println("7777");
        blockingQueue.take();
        System.out.println("8888");*/



        /*BlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(3);
        //使用add添加时,若队列已满 则抛IllegalStateException异常
        System.out.println(arrayBlockingQueue.add("a"));
        System.out.println(arrayBlockingQueue.add("b"));
        System.out.println(arrayBlockingQueue.add("c"));
//        System.out.println(arrayBlockingQueue.add("d"));
        //使用remove移除时,若队列已空,则抛NoSuchElementException
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove());*/




        /*//阻塞队列   //使用Offer添加时 若队列已满添加失败,则返回false
        BlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("d"));
        //使用poll移除时,若队列已空则返回   null
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/
    }
}
