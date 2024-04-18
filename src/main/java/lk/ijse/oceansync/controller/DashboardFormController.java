package lk.ijse.oceansync.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardFormController {

    @FXML
    private JFXButton btnHomeOnAction;

    @FXML
    private AnchorPane centerNode;

    @FXML
    private Pane inner_pane;

    @FXML
    private AnchorPane mostInnerPane;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private AnchorPane side_ankerpane;

    @FXML
    void btnActivityOnAction(ActionEvent event) throws IOException {
   AnchorPane activityPane = FXMLLoader.load(this.getClass().getResource("/view/activity_form.fxml"));
    this.centerNode.getChildren().clear();
    this.centerNode.getChildren().add(activityPane);
    }

    @FXML
    void btnCourcesOnAction(ActionEvent event) throws IOException {
        AnchorPane coursePane = FXMLLoader.load(this.getClass().getResource("/view/cources_form.fxml"));
        this.centerNode.getChildren().clear();
        this.centerNode.getChildren().add(coursePane);

    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
            AnchorPane customerPane = FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml"));
        this.centerNode.getChildren().clear();
        this.centerNode.getChildren().add(customerPane);
    }

    @FXML
    void btnEmopoleeOnAction(ActionEvent event) throws IOException {
        AnchorPane employeePane = FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml"));
        this.centerNode.getChildren().clear();
        this.centerNode.getChildren().add(employeePane);
    }

    public void initialize() throws IOException {
        LoadHomeForm();
    }

    private void LoadHomeForm() throws IOException {

        AnchorPane homePane = FXMLLoader.load(this.getClass().getResource("/view/home_form.fxml"));
        this.centerNode.getChildren().clear();
        this.centerNode.getChildren().add(homePane);
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
       LoadHomeForm();
    }

    @FXML
    void btnPaymentOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/payment_form.fxml"));

        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Payment Form");

        stage.show();
    }

    @FXML
    void btnStockOnAction(ActionEvent event) throws IOException {

        AnchorPane stockPane = FXMLLoader.load(this.getClass().getResource("/view/stock_form.fxml"));
        this.centerNode.getChildren().clear();
        this.centerNode.getChildren().add(stockPane);

    }

}
