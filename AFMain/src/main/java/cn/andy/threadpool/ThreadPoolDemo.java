package cn.andy.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description Executors工具类使用
 * @Author zhangheng
 * @Date 2020/7/17 10:22
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        threadPool.submit(()->{
            System.out.println(Thread.currentThread().getName()+"\t ");
        });

    }
}
