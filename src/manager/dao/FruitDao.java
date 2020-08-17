package manager.dao;

import manager.domain.Fruit;

import java.util.List;

public interface FruitDao {
    /**
     * 添加水果
     * @param fruit
     * @return是否添加成功
     */
    boolean addFruit(Fruit fruit);

    /**
     * 查询所有水果
     * @return一个list集合
     */
    List<Fruit> findAllFruit();

    /**
     * 删除特定id水果
     * @param delId
     * @return是否成功
     */
    void deleteFruitById(String delId);

    /**
     * 查询特定Id水果
     * @param id
     * @return该水果对象
     */
    Fruit getIndex(String id);

    /**
     * 更新水果信息
     * @param updateId,newfruit
     * @return是否成功
     */
    void updateFruit(String updateId, Fruit newfruit);
}
