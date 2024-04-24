package lk.ijse.oceansync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oceansync.controller.repository.DiscountRepo;
import lk.ijse.oceansync.controller.repository.StockRepo;
import lk.ijse.oceansync.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lk.ijse.oceansync.model.Discount;
import lk.ijse.oceansync.model.Stock;
import lk.ijse.oceansync.model.tm.DiscountTm;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data

public class DiscountFormController {

    private String discountId;
    private String type;
    private int discount;


    @FXML
    private ComboBox<DiscountType> cmbType;

    @FXML
    private TableColumn<?, ?> colDis;

    @FXML
    private TableColumn<?, ?> colDisId;

    @FXML
    private TableColumn<?, ?> colType;

    @FXML
    private TableView<DiscountTm> tblDiscount;

    @FXML
    private TextField txtDisId;

    @FXML
    private TextField txtDiscount;

    private List<Discount> discountList = new ArrayList<>();



    public void initialize() {
    this.discountList =getAllDiscount();
        getDiscountTypes();
        setCellValue();
        loadDiscountTable();
    }

    private List<Discount> getAllDiscount() {
        List<Discount> discountList = null;
        try {
            discountList = DiscountRepo.getAllDiscount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return discountList;
    }


    private void loadDiscountTable() {
        ObservableList<DiscountTm> discounts = FXCollections.observableArrayList();
        for (Discount discount1 : discountList) {
            DiscountTm discountTm = new DiscountTm(
                    discount1.getDiscountId(),
                    discount1.getType(),
                    discount1.getDiscount()
            );
            discounts.add(discountTm);
        }
        tblDiscount.setItems(discounts);
        //System.out.println(discount);
        DiscountTm selectedStock = tblDiscount.getSelectionModel().getSelectedItem();
       // System.out.println(selectedStock);
    }

    private void setCellValue() {
        colDisId.setCellValueFactory(new PropertyValueFactory<>("discountId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colDis.setCellValueFactory(new PropertyValueFactory<>("discount"));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private  void clearFields() {
        //cmbType.setValue();
        txtDisId.setText("");
        txtDiscount.setText("");

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String discountId = txtDisId.getText();
        DiscountType type = cmbType.getValue();
        String typeAsString = type.toString();
        if (discountId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter discount id").show();
            return;
        }
        if (txtDiscount.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter discount").show();
            return;
        }
        int discount = Integer.parseInt(txtDiscount.getText());
        Discount discount1 = new Discount(discountId, typeAsString, discount);
        try {
            boolean discountSave = DiscountRepo.discountSave(discount1);
            if (discountSave) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbTypeOnAction(ActionEvent event) {
        String type = cmbType.getValue().toString();

    }
    void getDiscountTypes() {
        cmbType.getItems().clear();
        for (DiscountType type : DiscountType.values()) {
            cmbType.getItems().add(type);
        }
    }
}

enum DiscountType {
    LOCAL, FOREIGN
}
