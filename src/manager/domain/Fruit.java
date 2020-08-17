package manager.domain;

public class Fruit extends Obj {
//amount库存
    private String amount;
//    水果价格
    private String price;

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Fruit() {
    }
//id水果id，name水果名称，price水果价格，amount库存
    public Fruit(String id, String name, String price, String amount) {
        super(id, name);
        this.price=price;
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "amount='" + amount + '\'' +
                ", price='" + price + '\'' +
                '}';
    }

    public String toTxt() {
        return this.getId() + ","
                + this.getName() + ","
                + price + ","
                + amount;
    }

    public static Fruit toObj(String s) {
        if (s == null) {
            return null;
        }
        Fruit fruit = new Fruit();
        String[] split = s.split(",");
        fruit.setId(split[0]);
        fruit.setName(split[1]);
        fruit.setPrice(split[2]);
        fruit.setAmount(split[3]);
        return fruit;
    }

    @Override
    public void putString(String s) {
        if (s == null) {
            return;
        }
        String[] split = s.split(",");
        this.setId(split[0]);
        this.setName(split[1]);
        this.setPrice(split[2]);
        this.setAmount(split[3]);
    }
}
