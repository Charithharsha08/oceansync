package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.SelectedActivity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SelectedActivityRepo {
    public static boolean saveSelectedActivity(List<SelectedActivity> selectedActivities) {
        System.out.println("selected activity ekat awa ");
        for (SelectedActivity selectedActivity : selectedActivities) {
            System.out.println(selectedActivity.toString());
        }
        String sql = "INSERT INTO selectedActivity VALUES(?,?,?)";
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(sql);
           for (SelectedActivity selectedActivitie: selectedActivities) {
               stm.setObject(1, selectedActivitie.getActivityId());
               stm.setObject(2, selectedActivitie.getCustomerId());
               stm.setObject(3, selectedActivitie.getDate());
               if (stm.executeUpdate() != 1) {
                   return false;
               }
           }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
