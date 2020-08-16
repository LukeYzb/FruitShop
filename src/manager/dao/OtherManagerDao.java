package manager.dao;

import manager.domain.Manager;

import java.util.ArrayList;

public class OtherManagerDao implements BaseManagerDao{
    //        创建顾客集合
    private static ArrayList<Manager> managers =new ArrayList<>();

    //    用IO流传入Manager对象，然后添加到managers集合里
    private void IputManager(){
        System.out.println("hhh");
    }
    //    用IO流传出Manager对象，然后添加到managers集合里
    private void OutputManager(){

    }

    public boolean addManager(Manager manager) {
        managers.add(manager);
        return true;
    }

    public Manager[] findAllManager() {
        Manager[] managers1=new Manager[managers.size()];
        for (int i = 0; i < managers1.length; i++) {
            managers1[i]= managers.get(i);
        }
        return managers1;
    }

    @Override
    public void deleteManagerById(String delId) {

    }

    public int getIndex(String id) {
        int index=-1;
        for (int i = 0; i < managers.size(); i++) {
            Manager manager = managers.get(i);
            if(manager !=null&& manager.getId().equals(id)){
                index=i;
                break;
            }
        }
        return index;
    }

    public void updateManager(String updateId, Manager newmanager) {
//        查找ID所在索引位置
        int index=getIndex(updateId);
//        该索引位置替换
        managers.set(index,newmanager);
    }
}
