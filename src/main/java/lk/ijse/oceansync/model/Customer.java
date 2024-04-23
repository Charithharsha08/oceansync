package lk.ijse.oceansync.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Customer {

    private String customerId;
    private String name;
    private String address;
    private String contact;
}
