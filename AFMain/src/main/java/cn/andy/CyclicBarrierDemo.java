package cn.andy;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Description 一个线程组的线程需要等待所有线程完成任务后再继续执行下一次任务
 * @Author zhangheng
 * @Date 2020/7/15 13:08
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(6,()->{
            //需要等待其他6个线程任务完成后才能执行
            System.out.println("==========所有选手就位，比赛开始！============");
        });
        for (int i = 1; i <=6; i++) {  //其他6个线程
            new Thread(()->{
                System.out.println(""+Thread.currentThread().getName()+"号选手已准备就绪。");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }
}
