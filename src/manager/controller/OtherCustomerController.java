package manager.controller;

import manager.domain.Fruit;
import manager.service.FruitService;

import java.util.Scanner;

public class OtherCustomerController implements BaseCustomerController {
    private FruitService customerService = new FruitService();
    private Scanner sc = new Scanner(System.in);

    //    开启顾客购买系统，展示菜单
    public void start() {
        l:
        while (true) {
//            ①②③④⑤
            System.out.println("欢迎使用顾客购买功能!");
            System.out.print("①：登录");
            System.out.print("  ②：查看水果");
            System.out.print("  ③：购买水果");
            System.out.print("  ④：结账");
            System.out.println("⑤：退出");
            System.out.print("请输入要选择的操作（1~5）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        logIn();
                        break lo;
                    case "2":
                        findAllFruit();
                        break lo;
                    case "3":
                        buyFruit();
                        break lo;
                    case "4":
                        checkout();
                        break lo;
                    case "5":
                        System.out.println("退出顾客购买系统，成功！");
//                        退出当前正在运行的JVM虚拟机
                        break l;
                    default:
                        System.out.print("您的输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }

    @Override
    public void logIn() {
//需要修改，输入账号（输入n返回），判断账号有误重新输入，输入密码（输入n返回），判断密码有误重新输入
    }

    @Override
    public void findAllFruit() {
        Fruit[] fruits = customerService.findAllFruit();
//        判断数组是否为空
        if (fruits == null) {
            System.out.println("查无信息，请添加后重试。");
            return;
        }
//        遍历数组打印水果信息
        System.out.println("编号\t\t名称\t价格\t\t库存量");
        for (int i = 0; i < fruits.length; i++) {
            Fruit fruit = fruits[i];
            if (fruit != null) {
                System.out.println(fruit.getId() + "\t\t" + fruit.getName() + "\t" + fruit.getPrice() + "\t\t" + fruit.getAmount());
            }
        }
    }

    @Override
    public void buyFruit() {
//需要修改
    }

    @Override
    public void checkout() {
//需要修改，添加结账后显示账单（同乐）
    }


    //    录入水果ID，可以用在buyFruit功能里
    public String inputFruitId() {
        String Id = null;
        l:
        while (true) {
            System.out.println("请输入水果的ID：");
            Id = sc.next();
//            判断ID是否存在
            l1:
            while (true) {
                boolean exists = customerService.isExists(Id);
                if (!exists) {//exists为负,则执行
                    System.out.println("ID不存在，退出请输入0，不退出请重新输入ID：");
                    int exit;
                    exit = sc.nextInt();
                    if (exit == 0) {
                        Id = null;
                        System.out.println("退出成功！");
                        break l;
                    } else {
                        Id = String.valueOf(exit);
                    }
                } else {
                    break l;
                }
            }
        }
        return Id;
    }
}
