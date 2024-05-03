package lk.ijse.oceansync.model.tm;

public class StockTm {
    private String itemId;
    private String name;
    private double price;
    private String qty;
    private String userId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public StockTm(String itemId, String name, double price, String qty, String userId) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.userId = userId;

    }

    @Override
    public String toString() {
        return "StockTm{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty='" + qty + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}