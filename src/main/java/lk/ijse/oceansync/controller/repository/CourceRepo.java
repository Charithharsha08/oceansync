package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.Cource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourceRepo {
    public static void courceDelete(String courceId) {

        try {
            Connection con = DbConnection.getInstance().getConnection();
            String sql = "DELETE FROM cource WHERE courceId=?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setObject(1, courceId);
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean courceSave(Cource cource) throws SQLException {
            Connection con = DbConnection.getInstance().getConnection();
            String sql = "INSERT INTO cource VALUES(?,?,?,?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setObject(1, cource.getCourceId());
            pstm.setObject(2, cource.getName());
            pstm.setObject(3, cource.getDuration());
            pstm.setObject(4, cource.getCost());

            return pstm.executeUpdate() > 0;

    }

    public static boolean courceUpdate(Cource cource) throws SQLException {

        PreparedStatement pstm = null;
        try {
            Connection con = DbConnection.getInstance().getConnection();
            String sql = "UPDATE cource SET name=?,duration=? WHERE courceId=?";
            pstm = con.prepareStatement(sql);
            pstm.setObject(1, cource.getName());
            pstm.setObject(2, cource.getDuration());
            pstm.setObject(3, cource.getCourceId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstm.executeUpdate() > 0;
    }
    public static List<String> getAllCources() throws SQLException {

        String sql = "SELECT name FROM cource";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        List<String> cources = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            cources.add(name);
        }
        return cources;
    }


}
