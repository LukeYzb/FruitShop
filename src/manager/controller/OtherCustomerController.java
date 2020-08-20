package manager.controller;

import manager.domain.Fruit;
import manager.service.CustomerService;
import manager.service.FruitService;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class OtherCustomerController implements BaseCustomerController {
    //    FruitService fruitService = new FruitService();
    CustomerService customerService = new CustomerService();
    ArrayList<Fruit> boughtFruit = new ArrayList<>();
    //    OtherManagerController otherManagerController = new OtherManagerController();
//    Customer customer = new Customer();
    ArrayList<Fruit> fruits = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    //    开启顾客购买系统，展示菜单
    public void start(String id) throws IOException {
        l:
        while (true) {
            //①②③④⑤
            System.out.println("-----------欢迎使用顾客购买功能!-----------");
            System.out.print("①查看水果");
            System.out.print("\t②购买水果");
            System.out.print("\t③结账");
            System.out.println("\t④返回上一层");
            System.out.print("请输入要选择的操作（1~5）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        findAllFruit();
                        break lo;
                    case "2":
                        fruits = buyFruit();
                        break lo;
                    case "3":
                        checkout(fruits, id);
                        break lo;
                    case "4":
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
    public String logIn() throws IOException {
        String id = null;
        CustomerService customerService = new CustomerService();
        System.out.println("---请先登录,无账号请先注册账号---");
        while (true) {
            System.out.println("请输入账户(输入N返回):");
            id = sc.next();
            if (id.equalsIgnoreCase("n")) {
                break;
            }
            System.out.println("请输入密码:");
            String password = sc.next();
            //通过customerService.isExist()返回的true或者false判断账号和密码的正确性
            boolean flag = customerService.isExist(id, password);
            if (!flag) {
                System.out.println("账号或者密码有误");
            } else {
                System.out.println("登录成功");
                break;
            }
        }
        return id;
    }

    @Override
    public void findAllFruit() throws IOException {
        FruitService fruitService = new FruitService();
        ArrayList<Fruit> fruits = fruitService.findAllFruit();
        //判断集合是否为空集合，如果为空提示没有信息
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
    public ArrayList<Fruit> buyFruit() throws IOException {
        CustomerService customer = new CustomerService();
        FruitService fruitService = new FruitService();
        lock:
        while (true) {
            lo:
            while (true) {
                System.out.println("请输入你要购买的水果");
                String name = sc.next();
                boolean exists = fruitService.isExist(name);
                Fruit byName = fruitService.getByName(name);
                if (!exists) {
                    System.out.println("你输入的水果不存在，重新输入");
                    break lo;
                }
                lo1:
                while (true) {
                    System.out.println("请输入你要购买的数量");
                    String amount = sc.next();
                    byName.setAmount(amount);
                    boolean amountOutOfRange = customer.buyFruit(name, amount);
                    if (amountOutOfRange) {
                        break lo1;
                    } else {
                        System.out.println("库存不足！");
                    }
                }
                boughtFruit.add(byName);
                System.out.println("是否继续购买Y/N");
                String go = sc.next();
                if (go.equalsIgnoreCase("Y")) {
                    break lo;
                } else if (go.equalsIgnoreCase("N")) {
                    break lock;
                } else {
                    System.out.println("您的输入有误");
                }
            }
        }
        return boughtFruit;
    }

    @Override
    public void checkout(ArrayList<Fruit> boughtFruit, String id) {
        System.out.println("您购买了以下水果, 请查看确认");
        System.out.println("名称\t\t单价\t\t数量\t\t金额");
        double totalPrice = 0;
        for (Fruit fruit : boughtFruit) {
            String[] split = fruit.toTxt().split(",");
            double total = Double.parseDouble(split[2]) * Double.parseDouble(split[3]);
            System.out.println(split[1] + "\t\t" + split[2] + "\t\t" + split[3] + "\t\t" + total);
            totalPrice += total;
        }
        double finalPrice = customerService.checkout(totalPrice, id, boughtFruit);
        DecimalFormat df=new DecimalFormat("#.00");
        System.out.println("总价为" + df.format(totalPrice) + "\t\t\t\t" + "优惠后的价格为" + df.format(finalPrice));
        //结账后账单清空
        boughtFruit.clear();
    }
}
