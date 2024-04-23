package lk.ijse.oceansync.model;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Cource {
    private String courceId;
    private String name;
    private String duration;
    private double cost;
}
