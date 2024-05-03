package lk.ijse.oceansync.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentFormController {

    @FXML
    private JFXComboBox<String> cmbDiscount;

    @FXML
    private JFXComboBox<String> cmbSelectedActivity;

    @FXML
    private JFXComboBox<String> cmbSelectedCource;

    @FXML
    private JFXComboBox<String> cmbStock;

    @FXML
    private JFXComboBox<String> cmbType;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colDescription;

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
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblActivityId;

    @FXML
    private Label lblCourceId;

    @FXML
    private Label lblDate;

    @FXML
    private Label lblDuration;

    @FXML
    private Label lblLocation;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblPaymentId;

    @FXML
    private Label lblSelectedActivityAmount;

    @FXML
    private Label lblSelectedCourceCost;

    @FXML
    private Label lblStockAmount;

    @FXML
    private Label lblStockId;

    @FXML
    private Label lblTime;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSearchTel;

    private ObservableList<PaymentTm> paymentList = FXCollections.observableArrayList();

    String activityId;
    String courceId;

    public void initialize() {
        setCellValueFactory();
//        getAllPayments();
        loadNextPaymentId();
        loadAllCources();
        loadAllActivities();
        loadAllStocks();
        loadAllDiscounts();
        loadDateAndTime();
        loadPaymentType();
    }

    private void loadPaymentType() {
        for (Type type : Type.values()) {
            cmbType.getItems().add(type.name());
        }
    }

    private void loadDateAndTime() {
        LocalDate now = LocalDate.now();
        LocalTime localTime = LocalTime.now().withNano(0);

        lblDate.setText(now.toString());
        lblTime.setText(localTime.toString());
        //System.out.println(localTime);
    }

    private void loadAllDiscounts() {
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

    private void loadNextPaymentId() {
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

    private void setCellValueFactory() {

        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));

    }

    @FXML
    void btnAddActivityOnAction(ActionEvent event) {
        String paymentId = lblPaymentId.getText();
        String courseId = lblCourceId.getText();
        String customerName = txtCustomerName.getText();
        activityId = lblActivityId.getText();
        String description = cmbSelectedActivity.getValue();
        double unitPrice = Double.parseDouble(lblSelectedActivityAmount.getText());
        int qty;
        if (txtQty.getText().equals("")) {
            qty = 1;
        } else {
            qty = Integer.parseInt(txtQty.getText());
        }
//        int discount = Integer.parseInt(cmbDiscount.getValue());
        int discount;
        if (cmbDiscount.getValue() == null) {
            discount = 0;
        } else {
            discount = Integer.parseInt(cmbDiscount.getValue());
        }

        double total = qty * unitPrice;
        if (customerName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a customer").show();
            return;
        }
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int selectedIndex = tblPayment.getSelectionModel().getSelectedIndex();
                paymentList.remove(selectedIndex);

                tblPayment.refresh();
                calculateNetTotal();
            }
        });
        for (int i = 0; i < tblPayment.getItems().size(); i++) {
            if (description.equals(colDescription.getCellData(i))) {
                qty += paymentList.get(i).getQty();
                total = unitPrice * qty;

                paymentList.get(i).setQty(qty);
                paymentList.get(i).setTotal(total);

                tblPayment.refresh();
                calculateNetTotal();
                txtQty.setText("");
                return;
            }
        }
        PaymentTm tm = new PaymentTm(activityId, customerName, description, unitPrice, qty, discount, total, btnRemove);
        paymentList.add(tm);
        tblPayment.setItems(paymentList);
        txtQty.setText("");
        calculateNetTotal();


    }

    private void calculateNetTotal() {

        double netTotal = 0;
        for (int i = 0; i < tblPayment.getItems().size(); i++) {
            PaymentTm tm = tblPayment.getItems().get(i);
            netTotal += tm.getTotal();
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }


    @FXML
    void btnAddCourceOnAction(ActionEvent event) {
        // String paymentId = lblPaymentId.getText();
        String customerName = txtCustomerName.getText();
        courceId = lblCourceId.getText();
        String description = cmbSelectedCource.getValue();
        double unitPrice = Double.parseDouble(lblSelectedCourceCost.getText());
        int qty;
        if (txtQty.getText().equals("")) {
            qty = 1;
        } else {
            qty = Integer.parseInt(txtQty.getText());
        }
        int discount;
        if (cmbDiscount.getValue() == null) {
            discount = 0;
        } else {
            discount = Integer.parseInt(cmbDiscount.getValue());
        }
        double total = qty * unitPrice;
        if (customerName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a customer").show();
            return;
        }
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int selectedIndex = tblPayment.getSelectionModel().getSelectedIndex();
                paymentList.remove(selectedIndex);

                tblPayment.refresh();
                calculateNetTotal();
            }
        });
        for (int i = 0; i < tblPayment.getItems().size(); i++) {
            if (description.equals(colDescription.getCellData(i))) {
                qty += paymentList.get(i).getQty();
                total = unitPrice * qty;

                paymentList.get(i).setQty(qty);
                paymentList.get(i).setTotal(total);

                tblPayment.refresh();
                calculateNetTotal();
                txtQty.setText("");
                return;
            }
        }
        PaymentTm tm = new PaymentTm(courceId, customerName, description, unitPrice, qty, discount, total, btnRemove);
        paymentList.add(tm);
        tblPayment.setItems(paymentList);
        txtQty.setText("");
        calculateNetTotal();

    }

    @FXML
    void btnAddStockOnAction(ActionEvent event) {
        //String paymentId = lblPaymentId.getText();
        String stockId = lblStockId.getText();
        String customerName = txtCustomerName.getText();
        String description = cmbStock.getValue();
        double unitPrice = Double.parseDouble(lblStockAmount.getText());
        int qty;
        if (txtQty.getText().equals("")) {
            qty = 1;
        } else {
            qty = Integer.parseInt(txtQty.getText());
        }
        int discount;
        if (cmbDiscount.getValue() == null) {
            discount = 0;
        } else {
            discount = Integer.parseInt(cmbDiscount.getValue());
        }
        double total = qty * unitPrice;
        if (customerName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please select a customer").show();
            return;
        }
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);

        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (type.orElse(no) == yes) {
                int selectedIndex = tblPayment.getSelectionModel().getSelectedIndex();
                paymentList.remove(selectedIndex);

                tblPayment.refresh();
                calculateNetTotal();
            }
        });
        for (int i = 0; i < tblPayment.getItems().size(); i++) {
            if (description.equals(colDescription.getCellData(i))) {
                qty += paymentList.get(i).getQty();
                total = unitPrice * qty;

                paymentList.get(i).setQty(qty);
                paymentList.get(i).setTotal(total);

                tblPayment.refresh();
                calculateNetTotal();
                txtQty.setText("");
                return;
            }
        }
        PaymentTm tm = new PaymentTm(stockId, customerName, description, unitPrice, qty, discount, total, btnRemove);
        paymentList.add(tm);
        tblPayment.setItems(paymentList);
        txtQty.setText("");
        calculateNetTotal();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtQty.setText("");
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        cmbSelectedCource.getSelectionModel().clearSelection();
        cmbSelectedActivity.getSelectionModel().clearSelection();
        cmbStock.getSelectionModel().clearSelection();
        cmbType.getSelectionModel().clearSelection();
        cmbDiscount.getSelectionModel().clearSelection();
        lblCourceId.setText("");
        lblSelectedCourceCost.setText("");
        lblSelectedActivityAmount.setText("");
        lblStockId.setText("");
        lblStockAmount.setText("");
        lblSelectedCourceCost.setText("");
        lblSelectedActivityAmount.setText("");
    }

    @FXML
    void btnExitOnAction(ActionEvent event) {
        Stage stage = (Stage) rootNode.getScene().getWindow();
        stage.close();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String paymentId = lblPaymentId.getText();
        String type = cmbType.getValue();
        double total = Double.parseDouble(lblNetTotal.getText());
        Date date = Date.valueOf(lblDate.getText());
        String customerId = txtCustomerId.getText();

        var payment = new Payment(paymentId, type, total, date, customerId);

        String gettedId = "null";
        char[] charArray = gettedId.toCharArray();
        List<SelectedActivity> selectedActivities = new ArrayList<>();
        List<SelectedCource> selectedCources = new ArrayList<>();

        for (int i = 0; i < tblPayment.getItems().size(); i++) {
            PaymentTm tm = tblPayment.getItems().get(i);
            gettedId = tm.getPaymentId();
            if (charArray[0] == 'A') {
                SelectedActivity sAct = new SelectedActivity(
                        tm.getPaymentId(),
                        customerId);
                selectedActivities.add(sAct);
            } else if (charArray[0] == 'C') {
                SelectedCource sCource = new SelectedCource(
                        tm.getPaymentId(),
                        customerId);
                selectedCources.add(sCource);
            } else if (charArray[0] == 'S') {

            }
        }



//        List<SelectedActivity> selectedActivities = new ArrayList<>();
//        for (int i = 0 ; i < tblPayment.getItems().size();i++){
//            PaymentTm tm = tblPayment.getItems().get(i);
//            SelectedActivity sAct = new SelectedActivity(
//                    activityId,
//                    customerId);
//            selectedActivities.add(sAct);
//        }

       // PlacePayment placePayment = new PlacePayment(payment, selectedActivities, null, null);
    }

    @FXML
    void btncreateNewActivityOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/activity_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Activity Form");

        stage.show();
    }

    @FXML
    void btncreateNewCourceOnAction(ActionEvent event) throws IOException {
        Parent rootNode = FXMLLoader.load(this.getClass().getResource("/view/cources_form.fxml"));
        Scene scene = new Scene(rootNode);
        Stage stage = new Stage();
        stage.setScene(scene);

        stage.setTitle("Course Form");

        stage.show();
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
    void cmbDiscountOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSelectedActivityOnAction(ActionEvent event) {
        String activity = (String) cmbSelectedActivity.getValue();
        try {
            Activity activity1 = ActivityRepo.getActivityByActivityName(activity);
            lblSelectedActivityAmount.setText(String.valueOf(activity1.getCost()));
            lblActivityId.setText(activity1.getActivityId());
            lblLocation.setText(activity1.getLocation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void cmbSelectedCourceOnAction(ActionEvent event) {
        String cource = (String) cmbSelectedCource.getValue();
        try {
            Cource cource1 = CourceRepo.getCourceByCourceName(cource);
            lblSelectedCourceCost.setText(String.valueOf(cource1.getCost()));
            lblCourceId.setText(cource1.getCourceId());
            lblDuration.setText(String.valueOf(cource1.getDuration()));
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @FXML
    void cmbStockOnAction(ActionEvent event) {
        String stock = (String) cmbStock.getValue();
        try {
            Stock stock1 = StockRepo.getStockByStockName(stock);
            lblStockAmount.setText(String.valueOf(stock1.getPrice()));
            lblStockId.setText(stock1.getItemId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void cmbTypeOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtCustomerNameOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddStockOnAction(event);
    }

    @FXML
    void txtSearchTelOnAction(ActionEvent event) {
        String tel = txtSearchTel.getText();
        try {
            Customer customer = CustomerRepo.getCustomerByTel(tel);

            if (customer == null) {
                new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
                return;
            }else {
                txtCustomerId.setText(customer.getCustomerId());
                txtCustomerName.setText(customer.getName());
            }
        } catch (SQLException e) {
           // new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
            throw new RuntimeException(e);
        }
    }

}
enum Type{
    CASH, CARD
}
