package lk.ijse.oceansync.model;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class Activity {

    private String activityId;
    private String name;
    private String type;
    private String location;
    private double cost;
}
