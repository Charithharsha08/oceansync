package lk.ijse.oceansync.model.tm;
import com.jfoenix.controls.JFXButton;


public class PaymentTm {

    private String paymentId;
    private String name;
    private String description;
    private double unitPrice;
    private int qty;
    private int discount;
    private double total;
    private JFXButton btn;

    public PaymentTm(String paymentId, String name, String description, double unitPrice, int qty, int discount, double total, JFXButton btn) {
        this.paymentId = paymentId;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        this.discount = discount;
        this.total = total;
        this.btn = btn;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public JFXButton getBtn() {
        return btn;
    }

    public void setBtn(JFXButton btn) {
        this.btn = btn;
    }
}