package manager.factory;

import manager.dao.OtherManagerDao;

public class ManagerDaoFactory {
    public static OtherManagerDao getManagerDao(){
        return new OtherManagerDao();
    }
}
