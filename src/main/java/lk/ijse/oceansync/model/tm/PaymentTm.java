package lk.ijse.oceansync.model.tm;
import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentTm {
    private String paymentId;
    private String customerName;
    private String activityName;
    private double activityCost;
    private String courseName;
    private double courseCost;
    private String stock;
    private String qty;
    private double amount;
    private int discount;
    private double total;
    private JFXButton btnRemove;

}
