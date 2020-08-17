package manager.service;

import manager.dao.impl.ManagerDaoImpl;
import manager.domain.Manager;
import manager.factory.ManagerDaoFactory;

import java.io.IOException;

public class ManagerService {
    private static ManagerDaoImpl managerDao = ManagerDaoFactory.getManagerDao();

    public static boolean idIsExists(String id) throws IOException {
//        ManagerDao managerDao=new ManagerDao();
        Manager[] managers = managerDao.findAllManager().toArray(new Manager[0]);
//        假设id不存在
        boolean exists = false;
//        遍历数组
        for (int i = 0; i < managers.length; i++) {
            Manager manager = managers[i];
            if (manager != null && manager.getId().equals(id)) {
                exists = true;
                break;
            }
        }
        return exists;
    }
    public static boolean passwdIsExists(String passwd) throws IOException {
//        ManagerDao managerDao=new ManagerDao();
        Manager[] managers = managerDao.findAllManager().toArray(new Manager[0]);
//        假设id不存在
        boolean exists=false;
//        遍历数组
        for (int i = 0; i < managers.length; i++) {
            Manager manager = managers[i];
            if(manager !=null&& manager.getPassword().equals(passwd)){
                exists=true;
                break;
            }
        }
        return exists;
    }
}
