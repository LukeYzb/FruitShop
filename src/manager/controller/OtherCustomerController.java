package manager.controller;

import manager.domain.Customer;
import manager.domain.Fruit;
import manager.service.CustomerService;
import manager.service.FruitService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class OtherCustomerController implements BaseCustomerController {
    FruitService customerService = new FruitService();
    Scanner sc = new Scanner(System.in);

    //    开启顾客购买系统，展示菜单
    public void start() throws IOException {
        l:
        while (true) {
            //①②③④⑤
            System.out.println("-----------欢迎使用顾客购买功能!-----------");
            System.out.print("①登录");
            System.out.print("\t②查看水果");
            System.out.print("\t③购买水果");
            System.out.print("\t④结账");
            System.out.println("\t⑤退出");
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
                        //break lo;
                    case "4":
                        checkout();
                        break lo;
                    case "5":
                        System.out.println("退出成功");
                        break l;
                    default:
                        System.out.print("输入有误，请重新输入");
                        break lo1;
                }
            }
        }
    }

    @Override
    public void logIn() throws IOException {
        CustomerService customerService = new CustomerService();
        while (true) {
            System.out.println("请输入账户");
            String id = sc.next();
            System.out.println("请输入密码");
            String password = sc.next();
            boolean flag = customerService.isExist(id, password);
            if (!flag) {
                System.out.println("账号或者密码有误");
            } else {
                System.out.println("登录成功");
                break;
            }
        }
    }

    @Override
    public void findAllFruit() throws IOException {
        FruitService fruitService=new FruitService();
        ArrayList<Fruit> fruits = fruitService.findAllFruit();
        //判断数组是否为空
        if (fruits == null) {
            System.out.println("查无信息，请添加后重试");
            return;
        }
        //遍历数组打印水果信息
        System.out.println("编号\t\t\t名称\t\t价格\t\t库存量");

        for (Fruit fruit : fruits) {
            System.out.println(fruit.toShow());
        }
    }

    @Override
    public void buyFruit() throws IOException {
        CustomerService customer = new CustomerService();
        FruitService fruitService=new FruitService();
        lock: while (true) {
            lo:
            while(true){
                System.out.println("请输入你要购买的水果");
                String name = sc.next();
                boolean exists = fruitService.isExist(name);
                if(!exists){
                    System.out.println("你输入的水果不存在，重新输入");
                    break lo;
                }
                System.out.println("请输入你要购买的数量");
                String amount = sc.next();
                System.out.println("是否继续购买Y/N");
                String go = sc.next();
                customer.buyFruit(name, amount);
                if (go == "Y") {
                    continue;
                } else if(go == "Y"){
                    continue ;
                } else{
                    break lock;
                }
            }

        }
    }

    @Override
    public void checkout() {
        /*customerService.checkOut(total);
        if(total>=100&&total<=200){
            total=((total)-100)*0.9+100;
        }else if (total>200&&total<=500){
            total=
        }else {

        }
        System.out.println("您一共消费"+total);*/
    }


    //    录入水果ID，可以用在buyFruit功能里
    public String inputFruitId() throws IOException {
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
