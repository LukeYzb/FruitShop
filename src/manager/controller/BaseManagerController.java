package manager.controller;

import manager.domain.Customer;
import manager.service.CustomerService;

import java.io.IOException;
import java.util.Scanner;

public interface BaseManagerController {
     CustomerService CUSTOMER_SERVICE =new CustomerService();
     Scanner sc = new Scanner(System.in);
//    开启管理系统，展示菜单
    public void start() throws IOException;

    public boolean logIn();

    public void manageCustomer() throws IOException;

    public void manageFruit() throws IOException;

    public Customer inputCustomerInfo(String id);


}
