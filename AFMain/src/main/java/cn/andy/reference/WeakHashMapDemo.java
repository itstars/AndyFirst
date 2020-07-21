package cn.andy.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

/**
 * @Description 弱引用应用
 * @Author zhangheng
 * @Date 2020/7/21 13:14
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
//        CommonHashMap();
        MyWeakHashMap();
    }

    private static void MyWeakHashMap() {
        WeakHashMap<Integer,String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(1);
        String val = "WeakHashMap";
        weakHashMap.put(key,val);
        System.out.println(weakHashMap);
        key = null;
        System.out.println(weakHashMap);
        System.gc();
        System.out.println(weakHashMap+"\t "+weakHashMap.size());
    }

    private static void CommonHashMap() {
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String val = "HashMap";
        map.put(key,val);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map+"\t "+map.size());
    }
}
