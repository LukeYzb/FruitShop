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
    public boolean addFruit(Fruit fruit) throws IOException {
        Fruit byId = getById(fruit.getId());
        if (byId != null) {
            return false;
        }
        List<Fruit> allFruit = findAllFruit();
        allFruit.add(fruit);
        BufferedWriter bw = new BufferedWriter(new FileWriter("fruitshop\\fruit.txt"));
        for (Fruit fruit1 : allFruit) {
            String s = fruit1.toTxt();
            bw.write(s);
        }
        bw.close();
        return true;
    }

    @Override
    public List<Fruit> findAllFruit() throws IOException {
        return StreamUtils.findAll(Fruit.class);
    }

    @Override
    public boolean deleteFruitById(String delId) throws IOException {
        Fruit byId = getById(delId);
        if (byId == null) {
            return false;
        }
        List<Fruit> allFruit = findAllFruit();
        allFruit.remove(byId);
        BufferedWriter bw = new BufferedWriter(new FileWriter("fruitshop\\fruit.txt"));
        for (Fruit fruit : allFruit) {
            String s = fruit.toTxt();
            bw.write(s);
        }
        bw.close();
        return true;
    }

    @Override
    public Fruit getById(String id) throws IOException {
        List<Fruit> allFruit = findAllFruit();
        for (Fruit fruit : allFruit) {
            String s = fruit.toTxt();
            String[] strings = s.split(",");
            if (strings[0].equals(id)) {
                return fruit;
            }
        }
        return null;
    }


    @Override
    public boolean updateFruit(Fruit fruit) throws IOException {
        String[] strings = fruit.toTxt().split(",");
        String id = strings[0];
        boolean d = deleteFruitById(id);
        boolean a = addFruit(fruit);
        return d && a;
    }

    @Override
    public boolean buyFruit(String name, String amount) {
        return false;
    }

    @Override
    public Fruit getByName(String name) throws IOException {
        return null;
    }


}

