package cn.andy.threadlock;

import java.util.concurrent.TimeUnit;

/**
 * @Description synchronized 对象锁   static synchronized 全局锁
 * @Author zhangheng
 * @Date 2020/7/20 12:49
 * 1. 锁的是当前对象 this，被锁定后，其它线程都无法进入到当前对象的其它被synchronized 修饰（锁定）的方法。
 * 2. 一个对象里面如果有多个synchronized方法，某一个时刻内，只要一个线程去调用其中的一个synchronize方法了，其它线程只能等待。
 * 3. 对象内不被synchronized修饰的普通方法，其它线程可以正常访问。
 * 4. 被静态static修饰的同步方法，锁的不在是当前对象this，而是Class的模板类。
 */
public class SyncLockDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone1 = new Phone();
        new Thread(()->{
            try {
                phone.sendMsg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();
        new Thread(()->{
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
        new Thread(()->{phone.openApp(); },"C").start();
        new Thread(()->{
            try {
                phone1.sendMsg();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A1").start();

        new Thread(()->{
            try {
                phone1.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B1").start();


    }
}

class Phone{
    public static synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("线程"+Thread.currentThread().getName()+"：*******Sending Email*******");
    }
    public synchronized void sendMsg() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("线程"+Thread.currentThread().getName()+"：*******Sending Message*******");
    }
    public void openApp(){
        System.out.println("线程"+Thread.currentThread().getName()+"：******Open App*******");
    }
}
