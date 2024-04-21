package lk.ijse.oceansync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.oceansync.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HomeFormController {

    @FXML
    private Label lblActivityCount;

    @FXML
    private Label lblCourceCount;

    @FXML
    private Label lblCustomerCount;

    @FXML
    private Label lblDayStatus;

    @FXML
    private AnchorPane rootNode;



    private int customerCount;
    private int courcesCount;
    private int activityCount;


    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }

    public void initialize() {
        setDayStatus();
        try {
            customerCount = getCustomerCount();
            courcesCount = getCourceCount();
            activityCount = getActivityCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setCustomerCount(customerCount);
        setCourceCount(courcesCount);
        setActivityCount(activityCount);
    }

    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    private int getCustomerCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS customer_count FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int customerCount = 0;
        if (resultSet.next()) {
            customerCount = resultSet.getInt("customer_count");
        }
        return customerCount;
    }

    private void setCourceCount(int courcesCount) {
        lblCourceCount.setText(String.valueOf(this.courcesCount));
    }

    private int getCourceCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS cource_count FROM cource";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int courcesCount = 0;
        if (resultSet.next()) {
            courcesCount = resultSet.getInt("cource_count");
        }
        return courcesCount;

    }

    private void setActivityCount(int activityCount) {
        lblActivityCount.setText(String.valueOf(this.activityCount));
    }

    private int getActivityCount() throws SQLException {
        String sql = "SELECT COUNT(*) AS activity_count FROM activity";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        int activityCount = 0;
        if (resultSet.next()) {
            activityCount = resultSet.getInt("activity_count");
        }
        return activityCount;
    }

    private void setDayStatus() {
            LocalDateTime now = LocalDateTime.now();
            lblDayStatus.setText(String.valueOf(now));
    }
}
