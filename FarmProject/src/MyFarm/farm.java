package MyFarm;

/* 
编写一个简单开心农场程序，要求如下：
1)假设你拥有一个简单的开心农场，该农场能够管理一批农场对象，例如农作物、动物等。农场中的对象使用数组进行存储，并使用常量设置数组最大容量，例如最多存储 10 个农场对象。通过程序实现该开心农场的对象管理功能。
2)程序需要实现具体农场对象的创建功能。在创建农场对象时，需要对该对象的信息进行登记，例如种植编号（自增）、名称、类型等，并将创建好的对象保存在内存数组中。
3)程序需要提供根据名称检索农场对象的功能。用户输入一个名称后，程序在当前农场中查找是否存在该对象。如果找到，则输出该对象的类种植编号、名称、类型。
4)程序需要提供农场对象的信息输出功能。根据创建序号，输出该序号对应的农场中的对象。
5)构建所有农场对象的父类：FarmObject。该类中定义农场对象的基本属性和方法。
6)构建具体农场对象类：Wheat 小麦类、Corn 玉米类、Chicken 鸡、Cow 牛类等。不同子类需要根据自己的特点重写父类中的方法。
7)程序需要通过多态实现对任意农场对象的统一照料（例如方法care）。无论传入的是小麦、玉米、鸡还是牛，都可以调用其对应的照料方法。
8)定义一个静态方法，该方法可以对传入的对象实例进行判断，并输出该对象实例的具体类型。
9)程序需要提供“收获农作物”或“出售动物”的功能。当农作物成熟后，用户可以将其收获；当动物需要出售时，用户可以将其从农场中移除。（该功能可以理解为：根据编号删除数组中对应的农场对象。）
10)构建测试类 FarmTest，该类实现与用户的交互。程序应向用户显示功能菜单，并根据用户输入执行对应功能。
示例：
程序应具有良好的类层次结构，良好的人机交互性能，即：程序应向用户提示功能说明，并可根据用户的功能选择，执行对应的功能，并给出带详细描述信息的最终执行结果。

*/
public class farm {
    private int MAX = 10;
    private FarmObject[] farmObjects = new FarmObject[MAX]; /*
                                                             * 1)假设你拥有一个简单的开心农场，该农场能够管理一批农场对象，例如农作物、动物等。农场中的对象使用数组进行存储，
                                                             * 并使用常量设置数组最大容量，例如最多存储 10 个农场对象。通过程序实现该开心农场的对象管理功能。
                                                             */
    private static int index = 0;

    public boolean AddFarmObject(FarmObject obj) {
        if (index < MAX) {
            farmObjects[index] = obj;
            index++;
            return true;
        } else {
            System.out.println("农场已满");
            return false;
        }
    } /*
       * 2)程序需要实现具体农场对象的创建功能。在创建农场对象时，需要对该对象的信息进行登记，例如种植编号（自增）、名称、类型等，
       * 并将创建好的对象保存在内存数组中。
       */

    public FarmObject SearchFarmObject(String name) {
        for (int i = 0; i < index; i++) {
            if (farmObjects[i].name.equals(name)) {
                System.out.println(farmObjects[i]);
                return farmObjects[i];
            }
        }
        System.out.println("未找到名称为 " + name + " 的农场对象。");
        return null;
    } /*
       * 3)程序需要提供根据名称检索农场对象的功能。用户输入一个名称后，程序在当前农场中查找是否存在该对象。如果找到，则输出该对象的类种植编号、名称、类型。
       */

    public void info(int idx) {
        if (idx >= 0 && idx < farm.index) {
            System.out.println(farmObjects[idx]);
        } else {
            System.out.println("error");
        }
    } /*
       * 4)程序需要提供农场对象的信息输出功能。根据创建序号，输出该序号对应的农场中的对象。
       */

    public FarmObject RemoveObject(int idx) {
        if (farmObjects[idx] != null) {
            FarmObject removed = farmObjects[idx];
            for (int i = idx; i < index - 1; i++) {
                farmObjects[i] = farmObjects[i + 1];
            }
            farmObjects[index - 1] = null;
            index--;
            return removed;
        } else {
            System.out.println("error");
            return null;
        }
    } /*
       * 9)程序需要提供“收获农作物”或“出售动物”的功能。当农作物成熟后，用户可以将其收获；当动物需要出售时，用户可以将其从农场中移除。（该功能可以理解为：
       * 根据编号删除数组中对应的农场对象。）
       */

    public void care(int idx) {
        if (idx >= 0 && idx < index) {
            if (farmObjects[idx] != null) {
                farmObjects[idx].care();
            }
        } else {
            System.out.println("无效的农场对象序号！");
        }
    }

    public static String check(FarmObject obj) {
        if (obj != null) {
            System.out.println("对象类型: " + obj.type);
            return obj.type;
        }
        return "Unknown type";
    } /*
       * 8)定义一个静态方法，该方法可以对传入的对象实例进行判断，并输出该对象实例的具体类型。
       */

    public void printAll() {
        System.out.println("当前农场对象列表：");
        for (int i = 0; i < index; i++) {
            System.out.println(farmObjects[i]);
        }
    }

    public FarmObject getObject(int idx) {
        if (idx >= 0 && idx < index) {
            return farmObjects[idx];
        } else {
            System.out.println("无效的农场对象序号！");
            return null;
        }
    }

    public static int getindex() {
        return index;
    }
}

abstract class FarmObject {

    public String name;
    public String type;
    public int idx;

    abstract public void care(); /*
                                  * 7)程序需要通过多态实现对任意农场对象的统一照料（例如方法care）。无论传入的是小麦、玉米、鸡还是牛，都可以调用其对应的照料方法。
                                  */

}
/*
 * 5)构建所有农场对象的父类：FarmObject。该类中定义农场对象的基本属性和方法。
 */

class Wheat extends FarmObject {
    public Wheat(String name) {
        this.name = name;
        this.type = "Wheat";
        this.idx = farm.getindex();
    }

    public String toString() {
        return "Wheat{name='" + name + "', type='" + type + "', idx='" + idx + "'}";
    }

    public void care() {
        System.out.println("照料小麦");
    }
}
/*
 * 6)构建具体农场对象类：Wheat 小麦类、Corn 玉米类、Chicken 鸡、Cow 牛类等。不同子类需要根据自己的特点重写父类中的方法。
 */

class Corn extends FarmObject {
    public Corn(String name) {
        this.name = name;
        this.type = "Corn";
        this.idx = farm.getindex();
    }

    public String toString() {
        return "Corn{name='" + name + "', type='" + type + "', idx='" + idx + "'}";
    }

    public void care() {
        System.out.println("照料玉米");
    }
}

class Chicken extends FarmObject {
    public Chicken(String name) {
        this.name = name;
        this.type = "Chicken";
        this.idx = farm.getindex();
    }

    public String toString() {
        return "Chicken{name='" + name + "', type='" + type + "', idx='" + idx + "'}";
    }

    public void care() {
        System.out.println("照料鸡");
    }
}

class Cow extends FarmObject {
    public Cow(String name) {
        this.name = name;
        this.type = "Cow";
        this.idx = farm.getindex();
    }

    public String toString() {
        return "Cow{name='" + name + "', type='" + type + "', idx='" + idx + "'}";
    }

    public void care() {

        System.out.println("照料牛");
    }
}