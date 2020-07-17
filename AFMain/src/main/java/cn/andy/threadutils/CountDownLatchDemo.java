package cn.andy.threadutils;

import java.util.concurrent.CountDownLatch;

/**
 * @Description 使一个线程在等待其他一些线程完成各自工作之后，再继续执行
 * @Author zhangheng
 * @Date 2020/7/15 12:54
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        try {
            CountDownLatch latch = new CountDownLatch(6);
            for (int i = 1; +i <=6; i++) {  //其他6个线程
                new Thread(()->{
                    System.out.println(""+Thread.currentThread().getName()+"号选手已到终点。");
                    latch.countDown();
                },String.valueOf(i)).start();
            }
            latch.await(); //等待的线程
            System.out.println("============比赛结束!==============");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
