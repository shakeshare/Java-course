package test;
import java.util.ArrayList;
import java.util.Collections;

public class add {
    public static void main(String[] args) throws Exception {
        int a = 10;
        int b = 20;
        int sum = a + b;
        System.out.println("The sum of " + a + " and " + b + " is: " + sum);
        ArrayList<Integer> list = new ArrayList<>();
        // 示例：基本操作
        list.add(5);                    // 添加元素
        list.add(15);
        list.add(25);
        int first = list.get(0);        // 访问元素
        System.out.println("first: " + first);
        list.remove(Integer.valueOf(15)); // 按值删除
        list.remove(0);                 // 按索引删除
        System.out.println("size: " + list.size());
        // 遍历
        for (Integer v : list) {
            System.out.println("value: " + v);
        }
        // 排序示例
        list.add(42);
        list.add(1);
        Collections.sort(list);
        System.out.println("sorted: " + list);
    }
}
