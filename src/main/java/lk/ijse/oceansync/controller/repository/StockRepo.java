package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.Stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockRepo {
    public static boolean stockSave(Stock stock) throws SQLException {

        String sql = "INSERT INTO stock VALUES(?, ?, ?, ?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, stock.getItemId());
        pstm.setObject(2, stock.getName());
        pstm.setObject(3, stock.getPrice());
        pstm.setObject(4, stock.getQty());
        pstm.setObject(5, stock.getUserId());


        return pstm.executeUpdate() > 0;
    }

    public static boolean stockUpdate(Stock stock) throws SQLException {

        String sql = "UPDATE stock SET name=?, type=?, qty=?, userId=? WHERE itemId=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, stock.getItemId());
        pstm.setObject(2, stock.getPrice());
        pstm.setObject(3, stock.getQty());
        pstm.setObject(4, stock.getUserId());
        pstm.setObject(5, stock.getItemId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean stockDelete(String itemId) throws SQLException {

        String sql = "DELETE FROM stock WHERE itemId=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, itemId);
        return pstm.executeUpdate() > 0;
    }


    public static List<Stock> getAll() throws SQLException {
        String sql = "SELECT * FROM stock";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        List<Stock> stockList = new ArrayList<>();
        while (resultSet.next()) {

            String itemId = resultSet.getString(1);
            String name = resultSet.getString(2);
            double price = Double.parseDouble(resultSet.getString(3));
            String qty = resultSet.getString(4);
            String userId = resultSet.getString(5);

            Stock stock = new Stock(itemId, name, price, qty, userId);
            stockList.add(stock);
        }
        return stockList;
    }

    public static List<String> getAllStock() throws SQLException {
        String sql = "SELECT name FROM stock";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        List<String> stockList = new ArrayList<>();
        ResultSet resultSet = pstm.executeQuery();


        while (resultSet.next()) {
            String name = pstm.getResultSet().getString(1);
            stockList.add(name);
        }
        return stockList;
    }

    public static Stock getStockByStockName(String stockName) throws SQLException {
        String sql = "SELECT * FROM stock WHERE name=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, stockName);
        ResultSet resultSet = pstm.executeQuery();
        String itemId = null;
        String name = null;
        double price = 0;
        String qty = null;
        String userId = null;
        if (resultSet.next()) {
            itemId = resultSet.getString(1);
            name = resultSet.getString(2);
            price = Double.parseDouble(resultSet.getString(3));
            qty = resultSet.getString(4);
            userId = resultSet.getString(5);

        }
        return new Stock(itemId, name, price, qty, userId);
    }
    public static boolean updateStockQty(List<Stock> stocks) {
        for (Stock stock : stocks) {
            if(updateStockQty(stock)){
                return true;
            }
        }
        return true;
    }

    public static boolean updateStockQty(Stock stocks) {

        String sql = "UPDATE stock SET qty=? WHERE itemId=?";
        try {
            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);
            pstm.setObject(1, stocks.getQty());
            pstm.setObject(2, stocks.getItemId());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
