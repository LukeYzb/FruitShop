package manager.util;

import manager.domain.Customer;
import manager.domain.Fruit;
import manager.domain.Manager;
import manager.domain.Obj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StreamUtils {

    public static <T extends Obj> List<T> findAll(Class<T> clazz) {
        String fileName = "";
        if (clazz.equals(Customer.class)) {
            fileName = "customer";
        } else if (clazz.equals(Fruit.class)) {
            fileName = "fruit";
        } else if (clazz.equals(Manager.class)) {
            fileName = "manager";
        } else {
            return null;
        }

        List<T> list = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("fruitshop\\" + fileName + ".txt"));
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
