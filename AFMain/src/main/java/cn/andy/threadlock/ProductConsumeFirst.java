package cn.andy.threadlock;

/**
 * @Description 初级消费生产者模式
 * 采用同步代码块synchronized 及 利用 Object的notify和wait实现
 * @Author zhangheng
 * @Date 2020/7/17 11:02
 */
public class ProductConsumeFirst {

    public static void main(String[] args) {
        MethodClass methodClass = new MethodClass();
        Thread t1 = new Thread(() -> {
            try {
                methodClass.product();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t2");
        Thread t3 = new Thread(() -> {
            try {
                methodClass.customer();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }, "t3");
        t1.start();
        t2.start();
        t3.start();

    }
}

class MethodClass {
    // 定义生产最大量
    private final int MAX_COUNT = 20;

    int productCount = 0;

    public synchronized void product() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(1000);
            //判断执行当前方法线程何时阻塞 何时唤醒其他线程-- 建议使用while 不用if 防止虚假唤醒 多个生成消费争抢出现错误
            while (productCount >= MAX_COUNT) {

                System.out.println("货舱已满,不必再生产。");
                wait();
            }
            productCount++;

            notifyAll();
        }
    }

    public synchronized void customer() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ":::run:::" + productCount);
            Thread.sleep(1000);
            //建议使用while 不用if 防止虚假唤醒 多个生成消费争抢出现错误
            while (productCount <= 0) {
                System.out.println("货舱已无货...无法消费");
                wait();
            }
            productCount--;

            notifyAll();
        }
    }
}
