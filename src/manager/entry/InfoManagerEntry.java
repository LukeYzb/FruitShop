package manager.entry;

import manager.controller.OtherCustomerController;
import manager.controller.OtherManagerController;

import java.io.IOException;
import java.util.Scanner;

public class InfoManagerEntry {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        l:
        while (true){
            System.out.println("欢迎使用水果店管理系统!");
            System.out.print("①：顾客角色");
            System.out.print("  ②：管理角色");
            System.out.println("  ③：退出系统");
            System.out.print("请输入要选择的操作（1~3）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
//                        开启顾客系统
                        OtherCustomerController customerController=new OtherCustomerController();
                        customerController.start();
                        break lo;
                    case "2":
//                        开启管理者系统
                        lo2:
                        while(true){
                            OtherManagerController otherManagerController =new OtherManagerController();
                            boolean isManager=otherManagerController.logIn();
                            if(isManager==true){
                                otherManagerController.start();
                                break lo2;
                            }else {
                                System.out.println("输入有误，请重新输入！");
                            }
                        }
                        break lo;
                    case "3":
                        System.out.println("退出成功，欢迎下次使用！");
//                        退出当前正在运行的JVM虚拟机
                        System.exit(0);
                    default:
                        System.out.print("您的输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }
}
