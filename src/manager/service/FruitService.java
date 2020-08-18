package manager.service;

import manager.dao.FruitDao;
import manager.dao.impl.FruitDaoImpl;
import manager.domain.Fruit;
import manager.factory.FruitDaoFactory;

import java.io.IOException;
import java.util.ArrayList;

public class FruitService {
    FruitDaoImpl fruitDao=new FruitDaoImpl();


    //通过水果库管工厂类，获取库管对象
    //private  FruitDao fruitDao = FruitDaoFactory.getFruitDao();
    //通过名称判断水果是否存在
    public  boolean isExist(String name) throws IOException {
        Fruit[] fruits = fruitDao.findAllFruit().toArray(new Fruit[0]);
        //假设id不存在
        boolean exists = false;
        //遍历数组
        for (int i = 0; i < fruits.length; i++) {
            Fruit fruit = fruits[i];
            if (fruit != null && fruit.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        return exists;
     }

    public boolean updateFruit(Fruit newfruit) {
        return fruitDao.updateFruit(newfruit);
    }

    public boolean deleteFruitById(String delId) {
        return fruitDao.deleteFruitById(delId);
    }

    //private OtherFruitDao fruitDao=new OtherFruitDao();
    public boolean addFruit(Fruit fruit) {
        //水果对象交给FruitDao库管
        //FruitDao fruitDao=new FruitDao();
        //库管来找水果是否存在
        return fruitDao.addFruit(fruit);
    }

    public boolean isExists(String id) throws IOException {
        Fruit[] fruits = fruitDao.findAllFruit().toArray(new Fruit[0]);
        //假设id不存在
        boolean exists = false;
        //遍历数组
        for (int i = 0; i < fruits.length; i++) {
            Fruit fruit = fruits[i];
            if (fruit != null && fruit.getId().equals(id)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public ArrayList<Fruit> findAllFruit() throws IOException {
        ArrayList<Fruit> fruits = (ArrayList<Fruit>) fruitDao.findAllFruit();
        return fruits;
    }

}