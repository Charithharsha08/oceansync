package lk.ijse.oceansync.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.oceansync.controller.repository.*;
import lk.ijse.oceansync.model.*;
import lk.ijse.oceansync.model.tm.PaymentTm;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentFormController {


    public Label lblStockAmount;
    public Label lblSelectedCourceCost;
    public Label lblSelectedActivityAmount;

    @FXML
    private Label lblNetTotal;

    @FXML
    private JFXComboBox<String> cmbSelectedActivity;

    @FXML
    private JFXComboBox<String> cmbSelectedCource;


    @FXML
    private JFXComboBox<String> cmbDiscount;


    @FXML
    private JFXComboBox<String> cmbStock;

    @FXML
    private JFXComboBox<?> cmbType;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colActivityName;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colActivityCost;

    @FXML
    private TableColumn<?, ?> colCourceCost;


    @FXML
    private TableColumn<?, ?> colCourceName;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDiscount;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStock;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private Label lblPaymentId;
    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearchTel;

    private ObservableList<PaymentTm> paymentList = FXCollections.observableArrayList();
    private double netTotal = 0;
    public  void initialize(){
        setCellValueFactory();
//        getAllPayments();
        loadNextPaymentId();
        loadAllCources();
        loadAllActivities();
        loadAllStocks();
        loadAllDiscounts();
    }
    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colActivityName.setCellValueFactory(new PropertyValueFactory<>("activityName"));
        colActivityCost.setCellValueFactory(new PropertyValueFactory<>("activityCost"));
        colCourceName.setCellValueFactory(new PropertyValueFactory<>("courceName"));
        colCourceCost.setCellValueFactory(new PropertyValueFactory<>("courceCost"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void loadAllStocks() {
        ObservableList<String> stocks = FXCollections.observableArrayList();
        try {
            List<String> stock = StockRepo.getAllStock();
            for (String name : stock) {
                stocks.add(name);
            }
            cmbStock.setItems(stocks);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllActivities() {
        ObservableList<String> activities = FXCollections.observableArrayList();
        try {
            List<String> activity = ActivityRepo.getAllActivities();
            for (String name : activity) {
                activities.add(name);
            }
            cmbSelectedActivity.setItems(activities);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllCources() {
        ObservableList<String> cources = FXCollections.observableArrayList();
        try {
            List<String> cource = CourceRepo.getAllCources();
            for (String name : cource) {
                cources.add(name);
            }
            cmbSelectedCource.setItems(cources);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllDiscounts(){
        ObservableList<String> discounts = FXCollections.observableArrayList();
        try {
            List<String> discount = DiscountRepo.getDiscount();
            for (String name : discount) {
                discounts.add(name);
            }
            cmbDiscount.setItems(discounts);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private  void loadNextPaymentId() {
        String currentId = PaymentRepo.currerntId();
        String nextId = nextId(currentId);
        lblPaymentId.setText(nextId);
    }

    private static String nextId(String currentId) {
        if (currentId != null) {
            String[] split = currentId.split("O");
//            System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
            int id = Integer.parseInt(split[1]);    //2
            return "O" + ++id;

        }
        return "O1";
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        String paymentId = lblPaymentId.getText();
        String customerName = txtCustomerName.getText();
        String activityName = (String) cmbSelectedActivity.getValue();
        double activityCost = Double.parseDouble(lblSelectedActivityAmount.getText());
        String courseName = (String) cmbSelectedCource.getValue();
        double courseCost = Double.parseDouble(lblSelectedCourceCost.getText());
        String stock = (String) cmbStock.getValue();
        String qty = txtQty.getText();
        String amount = txtAmount.getText();
        // String date = lblDate.getText();
        // String customerId = txtCustomerId.getText();
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type1 = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type1.orElse(no) == yes) {
                int selectedIndex = tblPayment.getSelectionModel().getSelectedIndex();
                paymentList.remove(selectedIndex);

                tblPayment.refresh();
                calculateNetTotal();
            }
        });
        for (int i = 0 ; i < tblPayment.getItems().size(); i++ ){
            if (paymentId.equals(colPaymentId.getCellData(i))){

                return;
            }
        }
    }

    private void calculateNetTotal() {

        netTotal = 0;
        for (int i = 0; i < tblPayment.getItems().size(); i++) {
            PaymentTm tm = tblPayment.getItems().get(i);
            netTotal += tm.getTotal();
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
    txtSearchTel.setText("");
    txtCustomerName.setText("");
    txtQty.setText("");
    txtAmount.setText("");
   // txtCustomerName.requestFocus();
    lblSelectedCourceCost.setText("");
    lblSelectedActivityAmount.setText("");
    lblNetTotal.setText("");
    tblPayment.getItems().clear();
    }

    @FXML
    void btnExitOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String paymentId = lblPaymentId.getText();
        String type = (String) cmbType.getValue();
        double amount = Double.parseDouble(txtAmount.getText());
        Date date = Date.valueOf(lblDate.getText());
        String customerName = txtCustomerName.getText();
        String activitId = (String) cmbSelectedActivity.getValue();
        String courceId = (String) cmbSelectedCource.getValue();

        var payment = new Payment(paymentId, type, amount, date, customerName);

        List<SelectedActivity> seAcList = new ArrayList<>();
        for (int i = 0; tblPayment.getItems().size() > i; i++) {
            PaymentTm tm = tblPayment.getItems().get(i);

            SelectedActivity selectedActivity = new SelectedActivity(
                    activitId,
            tm.getPaymentId()
            );
            seAcList.add(selectedActivity);

            List<SelectedCource> seCoList = new ArrayList<>();
            for (int j = 0; tblPayment.getItems().size() > j; j++) {
                PaymentTm tm1 = tblPayment.getItems().get(j);
                SelectedCource selectedCource = new SelectedCource(
                        courceId,
                        tm1.getPaymentId()
                );
                seCoList.add(selectedCource);
            }
            PlacePayment pp = new PlacePayment(payment, seAcList, seCoList);
            try {
                boolean isPlaced =PlacePaymentRepo.placePayment(pp);
                if (isPlaced){
                    new Alert(Alert.AlertType.INFORMATION, "payment Placed").show();
                    clearFields();
                }else {
                    new Alert(Alert.AlertType.INFORMATION, "payment Not Placed").show();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }


    @FXML
    void btncreateNewCustomerOnAction(ActionEvent event) throws IOException {

        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/customer_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Payment Form");

        stage.show();
    }

    @FXML
    void cmbSelectedActivityOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSelectedCourceOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStockOnAction(ActionEvent event) {

    }

    @FXML
    void cmbTypeOnAction(ActionEvent event) {

    }


    @FXML
    void txtAmountOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {
        txtCustomerName.requestFocus();
    }

    @FXML
    void txtCustomerNameOnAction(ActionEvent event) {
        txtQty.requestFocus();
    }



    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddOnAction(event);
    }

    @FXML
    void txtSearchTelOnAction(ActionEvent event) {
        String tel = txtSearchTel.getText();
        try {
            Customer customer = CustomerRepo.getCustomerByTel(tel);

            txtCustomerId.setText(customer.getCustomerId());
            txtCustomerName.setText(customer.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void cmbDiscountOnAction(ActionEvent event) {

    }


}
