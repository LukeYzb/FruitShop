package manager.dao.impl;

import manager.dao.ManagerDao;
import manager.domain.Fruit;
import manager.domain.Manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao {


    @Override
    public boolean addManager(Manager manager) throws IOException {
        Manager byId = getById(manager.getId());
        if (byId != null) {
            return false;
        }
        List<Manager> allManager = findAllManager();
        allManager.add(manager);
        BufferedWriter bw = new BufferedWriter(new FileWriter("fruitshop\\manager.txt"));
        for (Manager manager1 : allManager) {
            String s = manager1.toTxt();
            bw.write(s);
        }
        bw.close();
        return true;
    }

    @Override
    public List<Manager> findAllManager() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fruitshop\\manager.txt"));
        String s;
        List<Manager> managers = new ArrayList<>();
        while ((s = br.readLine()) != null) {
            Manager manager = Manager.toObj(s);
            managers.add(manager);
        }
        br.close();
        return managers;
    }

    @Override
    public boolean deleteManagerById(String delId) throws IOException {
        Manager byId = getById(delId);
        if (byId == null) {
            return false;
        }
        List<Manager> allManager = findAllManager();
        allManager.remove(byId);
        BufferedWriter bw = new BufferedWriter(new FileWriter("fruitshop\\manager.txt"));
        for (Manager manager : allManager) {
            String s = manager.toTxt();
            bw.write(s);
        }
        bw.close();
        return true;
    }

    @Override
    public Manager getById(String id) throws IOException {
        List<Manager> allManager = findAllManager();
        for (Manager manager : allManager) {
            String s = manager.toTxt();
            String[] strings = s.split(",");
            if (strings[0].equals(id)) {
                return manager;
            }
        }
        return null;
    }

    @Override
    public boolean updateManager(Manager manager) throws IOException {
        String[] strings = manager.toTxt().split(",");
        String id = strings[0];
        boolean d = deleteManagerById(id);
        boolean a = addManager(manager);
        return d && a;
    }
}

