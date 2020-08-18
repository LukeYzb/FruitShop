package manager.service;


import manager.dao.CustomerDao;
import manager.dao.impl.CustomerDaoImpl;
import manager.dao.impl.FruitDaoImpl;
import manager.domain.Customer;
import manager.domain.Fruit;
import manager.factory.CustomerDaoFactory;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerService {
    CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
    private CustomerDao customerDao = CustomerDaoFactory.getCustomerDao();
    FruitDaoImpl fruitDao = new FruitDaoImpl();

    //判断顾客账号或者密码是否正确
    public boolean isExist(String id, String password) throws IOException {
        ArrayList<Customer> customers = (ArrayList<Customer>) customerDaoImpl.findAllCustomer();
        boolean flag = false;
        lo:
        for (Customer customer : customers) {
            String s = customer.toTxt();
            String[] split = s.split(",");
            if (id.equals(split[0]) && password.equals(split[2])) {
                flag = true;
                break lo;
            }
        }
        return flag;
    }

    public boolean addCustomer(Customer customer) throws IOException {
        //顾客对象交给CustomerDao库管
        //CustomerDao customerDao=new CustomerDao();
        //库管来找顾客是否存在
        return customerDao.addCustomer(customer);
    }

    public boolean isExists(String id) throws IOException {

        Customer[] customers = customerDaoImpl.findAllCustomer().toArray(new Customer[0]);
        //假设id不存在
        boolean flag = false;
        //遍历数组
        for (int i = 0; i < customers.length; i++) {
            Customer customer = customers[i];
            if (customer != null && customer.getId().equals(id)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public boolean updateCustomer(Customer newcustomer) throws IOException {
        return customerDao.updateCustomer(newcustomer);
    }

    public boolean deleteCustomerById(String delId) throws IOException {
        return customerDao.deleteCustomerById(delId);
    }

    public Customer[] findAllCustomer() throws IOException {
        Customer[] customers = customerDaoImpl.findAllCustomer().toArray(new Customer[100]);
        return customers;
    }

    public void buyFruit(String name, String amount) throws IOException {

        fruitDao.buyFruit(name, amount);
    }

    public int checkout(int totalPrice) {
        int finalPrice = 0;
        if (totalPrice < 100) {
            finalPrice = totalPrice;
        } else if (totalPrice >= 100 && totalPrice <= 200) {
            finalPrice = (int) ((totalPrice - 100) * 0.9 + 100);
        } else if (totalPrice > 200 && totalPrice <= 500) {
            finalPrice = (int) ((totalPrice - 200) * 0.8 + 190);
        } else {
            finalPrice = (int) ((totalPrice - 500) * 0.7 + 350);
        }
        return finalPrice;
    }
}
