package cn.andy.interview;

/**
 * @Description 子父类执行顺序
 * 静态代码块先行  其它先父类后子类
 * @Author zhangheng
 * @Date 2020/7/20 14:17
 * =========Interview static 代码块============
 * =========InterviewChildren static 子类 代码块============
 * ===========Interview 普通代码块============
 * ============Interview 构造方法============
 * ===========InterviewChildren 子类 普通代码块============
 * ============InterviewChildren 子类 构造方法============
 * ==============InterviewChildren 子类 静态static方法===============
 */
public class InterviewChildren extends  Interview {

    public static void main(String[] args) {
        InterviewChildren children = new InterviewChildren();
//        System.out.println(children.sayHello());
        System.out.println(InterviewChildren.sayWord());
    }

    public InterviewChildren(){
        System.out.println("============InterviewChildren 子类 构造方法============");
    }
    {
        System.out.println("===========InterviewChildren 子类 普通代码块============");
    }
    static {
        System.out.println("=========InterviewChildren static 子类 代码块============");
    }

    public String sayHello(){
        System.out.println("==============InterviewChildren 子类 重写父类普通方法===============");
        return "子类 end";
    }

}
