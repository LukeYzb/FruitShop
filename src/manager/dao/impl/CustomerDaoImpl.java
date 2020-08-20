package manager.dao.impl;

import manager.dao.CustomerDao;
import manager.domain.Customer;
import manager.domain.Fruit;
import manager.util.StreamUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean addCustomer(Customer customer) {
        return StreamUtils.add(Customer.class, customer);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return StreamUtils.findAll(Customer.class);
    }

    @Override
    public boolean deleteCustomerById(String delId) {
        return StreamUtils.deleteById(Customer.class, delId);
    }

    @Override
    public Customer getById(String id) {
        return StreamUtils.getById(Customer.class, id);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        return StreamUtils.update(Customer.class, customer);
    }

    @Override
    public double checkout(double totalPrice, String id, ArrayList<Fruit> boughtFruit) {
        double finalPrice = 0;
        if (totalPrice < 100) {
            finalPrice = totalPrice;
        } else if (totalPrice >= 100 && totalPrice <= 200) {
            finalPrice = (double) ((totalPrice - 100) * 0.9 + 100);
        } else if (totalPrice > 200 && totalPrice <= 500) {
            finalPrice = (double) ((totalPrice - 200) * 0.8 + 190);
        } else {
            finalPrice = (double) ((totalPrice - 500) * 0.7 + 350);
        }
        Customer byId = getById(id);
        FruitDaoImpl returnAmount = new FruitDaoImpl();//余额不足时库存不应该减少，这里补充回减少的那部分
        String[] split = byId.toTxt().split(",");
        double writePrice = Double.parseDouble(split[3]) - finalPrice;
        String money = Double.toString(writePrice);
        double money1 = Double.parseDouble(money);
        if (money1 <= 0) {
            System.out.println("账户余额不足，充值后再购买！！！");
            for (Fruit fruit : boughtFruit) {//余额不足时库存不应该减少，这里补充回减少的那部分
                String[] split1 = fruit.toTxt().split(",");
                List<Fruit> fruits = returnAmount.findAllFruit();
                for (Fruit fruit1 : fruits) {
                    if (fruit1.getName().equals(split1[1])) {//通过水果名比对找到对象，回复库存
                        String oriAmo = fruit1.getAmount();
                        double allAmount = Double.parseDouble(oriAmo) + Double.parseDouble(split1[3]);
                        fruit1.setAmount(String.valueOf(allAmount));
                        returnAmount.updateFruit(fruit1);
                    }
                }
            }
        } else {
            byId.setMoney(money);
            updateCustomer(byId);
        }
        return finalPrice;
    }
}
