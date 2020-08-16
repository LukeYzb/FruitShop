package manager.service;

import manager.dao.OtherManagerDao;
import manager.domain.Manager;
import manager.factory.ManagerDaoFactory;

public class ManagerService {
    private OtherManagerDao managerDao = ManagerDaoFactory.getManagerDao();
    public boolean addManager(Manager manager) {
//        顾客对象交给ManagerDao库管
//        ManagerDao managerDao=new ManagerDao();
//库管来找顾客是否存在
        return managerDao.addManager(manager);
    }

    public boolean isExists(String id) {
//        ManagerDao managerDao=new ManagerDao();
        Manager[] managers = managerDao.findAllManager();
//        假设id不存在
        boolean exists=false;
//        遍历数组
        for (int i = 0; i < managers.length; i++) {
            Manager manager = managers[i];
            if(manager !=null&& manager.getId().equals(id)){
                exists=true;
                break;
            }
        }
        return exists;
    }
}
