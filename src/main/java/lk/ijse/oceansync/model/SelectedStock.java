package lk.ijse.oceansync.model;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SelectedStock {
    private  String itemId;
    private  int qty;
    private String customerId;
    private  String orderId;
    private Date date;
}
