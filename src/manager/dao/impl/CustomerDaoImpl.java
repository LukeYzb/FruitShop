package manager.dao.impl;

import manager.dao.CustomerDao;
import manager.domain.Customer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void updateCustomer(String updateId, Customer newcustomer) throws IOException {
        //得到findAllCustomer的List
        List<Customer> allCustomer = findAllCustomer();
        //        查找ID所在索引位置
        int index = getIndex(updateId);
//        该索引位置替换
        allCustomer.set(index, newcustomer);
    }

    public int getIndex(String id) throws IOException {
        List<Customer> allCustomer = findAllCustomer();
        int index = -1;
        for (int i = 0; i < allCustomer.size(); i++) {
            Customer customer = allCustomer.get(i);
            if (customer != null && customer.getId().equals(id)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public boolean addCustomer(Customer customer) throws IOException {
        //如果ID存在,不能继续添加
        Customer byId = getById(customer.getId());
        if (byId == null) {
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
        BufferedReader br = new BufferedReader(new FileReader("fruitshop\\customer.txt"));
        String s;
        List<Customer> customers = new ArrayList<>();
        while ((s = br.readLine()) != null) {
            Customer customer = Customer.toObj(s);
            customers.add(customer);
        }
        br.close();

        return customers;
    }

    @Override
    public void deleteCustomerById(String delId) throws IOException {
        //得到findAllCustomer的List
        List<Customer> allCustomer = findAllCustomer();
        //        查找ID所在索引位置
        int index = getIndex(delId);
//        将该位置用null覆盖
        allCustomer.remove(index);
    }

    @Override
    public Customer getById(String id) {
        return null;
    }


}
