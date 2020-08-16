package manager.controller;

import manager.domain.Customer;
import manager.service.CustomerService;

import java.util.Scanner;

public interface BaseManagerController {
     CustomerService CUSTOMER_SERVICE =new CustomerService();
     Scanner sc = new Scanner(System.in);
//    开启老师管理系统，展示菜单
    public void start();

    public void manageCustomer();

    public void manageFruit();

//    录入老师ID
    public String inputManagerId();

    public Customer inputManagerInfo(String id);
}
