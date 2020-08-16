package manager.domain;

public class Customer extends Obj {
    //password密码
    private String password;
    //money金额
    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Customer() {
    }

    //id顾客id，name顾客名称，money顾客金额，password密码
    public Customer(String id, String name, String password, String money) {
        super(id, name);
        this.password = password;
        this.money=money;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "password='" + password + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
