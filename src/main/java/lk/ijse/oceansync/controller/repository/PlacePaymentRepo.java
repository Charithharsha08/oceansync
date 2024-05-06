package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.PlacePayment;
import lk.ijse.oceansync.model.SelectedStock;

import java.sql.Connection;
import java.sql.SQLException;

public class PlacePaymentRepo {
    public static boolean placePayment(PlacePayment pp) throws SQLException {
        System.out.println("awa");
        System.out.println(pp.getPayment());
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);
        try {
            //System.out.println(pp.getPayment());
            boolean isPaymentSaved = PaymentRepo.savePayment(pp.getPayment());
            System.out.println("    Payment save");
            if (isPaymentSaved) {
                if (pp.getSelectedCources() != null) {
                    boolean isSelectedCourceSave = SelectedCourceRepo.saveSelectedCource(pp.getSelectedCources());
                    System.out.println("Selected Cource save");
                    if (isSelectedCourceSave) {
                        connection.commit();
                        return true;
                    }
                } else if (pp.getSelectedActivities() != null) {
                    boolean isSelectedActivity = SelectedActivityRepo.saveSelectedActivity(pp.getSelectedActivities());
                    System.out.println("Selected Activity save ");
                    if (isSelectedActivity) {
                        connection.commit();
                        return true;
                    }
                } else if (pp.getSelectedStock() != null) {
                    boolean isStockSelected = SelectedStockRepo.save(pp.getSelectedStock());
                    System.out.println("Selected Stock save");
                    if (isStockSelected) {
                        boolean isStockQtyUpdate = StockRepo.updateStockQtyOnHand(pp.getSelectedStock());
                        if (isStockQtyUpdate) {
                            connection.commit();
                            return true;
                        }
                    }
                }

            }connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }

    }}


