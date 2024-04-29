package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.PlacePayment;

import java.sql.Connection;
import java.sql.SQLException;

public class PlacePaymentRepo {
    public static boolean placePayment(PlacePayment pp) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            boolean isPaymentSaved = PaymentRepo.savePayment(pp.getPayment());
            if (isPaymentSaved) {
                boolean isSelectedCourseSaved = SelectedCourceRepo.saveSelectedCource(pp.getSelectedCources());
                if (isSelectedCourseSaved) {
                    boolean isPlaceSaved = SelectedActivityRepo.saveSelectedActivity(pp.getSelectedActivities());
                    if (isPlaceSaved) {
                        connection.commit();
                        return true;
                    }
                }

            }
            connection.rollback();
            return false;
        }catch (Exception e) {
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

}
