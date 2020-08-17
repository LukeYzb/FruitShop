package manager.dao.impl;

import manager.dao.ManagerDao;
import manager.domain.Customer;
import manager.domain.Manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {


//    @Override
//    public boolean addManager(Manager manager) {
//        return false;
//    }

    @Override
    public List<Manager> findAllManager() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(".\\manager.txt"));//获取相对路径fruit下文件
        String s;
        List<Manager> managers = new ArrayList<>();
        while ((s = br.readLine()) != null) {
            Manager manager = Manager.toObj(s);
            managers.add(manager);
        }
        br.close();

        return managers;
    }

//    @Override
//    public boolean deleteManagerById(String delId) {
//        return false;
//    }
//
//    @Override
//    public Manager getIndex(String id) {
//        return null;
//    }
//
//    @Override
//    public boolean updateManager(Manager manager) {
//        return false;
//    }
}

