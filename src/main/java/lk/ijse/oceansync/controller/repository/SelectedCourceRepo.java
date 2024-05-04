package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.SelectedCource;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class SelectedCourceRepo {
    public static boolean saveSelectedCource(List<SelectedCource> selectedCources)  {
        System.out.println("selected cource repo eke save ekt awa");
       String sql = "INSERT INTO selectedcource VALUES(?,?)";
       try {
           PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(sql);
           for (SelectedCource selectedCource : selectedCources) {
               stm.setObject(1, selectedCource.getCustomerId());
               stm.setObject(2, selectedCource.getCourceId());

           }
           return stm.executeUpdate() > 0;
       } catch (SQLException e) {
           throw new RuntimeException(e);
       }

    }
}
