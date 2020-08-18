package manager.controller;

import manager.domain.Fruit;
import manager.service.FruitService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public interface BaseCustomerController {
    FruitService customerService = new FruitService();
    Scanner sc = new Scanner(System.in);

    //    开启顾客管理系统，展示菜单
    public void start() throws IOException;

    public void logIn() throws IOException;

    public void findAllFruit() throws IOException;

    public ArrayList<Fruit> buyFruit() throws IOException;

    public void checkout(ArrayList<Fruit> boughtFruit);

    //    录入水果ID
    public String inputFruitId() throws IOException;
//抽象方法没有｛｝主体，分号结尾代替

}
