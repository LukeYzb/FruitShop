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

    //id顾客id，name顾客名称，password密码, money顾客金额
    public Customer(String id, String name, String password, String money) {
        super(id, name);
        this.password = password;
        this.money = money;
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

    public String toTxt() {
        return this.getId() + ","
                + this.getName() + ","
                + password + ","
                + money;
    }

    public static Customer toObj(String s) {
        if (s == null) {
            return null;
        }
        Customer customer = new Customer();
        String[] split = s.split(",");
        customer.setId(split[0]);
        customer.setName(split[1]);
        customer.setPassword(split[2]);
        customer.setMoney(split[3]);
        return customer;
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
        this.setMoney(split[3]);
    }
}
