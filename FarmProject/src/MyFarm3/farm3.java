package MyFarm3;

import java.util.ArrayList;
import java.io.*;

/* 
1.程序需要增加文件保存功能，能够将当前农场中的所有对象信息保存到本地文件中。下一次运行可以恢复当前农场状态。
2.程序需要增加文件读取功能。当程序下一次启动时，可以从本地文件 farm.txt 中读取上一次保存的农场状态，并恢复到二维 ArrayList 中。读取时，需要根据文件中的对象类型重新创建对应的对象。（程序启动时，可以先判断本地是否存在保存文件）
3.构建测试类，该类实现与用户的交互，向用户提示操作信息，并接收用户的操作请求。
*/
public class farm3 {
    private ArrayList<ArrayList<FarmObject>> farmObjects;

    public void initFarm(int rows) {
        farmObjects = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            farmObjects.add(new ArrayList<>());
        }
        System.out.println("农场初始化完毕，共包含 " + rows + " 行。");
    }

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

    public FarmObject SearchFarmObject(String name) {

        for (int i = 0; i < farmObjects.size(); i++) {
            ArrayList<FarmObject> rowList = farmObjects.get(i);
            for (int j = 0; j < rowList.size(); j++) {
                FarmObject obj = rowList.get(j);
                if (obj.name.equals(name)) {
                    System.out.println("编号: " + obj.idx + ", 类型: " + obj.type + ", 名称: " + obj.name + ", 所在位置: 第" + i
                            + "行, 第" + j + "列");
                    return obj;
                }
            }
        }
        System.out.println("未找到名称为 " + name + " 的农场对象。");
        return null;
    }

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
                System.out.println("成功移除: " + removed.name);
                return removed;
            }
        }
        System.out.println("位置无效");
        return null;
    }

    public void clearAll() {

        if (farmObjects != null) {
            for (ArrayList<FarmObject> rowList : farmObjects) {
                rowList.clear();
            }
            System.out.println("已清空。");
        }
    }

    public void care(int row, int col) {

        if (farmObjects != null && row >= 0 && row < farmObjects.size()) {
            ArrayList<FarmObject> rowList = farmObjects.get(row);
            if (col >= 0 && col < rowList.size()) {
                rowList.get(col).care();
                return;
            }
        }
        System.out.println("无效位置");
    }

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

    // 1)程序需要增加文件保存功能，能够将当前农场中的所有对象信息保存到本地文件中。
    public void Save(String filePath) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(farmObjects.size() + "\n");
            for (int i = 0; i < farmObjects.size(); i++) {
                ArrayList<FarmObject> rowList = farmObjects.get(i);
                for (int j = 0; j < rowList.size(); j++) {
                    FarmObject obj = rowList.get(j);
                    bw.write(obj.type + "," + obj.idx + "," + obj.name + "," + i + "\n");
                }
            }
            System.out.println("状态已保存到 " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 2)程序需要增加文件读取功能。根据文件中的对象类型重新创建对应的对象。
    public void Load(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("不存在文件");
            return;    
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if (line == null)
                return;
            int rows = Integer.valueOf(line);
            initFarm(rows);
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String type = parts[0];
                    int idx = Integer.valueOf(parts[1]);
                    String name = parts[2];
                    int row = Integer.valueOf(parts[3]);
                    FarmObject obj = null;
                    switch (type) {
                        case "Wheat":
                            obj = new Wheat(idx, name);
                            break;
                        case "Corn":
                            obj = new Corn(idx, name);
                            break;
                        case "Chicken":
                            obj = new Chicken(idx, name);
                            break;
                        case "Cow":
                            obj = new Cow(idx, name);
                            break;
                    }
                    if (obj != null) {
                        farmObjects.get(row).add(obj);
                    }
                }
            }
            System.out.println("农场状态已从 " + filePath + " 恢复。");
        } catch (IOException e) {
            e.printStackTrace();

        }
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