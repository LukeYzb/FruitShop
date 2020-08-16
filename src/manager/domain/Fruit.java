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
}
