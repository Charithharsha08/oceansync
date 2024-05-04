package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.SelectedActivity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SelectedActivityRepo {
    public static boolean saveSelectedActivity(List<SelectedActivity> selectedActivities) {
        System.out.println("selected activity ekat awa ");
        String sql = "INSERT INTO selectedactivity VALUES(?,?,?)";
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(sql);
           for (SelectedActivity selectedActivitie: selectedActivities) {
               stm.setObject(1, selectedActivitie.getCustomerId());
               stm.setObject(2, selectedActivitie.getActivityId());
           }
            return stm.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
