package manager.dao.impl;

import manager.dao.FruitDao;
import manager.domain.Fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitDaoImpl implements FruitDao {

    @Override
    public boolean addFruit(Fruit fruit) {
        return false;
    }

    @Override
    public List<Fruit> findAllFruit() {
//        需要从读取的数组中获取
        return null;
    }

    @Override
    public void deleteFruitById(String delId) {

    }

    @Override
    public Fruit getIndex(String id) {
        return null;
    }

    @Override
    public void updateFruit(String updateId, Fruit newfruit) {

    }
}

