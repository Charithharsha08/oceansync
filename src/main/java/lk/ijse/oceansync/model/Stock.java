package lk.ijse.oceansync.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Stock {

    private String itemId;
    private String name;
    private double price;
    private String qty;
    private String userId;
}
