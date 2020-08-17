package manager.service;


import manager.dao.CustomerDao;
import manager.domain.Customer;
import manager.factory.CustomerDaoFactory;

import java.io.IOException;

public class CustomerService {
    private CustomerDao customerDao = CustomerDaoFactory.getCustomerDao();
    public boolean addCustomer(Customer customer) throws IOException {
//        顾客对象交给CustomerDao库管
//        CustomerDao customerDao=new CustomerDao();
//库管来找顾客是否存在
        return customerDao.addCustomer(customer);
    }

    public boolean isExists(String id) throws IOException {
//        CustomerDao customerDao=new CustomerDao();
        Customer[] customers = customerDao.findAllCustomer().toArray(new Customer[0]);
//        假设id不存在
        boolean exists=false;
//        遍历数组
        for (int i = 0; i < customers.length; i++) {
            Customer customer = customers[i];
            if(customer !=null&& customer.getId().equals(id)){
                exists=true;
                break;
            }
        }
        return exists;
    }

    public void updateCustomer(String updateId, Customer newcustomer) throws IOException {
        customerDao.updateCustomer(updateId,newcustomer);
    }

    public void deleteCustomerById(String delId) throws IOException {
        customerDao.deleteCustomerById(delId);
    }

    public Customer[] findAllCustomer() throws IOException {
        Customer[] customers= customerDao.findAllCustomer().toArray(new Customer[100]);
        return customers;
    }
}
