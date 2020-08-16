package manager.dao;

import manager.domain.Manager;

public interface BaseManagerDao {
    //        创建老师数组
    static Manager[] managers = new Manager[2];

    public boolean addManager(Manager manager);

    public Manager[] findAllManager();

    public void deleteManagerById(String delId);

    public int getIndex(String id);

    public void updateManager(String updateId, Manager newmanager);
}
