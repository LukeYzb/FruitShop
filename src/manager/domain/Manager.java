package manager.domain;

public class Manager extends Obj {
    //    password密码
    private String password;

    public Manager() {
    }

    //id顾客id，name顾客名称，password密码
    public Manager(String id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "password='" + password + '\'' +
                '}';
    }

    public String toTxt() {
        return this.getId() + ","
                + this.getName() + ","
                + password;
    }

    public static Manager toObj(String s) {
        if (s == null) {
            return null;
        }
        Manager manager = new Manager();
        String[] split = s.split(",");
        manager.setId(split[0]);
        manager.setName(split[1]);
        manager.setPassword(split[2]);
        return manager;
    }

    @Override
    public void putString(String s) {
        if (s == null) {
            return;
        }
        String[] split = s.split(",");
        this.setId(split[0]);
        this.setName(split[1]);
        this.setPassword(split[2]);
    }
}
