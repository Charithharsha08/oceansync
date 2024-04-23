package lk.ijse.oceansync.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oceansync.controller.repository.StockRepo;
import lk.ijse.oceansync.db.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    private List<DiscountFormController> discountList = new ArrayList<>();

    DiscountFormController(String discountId, String type, int discount){
        this.discountId = discountId;
        this.type = type;
        this.discount = discount;
    }


    public void initialize() {
       // this.discountList = getAllDiscount();

        getDiscountTypes();
        setCellValue();
        loadDiscountTable();
    }



    public static List<DiscountFormController> getAll() throws SQLException {
        List<DiscountFormController> discountList = new ArrayList<>();
        String sql = "SELECT * FROM discount";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
        List<DiscountFormController> discountFormControllers = new ArrayList<>();
        while (resultSet.next()){

            String discountId = resultSet.getString(1);
            String type = resultSet.getString(2);
            int discount = resultSet.getInt(3);

            DiscountFormController discountFormController = new DiscountFormController(discountId,type,discount);
            discountList.add(discountFormController);
        }
        return discountList;
    }

    private void loadDiscountTable() {
        ObservableList<DiscountTm> discount = FXCollections.observableArrayList();
        for (DiscountFormController discountFormController : discountList) {
            DiscountTm discountTm = new DiscountTm(
                    discountFormController.getDiscountId(),
                    discountFormController.getType(),
                    discountFormController.getDiscount()

            );
            discount.add(discountTm);
        }
        tblDiscount.setItems(discount);
        System.out.println(discount);
        DiscountTm selectedStock = tblDiscount.getSelectionModel().getSelectedItem();
        System.out.println(selectedStock);
    }

    private void setCellValue() {
        colDisId.setCellValueFactory(new PropertyValueFactory<>("discountId"));
        colType.setCellValueFactory(new PropertyValueFactory<>("localOrForeign"));
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
        int discount = Integer.parseInt(txtDiscount.getText());
        discountSave(discountId, typeAsString, discount);

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

    public  void discountUpdate(String discountId, String localOrForeign, int discount) throws SQLException {
        String sql = "UPDATE discount SET type=?, discount=? WHERE id=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, discountId);
        pstm.setObject(2, localOrForeign);
        pstm.setObject(3, discount);
        if (pstm.executeUpdate() > 0){
            new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
            clearFields();
        }
    }

    public  void discountDelete(String id) throws SQLException {
        try {
        String sql = "DELETE FROM discount WHERE id=?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        pstm.setObject(1, id);
        if (pstm.executeUpdate() > 0) {
            new Alert(Alert.AlertType.CONFIRMATION, "DELETED SUCCESSFULLY").show();
        }
    }catch(SQLException e){
        new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
    }
}

    public void discountSave(String discountId, String localOrForeign, int discount) throws SQLException {
        try {
            String sql = "INSERT INTO discount VALUES(?, ?, ?)";

            PreparedStatement pstm = DbConnection.getInstance().getConnection()
                    .prepareStatement(sql);
            pstm.setObject(1, discountId);
            pstm.setObject(2, localOrForeign);
            pstm.setObject(3, discount);

            if (pstm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
            }
            }catch(SQLException e){
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }
}

enum DiscountType {
    LOCAL, FOREIGN
}
