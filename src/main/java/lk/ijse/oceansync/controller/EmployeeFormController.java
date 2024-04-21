package lk.ijse.oceansync.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lk.ijse.oceansync.controller.repository.UserRepo;

import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {

    @FXML
    private JFXComboBox<String> cmbUserId;

    @FXML
    private TableColumn<?, ?> colActivity;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblId;

    @FXML
    private TableView<?> tblStock;

    @FXML
    private TextField txtActivity;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    public void initialize() {
        getUserId();
    }

    private void setCellValue() {
    }

    private void getUserId() {

        ObservableList<String> userIds = FXCollections.observableArrayList();
        try {
            List<String> userIdList = UserRepo.getUserIds();
            for (String userId : userIdList) {
                userIds.add(userId);
            }
            cmbUserId.setItems(userIds);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnActin(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {

    }

}
