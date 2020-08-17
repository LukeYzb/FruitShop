package manager.dao.impl;

import manager.dao.CustomerDao;
import manager.domain.Customer;
import manager.util.StreamUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean addCustomer(Customer customer) throws IOException {
        //如果ID存在,不能继续添加
        Customer byId = getById(customer.getId());
        if (byId != null) {
            return false;
        }
        //得到findAllCustomer的List
        List<Customer> allCustomer = findAllCustomer();
        //在List里添加参数customer
        allCustomer.add(customer);
        //将新的List写入IO流
        BufferedWriter bw = new BufferedWriter(new FileWriter("fruitshop\\customer.txt"));
        //list遍历,totxt
        for (Customer customer1 : allCustomer) {
            String s = customer1.toTxt();
            bw.write(s);
        }
        bw.close();
        return true;
    }

    @Override
    public List<Customer> findAllCustomer() throws IOException {
        return StreamUtils.findAll(Customer.class);
    }

    @Override
    public boolean deleteCustomerById(String delId) throws IOException {
        //如果ID不存在,就不能继续删除
        Customer byId = getById(delId);
        if (byId == null) {
            return false;
        }
        //得到findAllCustomer的list
        List<Customer> allCustomer = findAllCustomer();
        //遍历list,删掉byId对象,得到一个新的list
        allCustomer.remove(byId);
        //将新的list写进本地customer文件中
        BufferedWriter bw = new BufferedWriter(new FileWriter("fruitshop\\customer.txt"));
        //list遍历,totxt
        for (Customer customer1 : allCustomer) {
            String s = customer1.toTxt();
            bw.write(s);
        }
        bw.close();
        return true;
    }

    @Override
    public Customer getById(String id) throws IOException {
        //先通过findAllCustomer得到全部对象的list
        List<Customer> allCustomer = findAllCustomer();
        //将list遍历转换成String
        for (Customer customer : allCustomer) {
            String s = customer.toTxt();
            String[] strings = s.split(",");
            if (strings[0].equals(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) throws IOException {
        //将参数customer里的id提取出来
        String[] strings = customer.toTxt().split(",");
        String id = strings[0];
        //通过id用deleteCustomerById删掉原对象
        boolean d = deleteCustomerById(id);
        //再addCustomer,将新的参数对象添加
        boolean a = addCustomer(customer);
        //当deleteCustomerById和addCustomer都返回true时,则为true
        return d && a;
    }
}
