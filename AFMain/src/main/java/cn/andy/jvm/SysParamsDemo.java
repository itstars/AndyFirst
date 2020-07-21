package cn.andy.jvm;

/**
 * @Description JVM常用参数
 * @Author zhangheng
 * @Date 2020/7/21 9:14
 * 一、参数类型
 *  1. 标准参数（-） 基本不变，所有的JVM实现都必须实现这些参数的功能，而且向后兼容。  eg: -version
 *  2. 非标准参数（-X） 默认jvm实现这些参数的功能，但是并不保证所有jvm实现都满足，且不保证向后兼容。
 *  3. 非Stable参数（-XX）：此类参数各个jvm实现会有所不同，将来可能会随时取消，需要慎重使用。
 *     ① boolean类型  +表示开启 -表示关闭  eg: -XX:+PrintGCDetails;   -XX:-UseSerialGC  -XX:-UseParallelGC
 *     ② KV设置类型  -XX:参数名称=值  eg: -XX: MaxTenuringThreshold = 15  -XX:MetaspaceSize=128m
 *     ③ 经典参数： -XX:InitialHeapSize(-Xms)    -XX:MaxHeapSize(-Xmx)   -XX:MaxNewSize(-Xmn)   -Xss:栈空间大小 默认 1M
 * 二、查看参数配置
 * 1.运行时查看
 *    ① jps 查看java进程号
 *    ② jinfo -flag 参数名称 pid   查看输入参数名称的配置值
 * 2.查看jvm默认命令
 *   ① java -XX:PrintFlagsInitial 查看jvm初始配置   以 = 修饰
 *   ② java -XX:PrintFlagsFinal 查看jvm根据不同电脑配置修改更新的一些初始化配置  以 := 修饰
 *   ③ java -XX:+PrintCommandLinesFlags  查看命令行参数
 * 三、常用参数
 *  -Xms: 初始堆大小内存 默认物理内存的1/64 等价于 -XX:InitialHeapSize
 *  -Xmx: 最大堆内存 默认为物理内存的1/4 等价于 -XX：MaxHeapSize
 *  -Xss: 单个线程栈的大小 默认为 512 --  1024 K  等价于-XX:ThreadStackSize
 *  -Xmn: 新生代最大空间 默认堆空间的1/3  等价于-XX:MaxNewSize
 *  -XX:MetaspaceSize  设置元空间大小
 *  -XX:+PrintGCDetails 打印GC详细信息
 *  -XX;SurvivorRatio 设置新生代中Eden和From/to区比例 默认 8:1:1 即-XX;SurvivorRatio=8
 *  -XX:NewRatio 设置新生代与老年代的比例  默认 1:2 即 -XX:NewRatio=2
 *  -XX:MaxTenuringThreshold 设置垃圾的最大年龄 即经过多次GC后仍存活的变量 默认为15  且必须在0-15之间
 */
public class SysParamsDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("======JVM运行时查看参数=========");
        Thread.sleep(Integer.MAX_VALUE);
    }
}
