package cn.andy.threadpool;

import java.util.concurrent.*;

/**
 * @Description ThreadPoolExecutor 与 Executors工具类使用
 * @Author zhangheng
 * @Date 2020/7/17 10:22
 * Executor --> ExecuterService 接口
 * Java获取多线程方法
 * 1. 继承Thread   2. 实现Runnable(无返回值)  3. 实现Callable(有返回值)  4. 使用线程池 ThreadPoolExecutor
 * https://www.cnblogs.com/dafanjoy/p/9729358.html
 * ThreadPoolExecutor构造函数的参数含义如下：
     * corePoolSize:指定了线程池中的线程数量，它的数量决定了添加的任务是开辟新的线程去执行，还是放到workQueue任务队列中去；
     * maximumPoolSize:指定了线程池中的最大线程数量，这个参数会根据你使用的workQueue任务队列的类型，决定线程池会开辟的最大线程数量；
     * keepAliveTime:当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
     * unit:keepAliveTime的单位
     * workQueue:任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种；
     * threadFactory:线程工厂，用于创建线程，一般用默认即可；
     * rejectedExecutionHandler:拒绝策略,当任务太多来不及处理时，如何拒绝任务。
 *
 * workQueue任务队列
 * 一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列；
 * 1、直接提交队列：设置为SynchronousQueue队列，SynchronousQueue是一个特殊的BlockingQueue，它没有容量，没执行一个插入操作就会阻塞，需要再执行一个删除操作才会被唤醒，反之每一个删除操作也都要等待对应的插入操作。
 * 2、有界任务队列: 使用ArrayBlockingQueue实现,线程数量的上限与有界任务队列的状态有直接关系，如果有界队列初始容量较大或者没有达到超负荷的状态，线程数将一直维持在corePoolSize以下，反之当任务队列已满时，则会以maximumPoolSize为最大线程数上限。
 * 3、无界任务队列：使用LinkedBlockingQueue实现，线程池的任务队列可以无限制的添加新的任务，而线程池创建的最大线程数量就是你corePoolSize设置的数量，也就是说在这种情况下maximumPoolSize这个参数是无效的。
 * 4、优先任务队列：通过PriorityBlockingQueue实现，一个特殊的无界队列，它其中无论添加了多少个任务，线程池创建的线程数也不会超过corePoolSize的数量，只不过其他队列一般是按照先进先出的规则处理任务，而PriorityBlockingQueue队列可以自定义规则根据任务的优先级顺序先后执行。
 *
 *  RejectedExecutionHandler 拒绝策略处理
 * 为防止资源被耗尽，任务队列都会选择创建有界任务队列，需要来处理线程池"超载"的情况。ThreadPoolExecutor自带的拒绝策略如下：
 * 1、AbortPolicy策略：该策略会直接抛出异常，阻止系统正常工作；
 * 2、CallerRunsPolicy策略：如果线程池的线程数量达到上限，该策略会把任务队列中的任务放在调用者线程当中运行；
 * 3、DiscardOledestPolicy策略：该策略会丢弃任务队列中最老的一个任务，也就是当前任务队列中最先被添加进去的，马上要被执行的那个任务，并尝试再次提交；
 * 4、DiscardPolicy策略：该策略会默默丢弃无法处理的任务，不予任何处理。当然使用此策略，业务场景中需允许任务的丢失；
 *
 * ThreadPoolExecutor扩展
 * ThreadPoolExecutor扩展主要是围绕beforeExecute()、afterExecute()和terminated()三个接口实现的，
 * 1、beforeExecute：线程池中任务运行前执行
 * 2、afterExecute：线程池中任务运行完毕后执行
 * 3、terminated：线程池退出后执行
    protected void beforeExecute(Thread t,Runnable r) {
        System.out.println("准备执行："+ r.hashCode());
    }
    protected void afterExecute(Runnable r,Throwable t) {
        System.out.println("执行完毕："+t.hashCode());
    }
    protected void terminated() {
        System.out.println("线程池退出");
    }
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {
//        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor(); //单一线程池
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5); //固定条数线程池
//        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool(); //N个条数线程池
        ExecutorService threadPool = new ThreadPoolExecutor(5, 10,
                30, TimeUnit.MINUTES, new ArrayBlockingQueue<>(100),
                 new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r,"threadPool-"+r.hashCode());
                    }
                 },
                new ThreadPoolExecutor.CallerRunsPolicy());
        try {
            for (int i = 1; i <= 20; i++) {  //20个业务访问线程创建
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 执行打印业务逻辑。");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭线程池 重要
            threadPool.shutdown();
        }

    }
}
