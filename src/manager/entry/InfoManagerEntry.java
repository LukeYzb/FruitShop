package manager.entry;

import manager.controller.OtherCustomerController;
import manager.controller.OtherManagerController;
import manager.domain.Customer;

import java.io.IOException;
import java.util.Scanner;

public class InfoManagerEntry {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        l:
        while (true) {
            System.out.println("----------欢迎使用水果店管理系统----------");
            System.out.print("①顾客角色");
            System.out.print("\t②管理角色");
            System.out.println("\t③退出系统");
            System.out.print("请输入要选择的操作（1~3）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        //开启顾客系统
                        OtherCustomerController customerController = new OtherCustomerController();
                        lo1a:
                        while (true) {
                            String id = customerController.logIn();
                            if (id.equals("N")) {
                                System.out.println("退出成功！");
                                break lo;
                            } else {
                                customerController.start(id);
                                break lo1a;
                            }
                        }
                        break lo;
                    case "2":
                        //开启管理者系统
                        lo2:
                        while (true) {
                            OtherManagerController otherManagerController = new OtherManagerController();
                            System.out.println("请输入管理员账号：");
                            String account = sc.next();
                            boolean isManager1 = otherManagerController.logInAccount(account);
                            //判断ID是否存在
                            l1:
                            while (true) {
                                if (isManager1 == false) {//账号不存在
                                    System.out.println("账号不存在，退出请输入exit，不退出请重新输入账号：");
                                    String exit;
                                    exit = sc.next();
                                    if (exit.equals("exit")) {
                                        System.out.println("退出成功！");
                                        break lo2;
                                    } else {
                                        isManager1 = otherManagerController.logInAccount(exit);
                                    }
                                } else {
                                    break l1;
                                }
                            }
                            //判断密码是否正确
                            System.out.println("请输入管理员密码：");
                            String passwd = sc.next();
                            boolean isManager2 = otherManagerController.logInPassword(passwd);
                            l2:
                            while (true) {
                                //密码不存在
                                if (isManager2 == false) {
                                    System.out.println("密码不存在，退出请输入exit，不退出请重新输入密码：");
                                    String exit;
                                    exit = sc.next();
                                    if (exit.equals("exit")) {
                                        System.out.println("退出成功！");
                                        break lo2;
                                    } else {
                                        isManager2 = otherManagerController.logInPassword(exit);
                                    }
                                } else {
                                    break l2;
                                }
                            }
                            otherManagerController.start();
                            break lo2;
                        }
                        break lo;
                    case "3":
                        System.out.println("退出成功,欢迎下次使用！");
                        //退出当前正在运行的JVM虚拟机
                        System.exit(0);
                    default:
                        System.out.print("输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }
}
