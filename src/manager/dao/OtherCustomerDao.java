package manager.dao;

import manager.domain.Customer;
import manager.domain.Customer;

import java.util.ArrayList;

public class OtherCustomerDao implements BaseCustomerDao{
    //        创建顾客集合
    private static ArrayList<Customer> customers =new ArrayList<>();

    //    用IO流传入Customer对象，然后添加到customers集合里
    private void IputCustomer(){
        System.out.println("hhh");
    }
    //    用IO流传出Customer对象，然后添加到customers集合里
    private void OutputCustomer(){

    }

    public boolean addCustomer(Customer customer) {
        customers.add(customer);
        return true;
    }

    public Customer[] findAllCustomer() {
        Customer[] customers1=new Customer[customers.size()];
        for (int i = 0; i < customers1.length; i++) {
            customers1[i]= customers.get(i);
        }
        return customers1;
    }

    public void deleteCustomerById(String delId) {
//        查找ID在容器中的索引位置
        int index=getIndex(delId);
//        将该位置用null覆盖
        customers.remove(index);
    }

    public int getIndex(String id) {
        int index=-1;
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            if(customer !=null&& customer.getId().equals(id)){
                index=i;
                break;
            }
        }
        return index;
    }

    public void updateCustomer(String updateId, Customer newcustomer) {
//        查找ID所在索引位置
        int index=getIndex(updateId);
//        该索引位置替换
        customers.set(index,newcustomer);
    }
}
