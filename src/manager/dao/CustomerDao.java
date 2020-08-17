package manager.dao;

import manager.domain.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerDao {

    /**
     * 添加顾客对象
     * @param customer
     * @return是否添加成功
     */
    boolean addCustomer(Customer customer) throws IOException;

    /**
     * 读取所有顾客对象
     * @return集合
     */
    List<Customer> findAllCustomer() throws IOException;

    /**
     * 删除特定Id的顾客对象
     * @param delId
     * @return是否删除成功
     */
    void deleteCustomerById(String delId) throws IOException;

    /**
     * 查询特定Id顾客
     * @param id
     * @return顾客对象
     */
    Customer getById(String id);

     void updateCustomer(String updateId, Customer newcustomer) throws IOException;
}
