package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.Payment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentRepo {

//    public static void paymentSave() {
//        String sql = "INSERT INTO payment VALUES (?,?,?,?,?,?)";
//        try {
//            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(sql);
//            stm.setObject(1, null);
//            stm.setObject(2, null);
//            stm.setObject(3, null);
//            stm.setObject(4, null);
//            stm.setObject(5, null);
//            stm.setObject(6, null);
//            stm.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static String currerntId() {
        String sql = "SELECT paymentId FROM payment ORDER BY paymentId DESC LIMIT 1";
        try {
            PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet rst = stm.executeQuery();
            if (rst.next()) {
                return rst.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static boolean savePayment(Payment payment)  {
        System.out.println("Save payment ekatath awa");
        System.out.println(payment);

        try {
            String sql = "INSERT INTO payment VALUES (?,?,?,?,?)";

            PreparedStatement pstm = null;
            pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);
            pstm.setObject(1, payment.getPaymentId());
            pstm.setObject(2, payment.getType());
            pstm.setObject(3,payment.getTotal());
            pstm.setObject(4, payment.getDate());
            pstm.setObject(5, payment.getCustomerId());

            System.out.println(payment.getPaymentId());
            System.out.println("save uand ndaa");
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("exeption ekkuth awa ");
            throw new RuntimeException(e);
        }

    }
}
