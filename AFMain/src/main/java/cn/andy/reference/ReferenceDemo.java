package cn.andy.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @Description Java引用类型  强引用 软引用 弱引用 虚引用
 * @Author zhangheng
 * @Date 2020/7/21 12:50
 * 1.强引用：对象只要有引用 就不进行垃圾回收 造成OOM的主要原因。  默认支持模式
 * 以下引用均为避免OOM
 * 2.软引用：对象在系统内存充足时，不会被回收；系统内存不足时，会被回收。 对内存敏感的程序 比如高速缓存、图片缓存加载等
 * 3.弱引用：只要存在gc,弱引用的对象就会被回收，不考虑内存是否充足与否。 比如Redis缓存、图片缓存加载 WeakHashMap等
 * 4.虚引用：虚引用对象跟没有任何引用一样（始终为null），任何时候都可能被回收，不能单独使用，需要通过ReferenceQueue联合使用。
 *         被gc后放入引用队列ReferenceQueue中，在真正被清除之前可以做一些操作起到通知/标识作用。 比如Object的finalize()方法。
 */
public class ReferenceDemo {

    public static void main(String[] args) {
//        StrongReference();
//        SoftReference_MemoryEnough();
//        SoftReference_MemoryNotEnough();
//        WeakReference();
          PhantomReference();
    }

    private static void PhantomReference() {
        Object obj1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> obj2 = new PhantomReference<>(obj1,referenceQueue);
        System.out.println(obj1);
        System.out.println("虚引用对象在gc前，obj2："+obj2.get());
        System.out.println("引用队列："+referenceQueue.poll());
        obj1=null;
        System.gc();
        System.out.println("---------------------------------");
        System.out.println(obj1);
        System.out.println("虚引用对象在gc后，obj2："+obj2.get());
        System.out.println("引用队列："+referenceQueue.poll());
    }
    private static void WeakReference() {
        Object obj1 = new Object();
        WeakReference<Object> obj2 = new WeakReference<>(obj1);
        obj1=null;
        System.gc();
        System.out.println(obj1);
        System.out.println("弱引用对象在gc后，obj2："+obj2.get());
    }

    private static void StrongReference() {
        Object obj1 = new Object();
        Object obj2 = obj1;
        obj1=null;
        System.gc();
        System.out.println(obj1);
        System.out.println("强引用对象在进行gc后，obj2："+obj2);
    }
    private static void SoftReference_MemoryEnough() {
        Object obj1 = new Object();
        SoftReference<Object> obj2 = new SoftReference<>(obj1);
        obj1=null;
        System.gc();
        System.out.println(obj1);
        System.out.println("软引用对象内存充足时gc后，obj2："+obj2.get());
    }
    private static void SoftReference_MemoryNotEnough() {
        Object obj1 = new Object();
        SoftReference<Object> obj2 = new SoftReference<>(obj1);
        obj1=null;
//        System.gc();  //内存不够时会自动gc
        try {
           byte [] bytes = new byte[3000000*1024*1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(obj1);
            System.out.println("软引用对象内存不足时gc后，obj2："+obj2.get());
        }
    }
}
