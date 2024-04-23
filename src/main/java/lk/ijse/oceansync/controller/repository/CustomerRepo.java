package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.Customer;
import lk.ijse.oceansync.model.Stock;
import lk.ijse.oceansync.model.tm.CustomerTm;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepo {
    public static boolean customerSave(Customer customer) throws SQLException {

        System.out.println(customer);

        String sql = "INSERT INTO customer VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, customer.getCustomerId());
        pstm.setObject(2, customer.getName());
        pstm.setObject(3, customer.getAddress());
        pstm.setObject(4, customer.getTel());

        return pstm.executeUpdate() > 0;

    }


    public static List<Customer> getAll() throws SQLException {
        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        List<Customer> customerList = new ArrayList<>();
        while (resultSet.next()){

            String customerId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            String contact = resultSet.getString(4);

            Customer customer = new Customer(customerId,name,address,contact);
            customerList.add(customer);
        }
        return customerList;
    }
    public static boolean customerUpdate(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name=?, address=?, tel=? WHERE customerId=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, customer.getName());
        pstm.setObject(2, customer.getAddress());
        pstm.setObject(3, customer.getTel());
        pstm.setObject(4, customer.getCustomerId());


        return pstm.executeUpdate() > 0;

    }

    public static boolean customerDelete(String customerId) throws SQLException {

        String sql = "DELETE FROM customer WHERE customerId=?";
        PreparedStatement pstm = null;
        try {
            pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);
            pstm.setObject(1, customerId);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstm.executeUpdate() > 0;
    }
}
