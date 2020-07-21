package cn.andy.arrayfind;

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * @Description 数据排序   Arrays.sort、 BubbleSort
 * @Author zhangheng
 * @Date 2020/7/21 14:50
 */
public class ArraySort {
    public static void main(String[] args) {
        int[] numbers = {10,98,55,33,59,2,1,99,6};
        bubbleSort(numbers);
        System.out.println(Arrays.toString(numbers));
    }


    public static void bubbleSort(int[] arr) {
        if (arr.length <= 1) return;       //如果只有一个元素就不用排序了

        for (int i = 0; i < arr.length; i++) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            for (int j = 0; j < arr.length - i - 1; j++) {        //此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面，
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                if (arr[j] > arr[j + 1]) {        //即这两个相邻的数是逆序的，交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag) break;//没有数据交换，数组已经有序，退出排序
        }
    }
}
