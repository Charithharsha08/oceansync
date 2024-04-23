package lk.ijse.oceansync.controller.repository;

import javafx.scene.control.Alert;
import lk.ijse.oceansync.controller.DiscountFormController;
import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.Discount;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountRepo {
    public static List<Discount> getAllDiscount() throws SQLException {
        String sql = "SELECT * FROM discount";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        List<Discount> discountList = new ArrayList<>();
        while (resultSet.next()) {
            String discountId = resultSet.getString(1);
            String type = resultSet.getString(2);
            int discount = resultSet.getInt(3);

            Discount discount1 = new Discount(discountId, type, discount);
            discountList.add(discount1);
        }
        return discountList;
    }

    public boolean discountUpdate(Discount discount) throws SQLException {
        String sql = "UPDATE discount SET type=?, discount=? WHERE id=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(3, discount.getDiscountId());
        pstm.setObject(1, discount.getType());
        pstm.setObject(2, discount);
        return pstm.executeUpdate() > 0;

    }

    public boolean discountDelete(String discountId) throws SQLException {

        String sql = "DELETE FROM discount WHERE id=?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, discountId);
        return pstm.executeUpdate() > 0;

    }

    public static boolean discountSave(Discount discount) throws SQLException {
        PreparedStatement pstm = null;
        try {
            String sql = "INSERT INTO discount VALUES(?, ?, ?)";

            pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);
            pstm.setObject(1, discount.getDiscountId());
            pstm.setObject(2, discount.getType());
            pstm.setObject(3, discount.getDiscount());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pstm.executeUpdate() > 0;

    }
}
