package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.Stock;
import lk.ijse.oceansync.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    public static List<String> getUserIds() throws SQLException {
        String sql = "SELECT userId FROM user";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> userIdList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();

        while(resultSet.next()) {
            userIdList.add(resultSet.getString(1));
        }
        //System.out.print(userIdList);
        return userIdList;
    }
    public static User userSearchById(String id) throws SQLException {
        String sql = "SELECT * FROM user WHERE userId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        pstm.setObject(1, id);
        ResultSet resultSet = pstm.executeQuery();

        User user = null;

        if (resultSet.next()) {
            String userId = resultSet.getString(1);
            String userName = resultSet.getString(2);
            String password = resultSet.getString(3);



            user = new User(userId, userName, password);
        }
        return user;
    }

}


