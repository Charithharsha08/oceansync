package lk.ijse.oceansync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oceansync.model.Customer;
import lk.ijse.oceansync.controller.repository.CustomerRepo;
import lk.ijse.oceansync.model.tm.CustomerTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerFormController {

    @FXML
    private Label lblUserId;
    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtTel;
    private List<Customer> customerList = new ArrayList<>();

    public void initialize(){
        this.customerList = getAllCustomers();
        loadUserName();
        setCellValueFactory();
        loadCustomerTable();
    }

    private List<Customer> getAllCustomers() {
        List<Customer> customerList = null;
        try {
            customerList = CustomerRepo.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return customerList;
    }



    private void setCellValueFactory() {

        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }
    private void loadCustomerTable() {
        ObservableList<CustomerTm> customerObservableList = FXCollections.observableArrayList();
        for (Customer customer : customerList) {
           // System.out.println(customerList);
            CustomerTm customerTm = new CustomerTm(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getTel()
                    );
            customerObservableList.add(customerTm);
            System.out.println(customerTm.toString());
        }
        tblCustomer.setItems(customerObservableList);
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
            txtCustomerId.setText("");
            txtName.setText("");
            txtAddress.setText("");
            txtTel.setText("");
            lblUserId.setText("");

    }

    @FXML
    void btnDeleteOnActin(ActionEvent event) {
               String customerId = txtCustomerId.getText();
        try {
            boolean customerDeleted = CustomerRepo.customerDelete(customerId);
            if (customerDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String customerId = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtTel.getText();
        Customer customer = new Customer(customerId,name,address,contact);
        try {
            boolean customerSaved = CustomerRepo.customerSave(customer);
            if (customerSaved){
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String customerId = txtCustomerId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String contact = txtTel.getText();

        Customer customer = new Customer(customerId,name,address,contact);
        try {
            boolean customerUpdated = CustomerRepo.customerUpdate(customer);
            if (customerUpdated){
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }


    private void loadUserName(){
        lblUserId.setText(LoginFormController.credential[1]);
    }

}
