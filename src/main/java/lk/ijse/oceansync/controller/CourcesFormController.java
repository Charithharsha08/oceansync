package lk.ijse.oceansync.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import lk.ijse.oceansync.model.Cource;
import lk.ijse.oceansync.controller.repository.CourceRepo;

import java.sql.SQLException;

public class CourcesFormController {

    @FXML
    private TextField txtCost;

    @FXML
    private TextField txtCourceId;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtName;

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtCourceId.setText("");
        txtName.setText("");
        txtCost.setText("");
        txtDuration.setText("");
    }

    @FXML
    void btnDeleteOnActin(ActionEvent event) {
        String courceId = txtCourceId.getText();
        CourceRepo.courceDelete(courceId);
        if (courceId != null) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            clearFields();
        }else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String courceId = txtCourceId.getText();
        String  name = txtName.getText();
        String duration = txtDuration.getText();
        double cost = Double.parseDouble(txtCost.getText());

        Cource cource = new Cource(courceId,name,duration,cost);
        boolean save = false;
        try {
            save = CourceRepo.courceSave(cource);
            if (save) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            throw new RuntimeException(e);

        }

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String courceId = txtCourceId.getText();
        String  name = txtName.getText();
        String  duration= txtDuration.getText();
        double cost = Double.parseDouble(txtCost.getText());
        Cource cource = new Cource(courceId,name,duration,cost);
        boolean save = false;
        try {
            save = CourceRepo.courceUpdate(cource);
            if (save) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated").show();
                clearFields();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            throw new RuntimeException(e);
        }
    }

}
