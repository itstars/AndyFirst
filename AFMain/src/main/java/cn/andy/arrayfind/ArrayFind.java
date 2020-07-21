package cn.andy.arrayfind;

import java.util.Arrays;

/**
 * @Description 数据查找
 * @Author zhangheng
 * @Date 2020/7/21 14:31
 */
public class ArrayFind {

    public static void main(String[] args) {
        int[] numbers = {10,98,55,33,59,2,1,99,6};
        int findNums = 33;
//        lineFindSearch(findNums,numbers);
        binarySearch(findNums,numbers);
    }
    /**
     * 二分法查找：前提 必须先排好序
     */
    private static void binarySearch(int findNums, int[] numbers) {
        Arrays.sort(numbers);
        System.out.println("排序后数组为："+Arrays.toString(numbers));
        int index = -1;
        int start = 0, end = numbers.length-1;
        while (start<=end){
            int middle = (start+end)/2;
            if(numbers[middle] == findNums){
                index=middle;
                break;
            }
            else if(numbers[middle] < findNums){
                start = middle+1;
            }else {
                end = middle-1;
            }
        }
        printResult(findNums, index);
    }

    /**
     * 线性查找：按数据从头到尾一个一个的找
     */
    private static void lineFindSearch(int findNums,int[] numbers) {
        int index = -1; //记录索引位置
        for(int i=0;i<=numbers.length;i++){
            if(findNums==numbers[i]){
                index=i;
                break;
            }
        }
        printResult(findNums, index);
    }

    private static void printResult(int findNums, int index) {
        if(index == -1){
            System.out.println("没有找到要查询的数据");
        }else {
            System.out.println("要查找的数据"+findNums+"在数组中下标为："+index+"。");
        }
    }
}
