package cn.andy.threadlock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description 中级生产者消费者模式
 * 采用ReentrantLock和Condition的await和singal方法实现
*  * synchronized与lock区别
*  * 1.原始构成
*  *   synchronized 关联字 属于JVM层面，包括只有在同步代码块中才能调用的wait/nofity底层通过monitor对象来完成（monitorenter -> monitorexit:2次）；
*  *   Lock 类 属于api层面的锁，相对灵活。java.util.concurrent.locks.Lock。
*  * 2.使用方法
*  *   synchronized 不需要手动释放锁，当synchronized修饰代码块执行完后系统会自动让线程释放锁的占用
*  *   ReentrantLock 需要手动加锁和释放锁，如果没有主动释放锁，则可能出现死锁的现象。
*  * 3.等待是否可中断
*  *   synchronized 不可中断，除非代码抛出异常或者正常运行完成。
*  *   ReentrantLock 可中断，通过设置超时方法 tryLock(long timeout, TimeUnit unit) 或调用interrupt（）方法。
*  * 4.加锁是否公平
*  *   synchronized 非公平锁
*  *   ReentrantLock 默认为非公平锁，通过设置构造参数为true，可变为公平锁。
*  * 5.锁绑定多个条件condition，精确唤醒
*  *   synchronized 无法实现，只能随机唤醒一个线程（notify）或唤醒全部线程（notifyAll）
*  *   ReentrantLock 可以绑定多个condition，实现分组唤醒需要唤醒的线程，达到精确唤醒（xxx.signal）。
 * @Author zhangheng
 * @Date 2020/7/17 11:04
 */
public class ProductConsumeSecond {
    public static void main(String[] args) throws Exception{
        BreadShop shop = new BreadShop();
        new Thread(()->{ shop.product();},"Product").start();
        new Thread(()->{ shop.sale();},"Consume1").start();
        new Thread(()->{ shop.sale();},"Consume2").start();
        TimeUnit.SECONDS.sleep(30);
        shop.closeDoor();
    }
}

class BreadShop{
    private volatile boolean flag = true;
    private int breadNum= 10; //货架容量  标志位
    private Lock lock = new ReentrantLock();
    Condition product = lock.newCondition();
    Condition sale = lock.newCondition();
    public void product(){
        while(flag){
            lock.lock();
            try {
                //判断执行当前方法线程何时阻塞以及何时唤醒其他线程 -- 建议使用while 不用if
                while(breadNum != 0){
                    product.await();
                }
                //执行自己线程业务(每次耗时1秒可生产1块)
                System.out.println(Thread.currentThread().getName()+"\t 商店生产好1块蛋糕。");
                breadNum++;
                sale.signal();
                TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
                flag=false;
            }
            finally {
                lock.unlock();
            }
        }

    }

    public void sale(){
        while(flag){
            lock.lock();
            try {
                //判断执行当前方法线程何时阻塞 何时唤醒其他线程-- 建议使用while 不用if
                while(breadNum == 0){
                    System.out.println("面包没有了，请生产。");
                    sale.await();
                }
                //执行自己线程业务(每次耗时1秒买1块蛋糕)
                System.out.println(Thread.currentThread().getName()+"\t 进商店买走1块蛋糕,");
                breadNum--;
                System.out.println(Thread.currentThread().getName()+"\t 还剩"+breadNum+"块蛋糕。");
                product.signal();
                //TimeUnit.SECONDS.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
                flag = false;
            }
            finally {
                lock.unlock();
            }
        }
    }

    public void closeDoor(){
        this.flag =false;
    }
}
