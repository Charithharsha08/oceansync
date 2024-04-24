package lk.ijse.oceansync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.oceansync.model.Activity;
import lk.ijse.oceansync.controller.repository.ActivityRepo;

import java.sql.SQLException;

public class ActivityFormController {

    @FXML
    private Label lblUserName;

    @FXML
    private TextField txtActivityId;

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtType;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtActivityId.setText("");
        txtCost.setText("");
        txtLocation.setText("");
        txtName.setText("");
        txtType.setText("");

    }

    @FXML
    void btnDeleteOnActin(ActionEvent event) {
        String activityId = txtSearch.getText();
        try {
            ActivityRepo.activityDelete(activityId);
            if (activityId != null) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String activityId = txtActivityId.getText();
        String name = txtName.getText();
        String type = txtType.getText();
        String location = txtLocation.getText();
        double cost = Double.parseDouble(txtCost.getText());
        Activity activity = new Activity(activityId,name,type,location,cost);
        if (name.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter name").show();
            return;
        }if (type.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter type").show();
            return;
        }if (location.isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Please enter location").show();
            return;
        }if (cost == 0){
            new Alert(Alert.AlertType.ERROR, "Please enter cost").show();
            return;
        }
        try {
           boolean isSaved = ActivityRepo.activitySave(activity);

        if(isSaved){
            new Alert(Alert.AlertType.CONFIRMATION, "Activity saved!").show();
            clearFields();
        }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
    String activityId = txtSearch.getText();

    try {
        Activity activity = ActivityRepo.activitySearchById(activityId);
        if (activity != null) {
            txtActivityId.setText(activity.getActivityId());
            txtName.setText(activity.getName());
            txtType.setText(activity.getType());
            txtLocation.setText(activity.getLocation());
            txtCost.setText(activity.getCost() + "");
        }
    } catch (SQLException e) {
        new Alert(Alert.AlertType.ERROR, "Activity not found!").show();
        throw new RuntimeException(e);
    }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String activityId = txtActivityId.getText();
        String name = txtName.getText();
        String type = txtType.getText();
        String location = txtLocation.getText();
        double cost = Double.parseDouble(txtCost.getText());
        Activity activity = new Activity(activityId,name,type,location,cost);
        try {
            boolean isUpdated = ActivityRepo.activityUpdate(activity);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            throw new RuntimeException(e);
        }
    }

}
