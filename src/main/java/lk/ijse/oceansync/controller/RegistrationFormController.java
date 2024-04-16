package lk.ijse.oceansync.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.oceansync.db.DbConnection;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationFormController {

    public AnchorPane rootNode;
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPw;

    @FXML
    private TextField txtUserId;

    @FXML
    private void btnRegisterOnAction(ActionEvent event) {
        String userId = txtUserId.getText();
        String name = txtName.getText();
        String pw = txtPw.getText();

        saveUser(userId, name, pw);
    }

    private void saveUser(String userId, String name, String pw) {
        try {
            String sql = "INSERT INTO user VALUES(?, ?, ?)";

            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setObject(1, userId);
            pstm.setObject(2, name);
            pstm.setObject(3, pw);

            if (pstm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();

//                AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
//
//                Scene scene = new Scene(rootNode);
//
//                Stage stage = (Stage) this.rootNode.getScene().getWindow();
//                stage.setScene(scene);
//                stage.centerOnScreen();
//                stage.setTitle("Login Form");

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "something happend!").show();

//        } catch (IOException e) {
//            throw new RuntimeException(e);
        }
    }
}

