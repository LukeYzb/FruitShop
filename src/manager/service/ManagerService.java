package manager.service;

import manager.dao.impl.ManagerDaoImpl;
import manager.domain.Manager;
import manager.factory.ManagerDaoFactory;

public class ManagerService {
    private static ManagerDaoImpl managerDao = ManagerDaoFactory.getManagerDao();

    public static boolean isExists(String id) {
//        ManagerDao managerDao=new ManagerDao();
        Manager[] managers = managerDao.findAllManager().toArray(new Manager[0]);
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
