package manager.dao.impl;

import manager.dao.ManagerDao;
import manager.domain.Manager;
import manager.util.StreamUtils;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {


    @Override
    public boolean addManager(Manager manager) {
        return StreamUtils.add(Manager.class, manager);
    }

    @Override
    public List<Manager> findAllManager() {
        return StreamUtils.findAll(Manager.class);
    }

    @Override
    public boolean deleteManagerById(String delId) {
        return StreamUtils.deleteById(Manager.class, delId);
    }

    @Override
    public Manager getById(String id) {
        return StreamUtils.getById(Manager.class, id);
    }

    @Override
    public boolean updateManager(Manager manager) {
        return StreamUtils.update(Manager.class, manager);
    }
}

