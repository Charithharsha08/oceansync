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
            System.out.println("Payment save " +isPaymentSaved);
            if (isPaymentSaved) {
                boolean isSelectedCourseSaved = SelectedCourceRepo.saveSelectedCource(pp.getSelectedCources());
                System.out.println("Selected Cource save");
                if (isSelectedCourseSaved) {
                    boolean isPlaceSaved = SelectedActivityRepo.saveSelectedActivity(pp.getSelectedActivities());
                    System.out.println("Selected Activity save ");
                    if (isPlaceSaved) {
                        boolean isStockQtyUpdate = StockRepo.updateStockQty(pp.getStocks());
                        System.out.println("Update Stock");
                        if (isStockQtyUpdate) {
                            connection.commit();
                            return true;
                        }
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
