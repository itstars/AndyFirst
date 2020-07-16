package cn.andy.blockquene;

import java.util.Objects;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 消费生产者模式
 * @Author zhangheng
 * @Date 2020/7/16 13:31
 */
public class ProdnctAndConsumeDemo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);
        BreadShop shop = new BreadShop(blockingQueue);
        new Thread(()->{
            try {
                shop.product();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Product").start();

        new Thread(()->{
            try {
                shop.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Consumer").start();

        TimeUnit.SECONDS.sleep(15);

        shop.closeDoor();

    }
}

class BreadShop{

    private volatile boolean flag = true;
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public BreadShop (BlockingQueue<String> blockingQueue){
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void product() throws Exception{
        String product = null;
        boolean offer;
        while (flag){
            product = atomicInteger.incrementAndGet()+"";
            offer = blockingQueue.offer(product, 3L, TimeUnit.SECONDS);
            if (offer) {
                System.out.println(Thread.currentThread().getName()+"\t 成功生产"+product+"号蛋糕到柜台。");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 生产"+product+"号蛋糕到柜台失败。");
            }
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println(Thread.currentThread().getName()+"\t  做面包师傅下班了！");
    }

    public void consume() throws Exception{
        String result = null;
        while (flag){
            result = blockingQueue.poll(3L, TimeUnit.SECONDS);
            if(Objects.isNull(result)){
                flag = false;
                System.out.println(Thread.currentThread().getName()+"\t 等待3秒后没有获取蛋糕，离开商店");
            }else{
                System.out.println(Thread.currentThread().getName()+"\t 获取蛋糕成功!");
            }
        }
    }

    public void closeDoor(){
        this.flag = false;
    }
}
