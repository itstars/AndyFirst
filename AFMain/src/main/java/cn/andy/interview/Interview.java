package cn.andy.interview;

/**
 * @Description 类各代码块执行顺序
 * @Author zhangheng
 * @Date 2020/7/20 14:13
 */
public class Interview {
    public static void main(String[] args) {
        Interview interview = new Interview();
//        System.out.println(interview.sayHello());
        System.out.println(Interview.sayWord());
    }

    public  Interview(){
        System.out.println("============Interview 构造方法============");
    }
    {
        System.out.println("===========Interview 普通代码块============");
    }
    static {
        System.out.println("=========Interview static 代码块============");
    }

    public String sayHello(){
        System.out.println("==============Interview 普通方法===============");
        return "end";
    }

    public static String sayWord(){
        System.out.println("==============Interview 静态static方法===============");
        return "end";
    }
}
