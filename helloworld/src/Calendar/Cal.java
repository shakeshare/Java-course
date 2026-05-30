package Calendar;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Cal {

    public static void main(String[] args) {
        int i = 0, year = 0, month = 1, days = 0, choice = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:\n1.查询某天星期\n2.打印某年日历\n3.退出程序\n请输入数字进行选择");
            choice = sc.nextInt();
            if (choice == 1) {
                System.out.println("请输入日期(年/月/日)：");
                DateTimeFormatter Dtf = DateTimeFormatter.ofPattern("yyyy/M/d");
                LocalDate date = LocalDate.parse(sc.next(), Dtf);
                DayOfWeek Dof = date.getDayOfWeek();
                int value = Dof.getValue();
                String chinese = switch (value) {
                    case 1 -> "星期一";
                    case 2 -> "星期二";
                    case 3 -> "星期三";
                    case 4 -> "星期四";
                    case 5 -> "星期五";
                    case 6 -> "星期六";
                    case 7 -> "星期日";
                    default -> "非法值";
                };
                System.out.println(chinese);
            } else if (choice == 2) {
                System.out.println("请输入年份");
                year = sc.nextInt();
                for (month = 1; month <= 12; month++) {
                    System.out.println("-------------" + (month) + "月------------");
                    YearMonth ym = YearMonth.of(year, month);
                    days = ym.lengthOfMonth();
                    DayOfWeek dow1 = ym.atDay(1).getDayOfWeek();
                    int start = dow1.getValue() % 7;
                    System.out.println("日\t一\t二\t三\t四\t五\t六");
                    for (i = 0; i < start; i++) {
                        System.out.print("   " + "\t");
                    }
                    for (int day = 1; day <= days; day++) {
                        System.out.printf(day + "\t");
                        start++;
                        if (start % 7 == 0) {
                            System.out.println();
                        }

                    }
                    System.out.println();
                }
            } else if (choice == 3) {
                break;
            }
        }
        sc.close();

    }
}