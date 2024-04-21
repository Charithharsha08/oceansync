package lk.ijse.oceansync.controller.repository;

import lk.ijse.oceansync.db.DbConnection;
import lk.ijse.oceansync.model.Employee;
import lk.ijse.oceansync.model.Stock;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {

    public static boolean employeeSave(Employee employee) throws SQLException {

        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?,?,?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, employee.getId());
        pstm.setObject(2, employee.getEmployeeId());
        pstm.setObject(3, employee.getName());
        pstm.setObject(4, employee.getActivity());
        pstm.setObject(5, employee.getMonth());
        pstm.setObject(6, employee.getSalary());
        pstm.setObject(7, employee.getDate());
        pstm.setObject(8, employee.getUserId());


        return pstm.executeUpdate() > 0;
    }

    public static List<Employee> getAll() throws SQLException {
        String sql = "SELECT * FROM employee";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {

            String id = resultSet.getString(1);
            String employeeId = resultSet.getString(2);
            String name = resultSet.getString(3);
            String activity = resultSet.getString(4);
            String month = resultSet.getString(5);
            String salary = resultSet.getString(6);
            String date = resultSet.getString(7);
            String userId = resultSet.getString(8);

           Employee employee = new Employee(id, employeeId, name, activity, month, salary, date, userId);
            employeeList.add(employee);
        }
        return employeeList;
    }
    public static boolean stemployeeSave(Employee employee) throws SQLException {

        String sql = "INSERT INTO employee VALUES(?, ?, ?, ?,?,?,?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getActivity());
        pstm.setObject(4, employee.getMonth());
        pstm.setObject(5, employee.getSalary());
        pstm.setObject(6, employee.getDate());
        pstm.setObject(7, employee.getUserId());

        return pstm.executeUpdate() > 0;
    }
    public static boolean employeeUpdate(Employee employee) throws SQLException {

        String sql = "UPDATE employee SET employeeId=?, name=?, activity=?, month=?, salary=?, date=?, userId=? WHERE id=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, employee.getEmployeeId());
        pstm.setObject(2, employee.getName());
        pstm.setObject(3, employee.getActivity());
        pstm.setObject(4, employee.getMonth());
        pstm.setObject(5, employee.getSalary());
        pstm.setObject(6, employee.getDate());
        pstm.setObject(7, employee.getUserId());
        pstm.setObject(8, employee.getId());

        return pstm.executeUpdate() > 0;
    }
    public static boolean employeeDelete(String id) throws SQLException {

        String sql = "DELETE FROM employee WHERE id=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, id);
        return pstm.executeUpdate() > 0;
    }

    public static Employee employeeSearchById(String userId) {
        return null;
    }
}
