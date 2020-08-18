package manager.service;

import manager.dao.FruitDao;
import manager.domain.Fruit;
import manager.factory.FruitDaoFactory;

import java.io.IOException;
import java.util.ArrayList;

public class FruitService {


    //通过水果库管工厂类，获取库管对象
    private static FruitDao fruitDao = FruitDaoFactory.getFruitDao();

    public boolean updateFruit(Fruit newfruit) throws IOException {
        return fruitDao.updateFruit(newfruit);
    }

    public static boolean deleteFruitById(String delId) throws IOException {
        return fruitDao.deleteFruitById(delId);
    }

    //private OtherFruitDao fruitDao=new OtherFruitDao();
    public static boolean addFruit(Fruit fruit) throws IOException {
        //水果对象交给FruitDao库管
        //FruitDao fruitDao=new FruitDao();
        //库管来找水果是否存在
        return fruitDao.addFruit(fruit);
    }

    public boolean isExists(String id) throws IOException {
        //FruitDao fruitDao=new FruitDao();
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

    public static ArrayList<Fruit> findAllFruit() throws IOException {
        ArrayList<Fruit> fruits = (ArrayList<Fruit>) fruitDao.findAllFruit();
        return fruits;
    }
}