package lk.ijse.oceansync.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.ijse.oceansync.controller.repository.StockRepo;
import lk.ijse.oceansync.controller.repository.UserRepo;
import lk.ijse.oceansync.model.Stock;
import lk.ijse.oceansync.model.User;
import lk.ijse.oceansync.model.tm.StockTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockFormController {

    @FXML
    private TableColumn<?, ?> ColName;

    @FXML
    private TableColumn<?, ?> ColType;

    @FXML
    private JFXComboBox<String> cmbUserId;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUserId;

    @FXML
    private AnchorPane rootnode;

    @FXML
    private TableView<StockTm> tblStock;

    @FXML
    private TextField txtItemId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtType;

    @FXML
    private Label lblUsername;

    private List<Stock> stockList = new ArrayList<>();

    public void initialize() {
        this.stockList = getAllStock();
        getUserId();
        setCellValue();
        loadStockTable();
    }

    private List<Stock> getAllStock() {
        List<Stock> stockList = null;
        try {
            stockList =StockRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return stockList;
    }

    private void loadStockTable() {
        ObservableList<StockTm> stocks = FXCollections.observableArrayList();
        for (Stock stock : stockList) {
            StockTm stockTm = new StockTm(
                    stock.getItemId(),
                    stock.getName(),
                    stock.getType(),
                    stock.getQty(),
                    stock.getUserId()
            );
          stocks.add(stockTm);
        }
        tblStock.setItems(stocks);
        System.out.println(stocks);
        StockTm selectedStock = tblStock.getSelectionModel().getSelectedItem();
    }

    private void setCellValue() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        ColName.setCellValueFactory(new PropertyValueFactory<>("name"));
        ColType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
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
            clearFields();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
       String userId = cmbUserId.getValue();
       String itemId = txtItemId.getText();
       String name = txtName.getText();
       String type = txtType.getText();
       String qty = txtQty.getText();

       if (qty.isEmpty()) {
           new Alert(Alert.AlertType.ERROR, "Please enter quantity").show();
           return;
       }
       if (name.isEmpty()) {
           new Alert(Alert.AlertType.ERROR, "Please enter name").show();
           return;
       }
       if (type.isEmpty()) {
           new Alert(Alert.AlertType.ERROR, "Please enter type").show();
           return;
       }
       if (itemId.isEmpty()) {
           new Alert(Alert.AlertType.ERROR, "Please enter item id").show();
           return;
       }
       Stock stock = new Stock(itemId, name, type, qty, userId);
        try {
            boolean stockSave = StockRepo.stockSave(stock);
            if (stockSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            throw new RuntimeException(e);
        }


    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {

          String userId = cmbUserId.getValue();



        try {
            User user =UserRepo.userSearchById(userId);
            lblUsername.setText(user.getUserName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
        txtItemId.setText("");
        txtName.setText("");
        txtType.setText("");
        txtQty.setText("");
        lblUsername.setText("");

    }

    public void btnDeleteOnActin(ActionEvent actionEvent) {
        String itemId = txtItemId.getText();
        try {
            StockRepo.stockDelete(itemId);
            if (itemId != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String userId = cmbUserId.getValue();
        String itemId = txtItemId.getText();
        String name = txtName.getText();
        String type = txtType.getText();
        String qty = txtQty.getText();

       Stock stock =  new Stock(itemId, name, type, qty, userId);
        try {
            StockRepo.stockUpdate(stock);
            if (itemId != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            throw new RuntimeException(e);
        }
    }
}
