package MyFarm2;

import java.util.ArrayList;

/* 
1)在原有简单开心农场程序的基础上，进一步掌握 Java 集合框架的使用方法，重点掌握 ArrayList 的嵌套使用；理解如何使用二维集合结构管理对象；掌握通过二维 ArrayList 完成农场对象的添加、查询、删除、清空、遍历等操作。
2)程序中不再使用数组存储农场对象，而是使用二维 ArrayList 进行存储。在 Farm 类中定义初始化方法，用于创建指定行数的农场。
3)程序需要支持添加农场对象。创建对象时，需要输入对象编号和名称，并选择要添加到第几行(考虑index)。
4)程序需要能够输出当前农场中的所有对象和对象总数。
5)程序需要支持根据名称查找对象。用户输入名称后，程序在整个二维 ArrayList 中查找该对象。如果找到，需要输出对象的编号、类型、名称以及所在位置。
6)程序需要通过多态实现对任意农场对象的照料。用户输入行号和位置编号，程序找到该对象并调用其照料方法。
7)程序需要支持根据位置删除对象。用户输入行号和位置编号，程序删除该位置上的对象。
8)构建测试类 FarmTest，通过菜单和用户进行交互。（参考作业一的形式）
9)若有必要，可以在程序中使用其它的容器，如Set、Map等。
*/
public class farm2 {
    private ArrayList<ArrayList<FarmObject>> farmObjects;

    public void initFarm(int rows) {
        farmObjects = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            farmObjects.add(new ArrayList<>());
        }
        System.out.println("农场初始化完毕，共 " + rows + " 行。");
    }

    // 2） 程序中不再使用数组存储农场对象，而是使用二维 ArrayList 进行存储。在 Farm 类中定义初始化方法，用于创建指定行数的农场。

    public boolean AddFarmObject(FarmObject obj, int row) {

        if (row >= 0 && row < farmObjects.size()) {
            (farmObjects.get(row)).add(obj);
            System.out.println("添加成功");
            return true;
        } else {
            System.out.println("无效行号");
            return false;
        }
    }

    // 3)程序需要支持添加农场对象。创建对象时，需要输入对象编号和名称，并选择要添加到第几行(考虑index)。
    public FarmObject SearchFarmObject(String name) {

        for (int i = 0; i < farmObjects.size(); i++) {
            ArrayList<FarmObject> rowList = farmObjects.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                FarmObject obj = rowList.get(j);
                if (obj.name.equals(name)) {
                    System.out.println("在第 " + i + " 行，第 " + j + " 列找到该对象: " + obj);
                    return obj;
                }
            }
        }
        System.out.println("未找到名称为 " + name + " 的农场对象。");
        return null;
    }
    // 5)程序需要支持根据名称查找对象。用户输入名称后，程序在整个二维 ArrayList 中查找该对象。

    public void info(int row, int col) {
        if (farmObjects != null && row >= 0 && row < farmObjects.size()) {
            ArrayList<FarmObject> rowList = farmObjects.get(row);
            if (col >= 0 && col < rowList.size()) {
                System.out.println(rowList.get(col));
                return;
            }
        }
        System.out.println("位置无效");
    }

    public FarmObject RemoveObject(int row, int col) {

        if (farmObjects != null && row >= 0 && row < farmObjects.size()) {
            ArrayList<FarmObject> rowList = farmObjects.get(row);
            if (col >= 0 && col < rowList.size()) {
                FarmObject removed = rowList.remove(col);
                System.out.println("成功移除: " + removed);
                return removed;
            }
        }
        System.out.println("位置无效");
        return null;
    }

    // 7)程序需要支持根据位置删除对象。用户输入行号和位置编号，程序删除该位置上的对象。
    public void clearAll() {

        if (farmObjects != null) {
            for (ArrayList<FarmObject> rowList : farmObjects) {
                rowList.clear(); // 清空每一行的 ArrayList
            }
            System.out.println("已清空。");
        }
    }
    // 1) 掌握通过二维 ArrayList 完成农场对象的清空操作

    public void care(int row, int col) {

        if (farmObjects != null && row >= 0 && row < farmObjects.size()) {
            ArrayList<FarmObject> rowList = farmObjects.get(row);
            if (col >= 0 && col < rowList.size()) {
                (rowList.get(col)).care();
                return;
            }
        }
        System.out.println("无效位置");
    }

    // 6)程序需要通过多态实现对任意农场对象的照料。用户输入行号和位置编号，程序找到该对象并调用其照料方法。
    public static String check(FarmObject obj) {
        if (obj != null) {
            return obj.type;
        }
        return "Unknown type";
    }

    public void printAll() {

        System.out.println("当前农场对象列表：");
        int count = 0;
        for (int i = 0; i < farmObjects.size(); i++) {
            ArrayList<FarmObject> rowList = farmObjects.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                System.out.println("第" + i + "行，第" + j + "列: " + rowList.get(j));
                count++;
            }
        }
        System.out.println("当前对象总数: " + count);
    }

    // 4)程序需要能够输出当前农场中的所有对象和对象总数。
    public FarmObject getObject(int row, int col) {
        if (farmObjects != null && row >= 0 && row < farmObjects.size()) {
            ArrayList<FarmObject> rowList = farmObjects.get(row);
            if (col >= 0 && col < rowList.size()) {
                return rowList.get(col);
            }
        }
        System.out.println("无效位置");
        return null;
    }

}

abstract class FarmObject {

    public String name;
    public String type;
    public int idx;

    abstract public void care();

}

class Wheat extends FarmObject {
    public Wheat(int idx, String name) {
        this.name = name;
        this.type = "Wheat";
        this.idx = idx;
    }

    public String toString() {
        return "Wheat{name='" + name + "', type='" + type + "', idx='" + idx + "'}";
    }

    public void care() {
        System.out.println("照料小麦");
    }
}

class Corn extends FarmObject {
    public Corn(int idx, String name) {
        this.name = name;
        this.type = "Corn";
        this.idx = idx;
    }

    public String toString() {
        return "Corn{name='" + name + "', type='" + type + "', idx='" + idx + "'}";
    }

    public void care() {
        System.out.println("照料玉米");
    }
}

class Chicken extends FarmObject {
    public Chicken(int idx, String name) {
        this.name = name;
        this.type = "Chicken";
        this.idx = idx;
    }

    public String toString() {
        return "Chicken{name='" + name + "', type='" + type + "', idx='" + idx + "'}";
    }

    public void care() {
        System.out.println("照料鸡");
    }
}

class Cow extends FarmObject {
    public Cow(int idx, String name) {
        this.name = name;
        this.type = "Cow";
        this.idx = idx;
    }

    public String toString() {
        return "Cow{name='" + name + "', type='" + type + "', idx='" + idx + "'}";
    }

    public void care() {

        System.out.println("照料牛");
    }
}