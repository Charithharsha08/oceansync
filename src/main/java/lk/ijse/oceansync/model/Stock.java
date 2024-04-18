package lk.ijse.oceansync.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Stock {

    private String itemId;
    private String name;
    private String type;
    private String qtyOnHand;
}
