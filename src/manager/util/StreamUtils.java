package manager.util;

import manager.domain.Customer;
import manager.domain.Fruit;
import manager.domain.Manager;
import manager.domain.Obj;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StreamUtils {

    /**
     * 用于底层customer,fruit,manager的findall方法调用
     *
     * @param clazz
     * @param <T>
     * @return一个list集合
     */
    public static <T extends Obj> List<T> findAll(Class<T> clazz) {
        String fileName = fileName(clazz);
        List<T> list = null;
        list = readIO(clazz, fileName, list);
        return list;
    }


    /**
     * 用于底层customer,fruit,manager的add方法调用
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Obj> boolean add(Class<T> clazz, T t) {
        String fileName = fileName(clazz);
        T byId = getById(clazz, t.getId());
        if (byId != null) {
            return false;
        }
        //得到findAllCustomer的List
        List<T> all = findAll(clazz);
        //在List里添加参数customer
        all.add(t);
        //将新的List写入IO流
        writeIO(fileName, all);
        return true;
    }


    /**
     * 通过ID删除特性对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Obj> boolean deleteById(Class<T> clazz, String delId) {
        String fileName = fileName(clazz);
        //如果ID不存在,就不能继续删除
        T byId = getById(clazz, delId);
        if (byId == null) {
            return false;
        }
        //得到findAllCustomer的list
        List<T> all = findAll(clazz);
        //遍历list,删掉byId对象,得到一个新的list
//        需要修改，list移除指定对象，下一行代码不可行
        all.remove(byId);
        //将新的list写进本地customer文件中
        writeIO(fileName, all);
        return true;
    }

    /**
     * 通过id查询特定对象
     *
     * @param id
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Obj> T getById(Class<T> clazz, String id) {
        //先通过findAllCustomer得到全部对象的list
        List<T> all = findAll(clazz);
        //将list遍历转换成String
        for (T t : all) {
            String s = t.toTxt();
            String[] strings = s.split(",");
            if (strings[0].equals(id)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 通过姓名得到特定对象
     * @param clazz
     * @param name
     * @param <T>
     * @return
     */
    public static <T extends Obj> T getByName(Class<T> clazz, String name) {
        //先通过findAllCustomer得到全部对象的list
        List<T> all = findAll(clazz);
        //将list遍历转换成String
        for (T t : all) {
            String s = t.toTxt();
            String[] strings = s.split(",");
            if (strings[1].equals(name)) {
                return t;
            }
        }
        return null;
    }

    /**
     * 更新对象
     *
     * @param clazz
     * @param t
     * @param <T>
     * @return
     */
    public static <T extends Obj> boolean update(Class<T> clazz, T t) {
        //将参数customer里的id提取出来
        String[] strings = t.toTxt().split(",");
        String id = strings[0];
        //通过id用deleteCustomerById删掉原对象
        boolean d = deleteById(clazz, id);
        //再addCustomer,将新的参数对象添加
        boolean a = add(clazz, t);
        //当deleteCustomerById和addCustomer都返回true时,则为true
        return d && a;
    }


    /**
     * 用于提取类对象的名称
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends Obj> String fileName(Class<T> clazz) {
        if (clazz.equals(Customer.class)) {
            return "customer";
        } else if (clazz.equals(Fruit.class)) {
            return "fruit";
        } else if (clazz.equals(Manager.class)) {
            return "manager";
        } else {
            return null;
        }
    }

    /**
     * 底层写入方法
     *
     * @param fileName
     * @param all
     * @param <T>
     */
    private static <T extends Obj> void writeIO(String fileName, List<T> all) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(".\\" + fileName + ".txt"));
            //list遍历,totxt
            for (T t1 : all) {
                String s = t1.toTxt();
                bw.write(s);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 底层读取方法
     *
     * @param clazz
     * @param fileName
     * @param list
     * @param <T>
     * @return
     */
    private static <T extends Obj> List<T> readIO(Class<T> clazz, String fileName, List<T> list) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(".\\" + fileName + ".txt"));
            String s;
            list = new ArrayList<>();
            while ((s = br.readLine()) != null) {
                T t = clazz.newInstance();
                t.putString(s);
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}



