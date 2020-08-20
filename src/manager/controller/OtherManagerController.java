package manager.controller;

import manager.dao.CustomerDao;
import manager.dao.impl.CustomerDaoImpl;
import manager.dao.impl.FruitDaoImpl;
import manager.domain.Customer;
import manager.domain.Fruit;
import manager.service.CustomerService;
import manager.service.FruitService;
import manager.service.ManagerService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OtherManagerController implements BaseManagerController {
    private CustomerService customerService = new CustomerService();
    private FruitService fruitService = new FruitService();
    private Scanner sc = new Scanner(System.in);

    //管理员登录账户判断
    public boolean logInAccount(String id) throws IOException {
        boolean isManager1 = false;
        //判断ID是否存在
        l:
        while (true) {
            boolean exists1 = ManagerService.idIsExists(id);
            //exists为负,则执行
            if (!exists1) {
                break l;
            } else {
                isManager1 = true;
                break l;
            }
        }
        return isManager1;
    }

    //管理员登录密码判断
    public boolean logInPassword(String passwd) throws IOException {
        boolean isManager2 = false;
        l:
        while (true) {
            boolean exists2 = ManagerService.passwdIsExists(passwd);
            //exists为负,则执行
            if (!exists2) {
                break l;
            } else {
                isManager2 = true;
                break l;
            }
        }

        return isManager2;
    }

    //开启老师管理系统，展示菜单
    public void start() throws IOException {
        l:
        while (true) {
            System.out.println("----------欢迎使用管理功能!----------");
            System.out.print("①管理用户");
            System.out.print("\t②管理水果");
            System.out.println("\t③返回上一层");
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
                        System.out.println("退出成功！");
                        //退出当前正在运行的JVM虚拟机
                        break l;
                    default:
                        System.out.print("输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }


    @Override
    public void manageCustomer() throws IOException {
        l:
        while (true) {
            System.out.println("----------欢迎使用顾客管理功能----------");
            System.out.print("①注册新用户");
            System.out.print("\t②编辑现用户");
            //扩展了删除用户
            System.out.print("\t③删除现用户");
            System.out.println("\t④返回上一层");
            System.out.print("请输入要选择的操作（1~4）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        findAllCustomer();
                        addCustomer();
                        findAllCustomer();
                        break lo;
                    case "2":
                        findAllCustomer();
                        customerManagement();
                        findAllCustomer();
                        break lo;
                    case "3":
                        findAllCustomer();
                        deleteCustomer();
                        findAllCustomer();
                        break lo;
                    case "4":
                        System.out.println("退出成功！");
//                        退出当前正在运行的JVM虚拟机
                        break l;
                    default:
                        System.out.print("输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }

    private void findAllCustomer() throws IOException {
        Customer[] customers = customerService.findAllCustomer();
        //判断数组是否为空
        if (customers == null) {
            System.out.println("查无信息，请添加后重试。");
            return;
        }
        //遍历数组打印顾客信息
        System.out.println("账号\t\t\t\t姓名\t\t密码\t\t\t余额");
        for (int i = 0; i < customers.length; i++) {
            Customer customer = customers[i];
            if (customer != null) {
                System.out.println(customer.getId() + "\t\t" + customer.getName() + "\t\t" + customer.getPassword() + "\t\t" + customer.getMoney());
            }
        }
    }


    public void addCustomer() throws IOException {
        //局部变量id
        String addId = inputCustomerId();
        if (addId != null) {
            l:
            while (true) {
                //判断ID是否存在
                l1:
                while (true) {
                    boolean exists = customerService.isExists(addId);
                    if (exists) {//exists为正,则执行
                        System.out.println("账号已存在，退出请输入exit，不退出请重新输入账号：");
                        String exit;
                        exit = sc.next();
                        if (exit.equals("exit")) {
                            System.out.println("退出成功！");
                            break l;
                        } else {
                            addId = exit;
                        }
                    } else {
                        Customer customer = inputCustomerInfo(addId);
                        boolean result = customerService.addCustomer(customer);
                        //根据返回的bool结果显示添加是否成功
                        if (result) {
                            System.out.println("添加成功");
                        } else {
                            System.out.println("添加失败");
                        }
                        break l;
                    }
                }
            }
        }
    }

    private void customerManagement() throws IOException {
        l:
        while (true) {
            //①②③④⑤
            System.out.print("①充值");
            System.out.print("\t②改名");
            System.out.print("\t③改密码");
            System.out.println("\t④返回上一层");
            System.out.print("请输入要选择的操作（1~4）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        li:
                        while (true) {
                            String updateId = inputCustomerId();
                            //判断ID是否存在
                            l1:
                            while (true) {
                                boolean exists = customerService.isExists(updateId);
                                //exists为负,则执行
                                if (exists) {
                                    Customer customer = inputCustomerMoney(updateId);
                                    boolean result = customerService.updateCustomer(customer);
                                    //根据返回的bool结果显示是否成功
                                    if (result) {
                                        System.out.println("修改成功。");
                                    } else {
                                        System.out.println("修改失败。");
                                    }
                                    break li;
                                } else {
                                    System.out.println("账号不存在，退出请输入exit，不退出请重新输入账号：");
                                    String exit;
                                    exit = sc.next();
                                    if (exit.equals("exit")) {
                                        System.out.println("退出成功！");
                                        break li;
                                    } else {
                                        updateId = exit;
                                    }
                                }
                            }
                        }
                        break lo;
                    case "2":
                        lii:
                        while (true) {
                            String updateId = inputCustomerId();
                            //判断ID是否存在
                            l1:
                            while (true) {
                                boolean exists = customerService.isExists(updateId);
                                //exists为负,则执行
                                if (exists) {
                                    Customer customer = inputCustomerName(updateId);
                                    boolean result = customerService.updateCustomer(customer);
                                    //根据返回的bool结果显示是否成功
                                    if (result) {
                                        System.out.println("修改成功。");
                                    } else {
                                        System.out.println("修改失败。");
                                    }
                                    break lii;
                                } else {
                                    System.out.println("账号不存在，退出请输入exit，不退出请重新输入账号：");
                                    String exit;
                                    exit = sc.next();
                                    if (exit.equals("exit")) {
                                        System.out.println("退出成功！");
                                        break lii;
                                    } else {
                                        updateId = exit;
                                    }
                                }
                            }
                        }
                        break lo;
                    case "3":
                        liii:
                        while (true) {
                            String updateId = inputCustomerId();
                            //判断ID是否存在
                            l1:
                            while (true) {
                                boolean exists = customerService.isExists(updateId);
                                //exists为负,则执行
                                if (exists) {
                                    Customer customer = inputCustomerPasswd(updateId);
                                    boolean result = customerService.updateCustomer(customer);
                                    //根据返回的bool结果显示是否成功
                                    if (result) {
                                        System.out.println("修改成功。");
                                    } else {
                                        System.out.println("修改失败。");
                                    }
                                    break liii;
                                } else {
                                    System.out.println("账号不存在，退出请输入exit，不退出请重新输入账号：");
                                    String exit;
                                    exit = sc.next();
                                    if (exit.equals("exit")) {
                                        System.out.println("退出成功！");
                                        break liii;
                                    } else {
                                        updateId = exit;
                                    }
                                }
                            }
                        }
                        break lo;
                    case "4":
                        System.out.println("退出成功！");
                        //退出当前正在运行的JVM虚拟机
                        break l;
                    default:
                        System.out.print("输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }

    private void deleteCustomer() throws IOException {
        String delId = inputCustomerId();
        if (delId != null) {
            l:
            while (true) {
                //判断ID是否存在
                l1:
                while (true) {
                    boolean exists = customerService.isExists(delId);
                    if (exists) {//exists为负,则执行
                        boolean result = customerService.deleteCustomerById(delId);
                        //根据返回的bool结果显示是否成功
                        if (result) {
                            System.out.println("删除成功。");
                        } else {
                            System.out.println("删除失败。");
                        }
                        break l;
                    } else {
                        System.out.println("账号不存在，退出请输入exit，不退出请重新输入账号：");
                        String exit;
                        exit = sc.next();
                        if (exit.equals("exit")) {
                            System.out.println("退出成功！");
                            break l;
                        } else {
                            delId = exit;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void manageFruit() throws IOException {
        l:
        while (true) {
            //①②③④⑤
            System.out.println("----------欢迎使用水果管理功能!----------");
            System.out.print("①添加水果");
            System.out.print("  ②修改水果数据");
            //扩展了删除水果
            System.out.print("  ③删除水果数据");
            System.out.println("  ④返回上一层");
            System.out.print("请输入要选择的操作（1~4）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        findAllFruits();
                        addFruit();
                        findAllFruits();
                        break lo;
                    case "2":
                        changeFruits();
                        break lo;
                    case "3":
                        findAllFruits();
                        deleteFruits();
                        findAllFruits();
                        break lo;
                    case "4":
                        System.out.println("退出成功！");
                        break l;
                    default:
                        System.out.print("输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }

    private void findAllFruits() throws IOException {
        ArrayList<Fruit> fruits = fruitService.findAllFruit();
        //判断数组是否为空
        if (fruits == null) {
            System.out.println("查无信息，请添加后重试。");
            return;
        }
        //遍历数组打印顾客信息
        System.out.println("编号\t\t\t名称\t\t价格\t\t库存量");
        for (Fruit fruit : fruits) {
            System.out.println(fruit.toShow());

        }
    }

    private void deleteFruits() throws IOException {
        String delId = inputFruitId();
        if (delId != null) {
            l:
            while (true) {
                //判断ID是否存在
                l1:
                while (true) {
                    boolean exists = customerService.isExists(delId);
                    //水果存在,则执行
                    if (exists) {
                        boolean result = fruitService.deleteFruitById(delId);
                        //根据返回的bool结果显示是否成功
                        if (result) {
                            System.out.println("删除成功。");
                        } else {
                            System.out.println("删除失败。");
                        }
                        break l;
                    } else {
                        System.out.println("id不存在，退出请输入exit，不退出请重新输入账号：");
                        String exit;
                        exit = sc.next();
                        if (exit.equals("exit")) {
                            System.out.println("退出成功！");
                            break l;
                        } else {
                            delId = exit;
                        }
                    }
                }
            }
        }
    }

    private void changeFruits() throws IOException {
        l:
        while (true) {
            findAllFruits();
            //①②③④⑤
            System.out.print("①修改价格");
            System.out.print("\t②修改库存");
            System.out.print("\t③修改名称");
            System.out.println("\t④返回上一层");
            System.out.print("请输入要选择的操作（1~4）：");
            lo:
            while (true) {
                String choice = sc.next();
                lo1:
                switch (choice) {
                    case "1":
                        li:
                        while (true) {
                            String updateId = inputFruitId();
                            //判断ID是否存在
                            l1:
                            while (true) {
                                boolean exists = fruitService.isExists(updateId);
                                if (exists) {//exists为正,则执行
                                    Fruit fruit = inputFruitPrice(updateId);
                                    boolean result = fruitService.updateFruit(fruit);
                                    //根据返回的bool结果显示添加是否成功
                                    if (result) {
                                        System.out.println("修改成功。");
                                    } else {
                                        System.out.println("修改失败。");
                                    }
                                    break li;
                                } else {
                                    System.out.println("账号不存在，退出请输入exit，不退出请重新输入账号：");
                                    String exit;
                                    exit = sc.next();
                                    if (exit.equals("exit")) {
                                        System.out.println("退出成功！");
                                        break li;
                                    } else {
                                        updateId = exit;
                                    }
                                }
                            }
                        }
                        break lo;
                    case "2":
                        lii:
                        while (true) {
                            String updateId = inputFruitId();
                            //判断ID是否存在
                            l1:
                            while (true) {
                                boolean exists = fruitService.isExists(updateId);
                                //exists为负,则执行
                                if (exists) {
                                    Fruit fruit = inputFruitAmount(updateId);
                                    boolean result = fruitService.updateFruit(fruit);
                                    //根据返回的bool结果显示添加是否成功
                                    if (result) {
                                        System.out.println("修改成功。");
                                    } else {
                                        System.out.println("修改失败。");
                                    }
                                    break lii;
                                } else {
                                    System.out.println("ID不存在，退出请输入exit，不退出请重新输入账号：");
                                    String exit;
                                    exit = sc.next();
                                    if (exit.equals("exit")) {
                                        System.out.println("退出成功！");
                                        break lii;
                                    } else {
                                        updateId = exit;
                                    }
                                }
                            }
                        }
                        break lo;
                    case "3":
                        liii:
                        while (true) {
                            String updateId = inputFruitId();
                            //判断ID是否存在
                            l1:
                            while (true) {
                                boolean exists = fruitService.isExists(updateId);
                                if (exists) {//exists为负,则执行
                                    Fruit fruit = inputFruitName(updateId);
                                    boolean result = fruitService.updateFruit(fruit);
                                    //根据返回的bool结果显示添加是否成功
                                    if (result) {
                                        System.out.println("修改成功。");
                                    } else {
                                        System.out.println("修改失败。");
                                    }
                                    break liii;
                                } else {
                                    System.out.println("账号不存在，退出请输入exit，不退出请重新输入账号：");
                                    String exit;
                                    exit = sc.next();
                                    if (exit.equals("exit")) {
                                        System.out.println("退出成功！");
                                        break liii;
                                    } else {
                                        updateId = exit;
                                    }
                                }
                            }
                        }
                        break lo;
                    case "4":
                        System.out.println("退出成功！");
                        //退出当前正在运行的JVM虚拟机
                        break l;
                    default:
                        System.out.print("输入有误，请重新输入。");
                        break lo1;
                }
            }
        }
    }

    private void addFruit() throws IOException {
        String addId = inputFruitId();
        if (addId != null) {
            l:
            while (true) {
                //判断ID是否存在
                l1:
                while (true) {
                    boolean exists = customerService.isExists(addId);
                    if (exists) {//exists为正,则执行
                        System.out.println("ID已存在，退出请输入exit，不退出请重新输入账号：");
                        String exit;
                        exit = sc.next();
                        if (exit.equals("exit")) {
                            System.out.println("退出成功！");
                            break l;
                        } else {
                            addId = exit;
                        }
                    } else {
                        Fruit fruit = inputFruitInfo(addId);
                        boolean result = fruitService.addFruit(fruit);
                        //根据返回的bool结果显示添加是否成功
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
    }

    //录入顾客ID，可以用来判断id是否存在，返回String的ID值或null
    public String inputCustomerId() {
        String Id = null;
        System.out.println("请输入顾客的ID：");
        Id = sc.next();
        return Id;
    }

    //录入水果ID，可以用来判断id是否存在，返回String的ID值或null
    public String inputFruitId() {
        String Id = null;
        System.out.println("请输入水果的ID：");
        Id = sc.next();
        return Id;
    }

    //更改顾客姓名、密码和充值金额
    public Customer inputCustomerName(String id) throws IOException {
        //根据ID修改顾客
        System.out.println("请输入顾客姓名：");
        String name = sc.next();
        //获取对应顾客的余额
        CustomerDaoImpl newcustomer1 = new CustomerDaoImpl();
        String originamount = "0";
        String passwd = "";
        List<Customer> customers = newcustomer1.findAllCustomer();
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                originamount = customer.getMoney();
                passwd = customer.getPassword();
            }
        }
        //封装顾客对象并返回
        Customer newcustomer = new Customer(id, name, passwd, originamount);
        return newcustomer;
    }

    public Customer inputCustomerPasswd(String id) throws IOException {
        //根据ID修改顾客
        System.out.println("请输入顾客密码：");
        String passwd = sc.next();
        String name = "";
        String amount = "0";
        //获取对应顾客的除密码外其他属性
        CustomerDaoImpl newcustomer1 = new CustomerDaoImpl();
        List<Customer> customers = newcustomer1.findAllCustomer();
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                amount = customer.getMoney();
                name = customer.getName();
            }
        }
        //封装顾客对象并返回
        Customer newcustomer = new Customer(id, name, passwd, amount);
        return newcustomer;
    }

    public Customer inputCustomerMoney(String id) throws IOException {
        //根据ID修改顾客
        String passwd = "";
        String name = "";
        System.out.println("请输入充值金额：");
        String amount = sc.next();
        //String金额强转int
        double amo = Double.parseDouble(amount);
        //获取对应顾客的余额、姓名和密码
        CustomerDaoImpl newcustomer1 = new CustomerDaoImpl();
        String originamount = "0";
        List<Customer> customers = newcustomer1.findAllCustomer();
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                originamount = customer.getMoney();
                passwd = customer.getPassword();
                name = customer.getName();
            }
        }

        //String金额强转int
        double oriamo = Double.parseDouble(originamount);
        //充值金额加上原来金额=总金额
        oriamo = amo + oriamo;
        //int金额强转String
        amount = String.valueOf(oriamo);
        //        封装顾客对象并返回
        Customer newcustomer = new Customer(id, name, passwd, amount);
        return newcustomer;
    }

    //用来添加顾客，一次输入全部属性
    public Customer inputCustomerInfo(String id) throws IOException {
        //根据ID修改顾客
        System.out.println("请输入顾客密码：");
        String passwd = sc.next();
        System.out.println("请输入顾客姓名：");
        String name = sc.next();
        System.out.println("请输入充值金额：");
        String amount = sc.next();
        //String金额强转int
        double amo = Double.parseDouble(amount);
        //获取对应顾客的余额
        CustomerDaoImpl newcustomer1 = new CustomerDaoImpl();
        String originamount = "0";
        List<Customer> customers = newcustomer1.findAllCustomer();
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                originamount = customer.getMoney();
            }
        }
        //String金额强转int
        double oriamo = Double.parseDouble(originamount);
        //充值金额加上原来金额=总金额
        oriamo = amo + oriamo;
        //int金额强转String
        amount = String.valueOf(oriamo);
        //封装顾客对象并返回
        Customer newcustomer = new Customer(id, name, passwd, amount);
        return newcustomer;
    }

    //可以用来输入水果名称、价格和库存
    public Fruit inputFruitName(String id) throws IOException {
        //根据ID修改水果
        System.out.println("请输入水果名称：");
        String name = sc.next();
        //获取对应水果的除名称外其他信息
        FruitDaoImpl newfruit1 = new FruitDaoImpl();
        String originamount = "0";
        String price = "0";
        List<Fruit> fruits = newfruit1.findAllFruit();
        for (Fruit fruit : fruits) {
            if (fruit.getId().equals(id)) {
                originamount = fruit.getAmount();
                price = fruit.getPrice();
            }
        }
        //将键盘录入信息封装为水果对象
        Fruit newfruit = new Fruit(id, name, price, originamount);
        return newfruit;
    }

    public Fruit inputFruitPrice(String id) throws IOException {
        //根据ID修改水果
        System.out.println("请输入水果价格：");
        String price = sc.next();
        //获取对应水果的除价格外其他信息
        FruitDaoImpl newfruit1 = new FruitDaoImpl();
        String originamount = "0";
        String name = "";
        List<Fruit> fruits = newfruit1.findAllFruit();
        for (Fruit fruit : fruits) {
            if (fruit.getId().equals(id)) {
                originamount = fruit.getAmount();
                name = fruit.getName();
            }
        }
        //将键盘录入信息封装为水果对象
        Fruit newfruit = new Fruit(id, name, price, originamount);
        return newfruit;
    }

    public Fruit inputFruitAmount(String id) throws IOException {
        //根据ID修改水果
        System.out.println("请输入水果库存：");
        String amount = sc.next();
        //获取对应水果的除库存外其他信息
        FruitDaoImpl newfruit1 = new FruitDaoImpl();
        String name = "";
        String price = "0";
        List<Fruit> fruits = newfruit1.findAllFruit();
        for (Fruit fruit : fruits) {
            if (fruit.getId().equals(id)) {
                name = fruit.getName();
                price = fruit.getPrice();
            }
        }
        //将键盘录入信息封装为水果对象
        Fruit newfruit = new Fruit(id, name, price, amount);
        return newfruit;
    }

    //用来添加水果，一次输入全部属性
    public Fruit inputFruitInfo(String id) {
        //根据ID修改水果
        System.out.println("请输入水果名称：");
        String name = sc.next();
        System.out.println("请输入水果价格：");
        String price = sc.next();
        System.out.println("请输入水果库存：");
        String amount = sc.next();
        //将键盘录入信息封装为水果对象
        Fruit newfruit = new Fruit(id, name, price, amount);
        return newfruit;
    }
}
