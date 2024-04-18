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


    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
    }

    public void initialize() {
        try {
            customerCount = getCustomerCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setCustomerCount(customerCount);
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
        if(resultSet.next()) {
            customerCount = resultSet.getInt("customer_count");
        }
        return customerCount;
    }
    public void setLblCourceCount() {
        lblCourceCount.setText(String.valueOf(courcesCount));
    }
    public void getCourceCount() {
        String sql = "SELECT COUNT(*) AS cources_count FROM customer";

    }

}
