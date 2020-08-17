package manager.controller;

import manager.domain.Customer;
import manager.domain.Fruit;
import manager.domain.Manager;
import manager.service.CustomerService;
import manager.service.FruitService;
import manager.service.ManagerService;

import java.io.IOException;
import java.util.Scanner;

public class OtherManagerController implements BaseManagerController {
    private CustomerService customerService = new CustomerService();
    private FruitService fruitService = new FruitService();
    private Scanner sc = new Scanner(System.in);

    @Override
    public boolean logIn() {
        boolean isManager=false;
        String Id = null;
        l:
        while (true) {
            System.out.println("请输入管理员ID：");
            Id = sc.next();
//            判断ID是否存在
            l1:
            while (true) {
                boolean exists = ManagerService.isExists(Id);
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
                    isManager=true;
                    break l;
                }
            }
        }
        return isManager;
    }
    //    开启老师管理系统，展示菜单
    public void start() throws IOException {
//        Scanner sc = new Scanner(System.in);//放在开头
        l:
        while (true) {
            System.out.println("欢迎使用管理功能!");
            System.out.print("①：管理用户");
            System.out.print("  ②：管理水果");
            System.out.println("  ③：退出");
            System.out.print("请输入要选择的操作（1~3）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        manageCustomer();
                        break lo;
                    case "2":
                        manageFruit();
                        break lo;
                    case "3":
                        System.out.println("退出管理系统，成功！");
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
    public void manageCustomer() throws IOException {
        l:
        while (true) {
//            ①②③④⑤
            System.out.println("欢迎使用顾客管理功能!");
            System.out.print("①：注册新用户");
            System.out.print("  ②：编辑现用户");
//            扩展了删除用户
            System.out.print("  ③：删除现用户");
            System.out.println("  ④：退出");
            System.out.print("请输入要选择的操作（1~4）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        findAllCustomer();
                        addCustomer();
                        break lo;
                    case "2":
                        findAllCustomer();
                        Customermanagement();
                        break lo;
                    case "3":
                        findAllCustomer();
                        deleteCustomer();
                        break lo;
                    case "4":
                        System.out.println("退出管理系统，成功！");
//                        退出当前正在运行的JVM虚拟机
                        break l;
                    default:
                        System.out.print("您的输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }

    private void findAllCustomer() throws IOException {
        Customer[] customers=customerService.findAllCustomer();
//        判断数组是否为空
        if(customers==null){
            System.out.println("查无信息，请添加后重试。");
            return;
        }
//        遍历数组打印顾客信息
        System.out.println("ID\t\t姓名\t密码\t\t余额");
        for (int i = 0; i < customers.length; i++) {
            Customer customer=customers[i];
            if(customer!=null){
                System.out.println(customer.getId()+"\t\t"+customer.getName()+"\t"+customer.getPassword()+"\t\t"+customer.getMoney());
            }
        }
    }


    public void addCustomer() throws IOException {
//        局部变量id
        String addId = null;
        l:
        while (true) {
            System.out.println("请输入顾客的ID：");
            addId = sc.next();
//            判断ID是否存在
            l1:
            while (true) {
                boolean exists = customerService.isExists(addId);
                if (exists) {//exists为负,则执行
                    System.out.println("ID已存在，退出请输入0，不退出请重新输入ID：");
                    int exit;
                    exit = sc.nextInt();
                    if (exit == 0) {
                        System.out.println("退出成功！");
                        break l;
                    } else {
                        addId = Integer.toString(exit);
                    }
                } else {
                    Customer customer = inputCustomerInfo(addId);
//        将顾客对象传给ManagerService中的addManager方法
                    boolean result = customerService.addCustomer(customer);
//        根据返回的bool结果显示添加是否成功
                    if (result) {
                        System.out.println("添加成功。");
                    } else {
                        System.out.println("添加失败。");
                    }
                    break l;
                }
            }
        }
    }

    private void Customermanagement() throws IOException {
        String updateId=inputCustomerId();
        if(updateId!=null){
            Customer newcustomer=inputCustomerInfo(updateId);
            customerService.updateCustomer(updateId,newcustomer);
            System.out.println("修改成功！");
        }
    }

    private void deleteCustomer() throws IOException {
        String delId=inputCustomerId();
        if(delId!=null){
            //            根据ID删除客户
            customerService.deleteCustomerById(delId);
            System.out.println("删除成功！");
        }
    }

    @Override
    public void manageFruit() throws IOException {
        l:
        while (true) {
//            ①②③④⑤
            System.out.println("欢迎使用水果管理功能!");
            System.out.print("①：添加水果");
            System.out.print("  ②：修改水果数据");
//            扩展了删除水果
            System.out.print("  ③：删除水果数据");
            System.out.println("  ④：返回上一层");
            System.out.print("请输入要选择的操作（1~4）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        findAllFruits();
                        addFruit();
                        break lo;
                    case "2":
                        findAllFruits();
                        changeFruits();
                        break lo;
                    case "3":
                        findAllFruits();
                        deleteFruits();
                        break lo;
                    case "4":
                        System.out.println("退出水果管理系统，成功！");
                        break l;
                    default:
                        System.out.print("您的输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }

    private void findAllFruits() {
        Fruit[] fruits=fruitService.findAllFruit();
//        判断数组是否为空
        if(fruits==null){
            System.out.println("查无信息，请添加后重试。");
            return;
        }
//        遍历数组打印顾客信息
        System.out.println("ID\t\t名称\t价格\t\t库存");
        for (int i = 0; i < fruits.length; i++) {
            Fruit fruit=fruits[i];
            if(fruit!=null){
                System.out.println(fruit.getId()+"\t\t"+fruit.getName()+"\t"+fruit.getPrice()+"\t\t"+fruit.getAmount());
            }
        }
    }

    private void deleteFruits() throws IOException {
        String delId=inputFruitId();
        if(delId!=null){
            //            根据ID删除水果
            FruitService.deleteFruitById(delId);
            System.out.println("删除成功！");
        }
    }

    private void changeFruits() throws IOException {
        String updateId=inputFruitId();
        if(updateId!=null){
            Fruit newfruit=inputFruitInfo(updateId);
            FruitService.updateFruit(updateId,newfruit);
            System.out.println("修改成功！");
        }
    }

    private void addFruit() {
        System.out.println("addFruit");
    }

    //    录入顾客ID，可以用来判断id是否存在，返回String的ID值或null
    public String inputCustomerId() throws IOException {
        String Id = null;
        l:
        while (true) {
            System.out.println("请输入顾客的ID：");
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
    //    录入水果ID，可以用来判断id是否存在，返回String的ID值或null
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

    //    可以用来输入顾客姓名和充值金额
    public Customer inputCustomerInfo(String id) {
        //            根据ID修改顾客
        System.out.println("请输入顾客昵称：");
        String name = sc.next();
        System.out.println("请输入充值金额：");
        String amount = sc.next();
//        String金额强转int
        int amo = Integer.parseInt(amount);
//        将键盘录入信息封装为顾客对象
        Customer newcustomer = new Customer();

        String originamount = newcustomer.getMoney();
//        String金额强转int
        int oriamo = Integer.parseInt(originamount);
//        充值金额加上原来金额=总金额
        oriamo = amo + oriamo;
//        int金额强转String
        amount = String.valueOf(oriamo);
        newcustomer.setId(id);
        newcustomer.setName(name);
        newcustomer.setMoney(amount);
        return newcustomer;
    }
    //    可以用来输入水果名称、价格和库存
    public Fruit inputFruitInfo(String id) {
        //            根据ID修改顾客
        System.out.println("请输入水果名称：");
        String name = sc.next();
        System.out.println("请输入水果价格：");
        String price = sc.next();
        System.out.println("请输入水果库存：");
        String amount = sc.next();
//        将键盘录入信息封装为顾客对象
        Fruit newfruit = new Fruit();

        newfruit.setId(id);
        newfruit.setName(name);
        newfruit.setPrice(price);
        newfruit.setAmount(amount);
        return newfruit;
    }
}
