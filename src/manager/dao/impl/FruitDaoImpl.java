package manager.dao.impl;

import manager.dao.FruitDao;
import manager.domain.Customer;
import manager.domain.Fruit;
import manager.util.StreamUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FruitDaoImpl implements FruitDao {

    @Override
    public boolean addFruit(Fruit fruit) {
        return StreamUtils.add(Fruit.class, fruit);
    }

    @Override
    public List<Fruit> findAllFruit() {
        return StreamUtils.findAll(Fruit.class);
    }

    @Override
    public boolean deleteFruitById(String delId) {
        return StreamUtils.deleteById(Fruit.class, delId);
    }

    @Override
    public Fruit getById(String id) {
        return StreamUtils.getById(Fruit.class, id);
    }


    @Override
    public boolean updateFruit(Fruit fruit) {
        return StreamUtils.update(Fruit.class, fruit);
    }

    @Override
    public boolean buyFruit(String name, String amount) {
        Fruit fruit = getByName(name);
        String[] split = fruit.toTxt().split(",");
        int quantity = Integer.parseInt(split[3]);
        quantity = quantity - Integer.parseInt(amount);
        if(quantity>=0){
            String result = Integer.toString(quantity);
            Fruit fruit1 = new Fruit(split[0], split[1], split[2], result);
            updateFruit(fruit1);
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Fruit getByName(String name) {
        return StreamUtils.getByName(Fruit.class, name);
    }


}

