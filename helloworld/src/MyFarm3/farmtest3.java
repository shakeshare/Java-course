package MyFarm3;

import java.util.Scanner;

public class farmtest3 {
    public static void main(String[] args) {
        farm3 myFarm = new farm3();
        Scanner scanner = new Scanner(System.in);

        System.out.println("初始化农场，输入行数：");
        int rows = scanner.nextInt();
        scanner.nextLine();
        myFarm.initFarm(rows);

        while (true) {
            System.out.println("======二维开心农场管理系统======");
            System.out.println("1. 创建农场对象");
            System.out.println("2. 根据名称查找农场对象");
            System.out.println("3. 根据类型输出农场对象");
            System.out.println("4. 输出指定编号的农场对象");
            System.out.println("5. 照料指定农场对象");
            System.out.println("6. 判断指定农场对象类型");
            System.out.println("7. 收获农作物或出售动物");
            System.out.println("8. 输出所有农场对象");
            System.out.println("9. 清空农场");      
            System.out.println("0. 退出");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1: {
                    System.out.println("请输入农场对象类型(Wheat/Corn/Chicken/Cow)");
                    String type = scanner.nextLine();
                    System.out.println("请输入对象编号数字：");
                    int idx = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("请输入农场对象名称：");
                    String name = scanner.nextLine();
                    System.out.println("请输入要添加到第几行(0起始)：");
                    int row = scanner.nextInt();
                    scanner.nextLine();

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
                        default:
                            System.out.println("未知类型，创建失败。");
                            break;
                    }
                    if (obj != null) {
                        myFarm.AddFarmObject(obj, row);
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
                    System.out.println("类型: " + farm3 .check(obj));
                    break;
                }
                case 4: {
                    System.out.println("输入所在的行号：");
                    int row = scanner.nextInt();
                    System.out.println("输入位置编号（列号）：");
                    int col = scanner.nextInt();
                    scanner.nextLine();
                    myFarm.info(row, col);
                    break;
                }
                case 5: {
                    System.out.println("输入所在的行号：");
                    int row = scanner.nextInt();
                    System.out.println("输入位置编号（列号）：");
                    int col = scanner.nextInt();
                    scanner.nextLine();
                    myFarm.care(row, col);
                    break;
                }
                case 6: {
                    System.out.println("输入所在的行号：");
                    int row = scanner.nextInt();
                    System.out.println("输入位置编号（列号）：");
                    int col = scanner.nextInt();
                    scanner.nextLine();
                    String objType = farm3.check(myFarm.getObject(row, col));
                    System.out.println("该农场对象的类型是：" + objType);
                    break;
                }
                case 7: {
                    System.out.println("输入要收获或出售的农场对象行号：");
                    int row = scanner.nextInt();
                    System.out.println("输入位置编号（列号）：");
                    int col = scanner.nextInt();
                    scanner.nextLine();
                    myFarm.RemoveObject(row, col);
                    break;
                }
                case 8: {
                    myFarm.printAll();
                    break;
                }
                case 9: {
                    myFarm.clearAll();
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
