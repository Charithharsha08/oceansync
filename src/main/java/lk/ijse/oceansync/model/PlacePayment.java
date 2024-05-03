package lk.ijse.oceansync.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlacePayment {
    private Payment payment;
    private List<SelectedActivity> selectedActivities;
    private List<SelectedCource> selectedCources;
    private List<Stock> stocks;
}
