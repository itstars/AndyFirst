package cn.andy.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @Description 引用队列 : 软引用、弱引用、虚引用在真正被gc回收前，都会放到引用队列中保存。
 * @Author zhangheng
 * @Date 2020/7/21 13:39
 * 当关联的引用队列中有数据的时候，意味着堆内存中的对象被回收，我们可以在对象真正被gc前做一些处理。
 * 例：Object的finalize()方法。
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(o1,referenceQueue);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("-----------------------------------");
        o1=null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o1);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }
}
