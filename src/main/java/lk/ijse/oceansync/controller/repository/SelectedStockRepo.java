package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.SelectedStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SelectedStockRepo {

    public static boolean save(SelectedStock selectedStock) throws SQLException, ClassNotFoundException {
        System.out.println(selectedStock.toString());
        Connection con = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = con.prepareStatement("INSERT INTO selectedStock VALUES(?,?,?,?,?)");
        pstm.setObject(1, selectedStock.getItemId());
        pstm.setObject(2, selectedStock.getQty());
        pstm.setObject(3, selectedStock.getCustomerId());
        pstm.setObject(4, selectedStock.getOrderId());
        pstm.setObject(5, selectedStock.getDate());
        return pstm.executeUpdate() > 0;

    }
}
