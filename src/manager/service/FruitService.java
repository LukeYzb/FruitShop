package manager.service;

import manager.dao.BaseFruitDao;
import manager.domain.Fruit;
import manager.factory.FruitDaoFactory;

public class FruitService {
//    通过水果库管工厂类，获取库管对象
    private BaseFruitDao fruitDao= FruitDaoFactory.getFruitDao();
//    private OtherFruitDao fruitDao=new OtherFruitDao();
    public boolean addFruit(Fruit fruit) {
//        水果对象交给FruitDao库管
//        FruitDao fruitDao=new FruitDao();
//库管来找水果是否存在
        return fruitDao.addFruit(fruit);
    }

    public boolean isExists(String id) {
//        FruitDao fruitDao=new FruitDao();
        Fruit[] fruits =fruitDao.findAllFruit();
//        假设id不存在
        boolean exists=false;
//        遍历数组
        for (int i = 0; i < fruits.length; i++) {
            Fruit fruit = fruits[i];
            if(fruit !=null&& fruit.getId().equals(id)){
                exists=true;
                break;
            }
        }
        return exists;
    }

    public Fruit[] findAllFruit() {
//        用FruitDao获取水果数组
        Fruit[] allFruit = fruitDao.findAllFruit();
        boolean flag=false;
        for (int i = 0; i < allFruit.length; i++) {
            Fruit fruit = allFruit[i];
            if(fruit !=null){
                flag=true;
                break;
            }
        }
        if(flag==true){
            return allFruit;
        }else {
            return null;
        }
    }
}