package cn.andy;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Description 用来控制同时访问特定资源的线程数量，它通过协调各个线程，以保证合理的使用公共资源。
 * @Author zhangheng
 * @Date 2020/7/15 13:19
 */
public class semaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //同时访问资源的线程数量
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                try {
                    semaphore.acquire(); //抢占一个资源
                    System.out.println("*******************************"+Thread.currentThread().getName()+"号车驶入，" +
                            "剩余"+semaphore.availablePermits()+"个车位可用。");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();//释放资源
                    System.out.println(Thread.currentThread().getName()+"号车离开，" +
                            "剩余"+semaphore.availablePermits()+"个车位可用。*******************************");
                }
                },String.valueOf(i)).start();
        }
    }
}
