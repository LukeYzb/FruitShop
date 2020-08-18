package manager.service;


import manager.dao.CustomerDao;
import manager.dao.impl.CustomerDaoImpl;
import manager.dao.impl.FruitDaoImpl;
import manager.domain.Customer;
import manager.factory.CustomerDaoFactory;

import java.io.IOException;
import java.util.ArrayList;

public class CustomerService {
    CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
    private CustomerDao customerDao = CustomerDaoFactory.getCustomerDao();
    FruitDaoImpl fruitDao = new FruitDaoImpl();


    //判断顾客账号或者密码是否正确
    public boolean isExist(String id, String password) throws IOException {

        ArrayList<Customer> customers = (ArrayList<Customer>) customerDao.findAllCustomer();
        for (int i = 0; i < customers.size(); i++) {
            //customers.toString();
            //Customer customer = Customer.toObj(customers);
        }
        for (Customer customer : customers) {

        }
        return false;
    }


    public boolean addCustomer(Customer customer) throws IOException {
        //顾客对象交给CustomerDao库管
        //CustomerDao customerDao=new CustomerDao();
        //库管来找顾客是否存在
        return customerDao.addCustomer(customer);
    }

    public boolean isExists(String id) throws IOException {


        Customer[] customers = customerDaoImpl.findAllCustomer().toArray(new Customer[100]);
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

    public void buyFruit(String name, String amount) {
        fruitDao.buyFruit(name, amount);
    }


}
