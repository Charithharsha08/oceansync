package lk.ijse.oceansync.model.tm;

public class StockTm {
    private String itemId;
    private String name;
    private String type;
    private String qty;
    private String userId;

    public StockTm(String itemId, String name, String type, String qty, String userId) {
        this.itemId = itemId;
        this.name = name;
        this.type = type;
        this.qty = qty;
        this.userId = userId;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "StockTm{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", qty='" + qty + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}