package manager.domain;

public class Manager extends Obj {
    //    password密码
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Manager() {
    }

    //id顾客id，name顾客名称，password密码
    public Manager(String id, String name, String password) {
        super(id, name);
        this.password = password;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "password='" + password + '\'' +
                '}';
    }
}
