package manager.dao;

import manager.domain.Manager;

import java.io.IOException;
import java.util.List;

public interface ManagerDao {
    /**
     * 添加管理员对象
     * @param manager
     * @return是否成功
     */
    boolean addManager(Manager manager) throws IOException;

    /**
     * 查询所有管理员信息
     * @return一个list集合
     */
    List<Manager> findAllManager() throws IOException;

    /**
     * 删除特定Id对象
     * @param delId
     * @return是否成功
     */
    boolean deleteManagerById(String delId) throws IOException;

    /**
     * 查询特定Id管理员对象
     * @param id
     * @return管理员对象
     */
    Manager getById(String id) throws IOException;

    /**
     * 更新管理员对象
     * @param manager
     * @return是否成功
     */
    boolean updateManager(Manager manager) throws IOException;
}
