package cn.andy.jvm;

/**
 * @Description 类加载器
 * @Author zhangheng
 * @Date 2020/7/20 9:58
 * Java默认提供的内置三个ClassLoader，采用双亲委托机制，防止核心类库被篡改。
 * 1.BootStrap ClassLoader
 * 称为启动类加载器，是Java类加载层次中最顶层的类加载器，负责加载JDK中的核心类库，如：rt.jar、resources.jar、charsets.jar等，这个ClassLoader完全是JVM自己控制的，需要加载哪个类，怎么加载都是由JVM自己控制，别人也访问不到这个类，所以BootStrap ClassLoader不遵循委托机制(后面再阐述什么是委托机制)，没有子加载器。
 * 2.EtxClassLoader
 * 称为扩展类加载器，负责加载Java的扩展类库，Java 虚拟机的实现会提供一个扩展库目录，该类加载器在此目录里面查找并加载 Java 类。默认加载JAVA_HOME/jre/lib/ext/目下的所有jar。
 * 3. AppClassLoader
 * 称为系统类加载器，负责加载应用程序classpath目录下的所有jar和class文件。一般来说，Java 应用的类都是由它来完成加载的。可以通过 ClassLoader.getSystemClassLoader()来获取它。我们可以通过System.getProperty(“java.class.path”) 来查看 classpath。
 *
 *
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
//        System.out.println("*****************BootStrapClassLoader******************");
//        System.out.println(System.getProperty("sun.boot.class.path"));
//        System.out.println("*****************EtxClassLoader*************************");
//        System.out.println(System.getProperty("java.ext.dirs"));
//        System.out.println("*****************AppClassLoader*************************");
//        System.out.println(System.getProperty("java.class.path"));

        //双亲委派机制
        Person person = new Person();
        System.out.println(person.getClass().getClassLoader()); // AppClassLoader
        System.out.println(person.getClass().getClassLoader().getParent());  //EtxClassLoader
        System.out.println(person.getClass().getClassLoader().getParent().getParent());  //BootStrap ClassLoader  null

    }
}

class Person{
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
