package lk.ijse.oceansync.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class StockFormController {

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColType;

    @FXML
    private JFXComboBox<?> cmbUserId;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private TextField lblItemId;

    @FXML
    private TextField lblName;

    @FXML
    private TextField lblQty;

    @FXML
    private TextField lblType;

    @FXML
    private AnchorPane rootnode;

    @FXML
    private TableView<?> tblStock;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {

    }

}
