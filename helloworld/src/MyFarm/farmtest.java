package MyFarm;

import java.util.Scanner;

public class farmtest {
    public static void main(String[] args) {
        farm myFarm = new farm();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("======简单开心农场管理系统======");
            System.out.println("1. 创建农场对象");
            System.out.println("2. 根据名称查找农场对象");
            System.out.println("3. 根据类型输出农场对象");
            System.out.println("4. 输出指定编号的农场对象");
            System.out.println("5. 照料指定农场对象");
            System.out.println("6. 判断指定农场对象类型");
            System.out.println("7. 收获农作物或出售动物");
            System.out.println("8. 输出所有农场对象");
            System.out.println("0. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("请输入农场对象类型(Wheat/Corn/Chicken/Cow)");
                    String type = scanner.nextLine();
                    System.out.println("请输入农场对象名称：");
                    String name = scanner.nextLine();
                    FarmObject obj = null;
                    switch (type) {
                        case "Wheat":
                            obj = new Wheat(name);
                            break;
                        case "Corn":
                            obj = new Corn(name);
                            break;
                        case "Chicken":
                            obj = new Chicken(name);
                            break;
                        case "Cow":
                            obj = new Cow(name);
                            break;
                        default:
                            System.out.println("未知类型，创建失败。");
                            break;
                    }
                    if (obj != null) {
                        myFarm.AddFarmObject(obj);
                    }
                    break;
                }
                case 2: {
                    System.out.println("输入农场对象名称");
                    String searchName = scanner.nextLine();
                    myFarm.SearchFarmObject(searchName);
                    break;
                }
                case 3: {
                    System.out.println("输入农场对象名称");
                    String searchType = scanner.nextLine();
                    FarmObject obj = myFarm.SearchFarmObject(searchType);
                    farm.check(obj);
                    break;
                }
                case 4: {
                    System.out.println("输入指定编号");
                    int idx = scanner.nextInt();
                    scanner.nextLine();
                    myFarm.info(idx);
                    break;
                }
                case 5: {
                    System.out.println("输入指定编号");
                    int careidx = scanner.nextInt();
                    scanner.nextLine();
                    myFarm.care(careidx);
                    break;
                }
                case 6: {
                    System.out.println("请输入要判断类型的农场对象序号：");
                    int typeidx = scanner.nextInt();
                    scanner.nextLine();
                    String objType = farm.check(myFarm.getObject(typeidx));
                    System.out.println("该农场对象的类型是：" + objType);
                    break;
                }
                case 7: {
                    System.out.println("输入要收获或出售的农场对象序号：");
                    int removeidx = scanner.nextInt();
                    scanner.nextLine();
                    myFarm.RemoveObject(removeidx);
                    break;
                }
                case 8: {
                    myFarm.printAll();
                    break;
                }
                case 0: {
                    System.out.println("退出程序");
                    scanner.close();
                    break;
                }
                default: {
                    System.out.println("无效选择，请重新输入");
                    continue;
                }
            }
        }
    }
}
