package manager.controller;

import manager.domain.Customer;
import manager.service.CustomerService;

import java.io.IOException;
import java.util.Scanner;

public interface BaseManagerController {
    CustomerService CUSTOMER_SERVICE = new CustomerService();
    Scanner sc = new Scanner(System.in);

    //    开启管理系统，展示菜单
    public void start() throws IOException;

    //登录账号和密码判断
    public boolean logInAccount(String id) throws IOException;

    public boolean logInPassword(String passwd) throws IOException;

    public void manageCustomer() throws IOException;

    public void manageFruit() throws IOException;

    public Customer inputCustomerInfo(String id) throws IOException;


}
