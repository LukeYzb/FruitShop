package manager.dao;

import manager.domain.Fruit;

import java.io.IOException;
import java.util.List;

public interface FruitDao {
    /**
     * 添加水果
     * @param fruit
     * @return是否添加成功
     */
    boolean addFruit(Fruit fruit) throws IOException;

    /**
     * 查询所有水果
     * @return一个list集合
     */
    List<Fruit> findAllFruit() throws IOException;

    /**
     * 删除特定id水果
     * @param delId
     * @return是否成功
     */
    boolean deleteFruitById(String delId) throws IOException;

    /**
     * 查询特定Id水果
     * @param id
     * @return该水果对象
     */
    Fruit getById(String id) throws IOException;

    /**
     * 更新水果信息
     * @param fruit
     * @return是否成功
     */
    boolean updateFruit(Fruit fruit) throws IOException;
}
