package lk.ijse.oceansync.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.oceansync.controller.repository.EmployeeRepo;
import lk.ijse.oceansync.controller.repository.UserRepo;
import lk.ijse.oceansync.model.Employee;
import lk.ijse.oceansync.model.User;
import lk.ijse.oceansync.model.tm.EmployeeTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFormController {

    public TextField txtMonth;
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
    private TableView<EmployeeTm> tblEmployee;

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

    private List<Employee> employeeIdList = new ArrayList<>();

    public void initialize() {
        this.employeeIdList = getAllEmployeeId();
        loadNextId();
        getUserId();
        setCellValue();
        loadEmployeeTable();
    }

    private List<Employee> getAllEmployeeId() {
        List<Employee> employeeList = null;
        try {
            employeeList = EmployeeRepo.getAll();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return employeeList;
    }


    private void setCellValue() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colActivity.setCellValueFactory(new PropertyValueFactory<>("activity"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
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
    private void loadEmployeeTable() {
        ObservableList<EmployeeTm> employees = FXCollections.observableArrayList();
        for (Employee employee : employeeIdList) {
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getId(),
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getActivity(),
                    employee.getSalary(),
                    employee.getDate(),
                    employee.getUserId()
            );
            employees.add(employeeTm);
        }
        tblEmployee.setItems(employees);
        EmployeeTm selectedEmployee = tblEmployee.getSelectionModel().getSelectedItem();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtActivity.setText("");
        txtDate.setText("");
        txtEmployeeId.setText("");
        txtName.setText("");
        txtSalary.setText("");
    }

    @FXML
    void btnDeleteOnActin(ActionEvent event) {
    String employeeId = txtEmployeeId.getText();
        try {
            EmployeeRepo.employeeDelete(employeeId);
            if (employeeId != null) {
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
        String id = lblId.getText();
        String userId = cmbUserId.getValue();
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String activity = txtActivity.getText();
        String month = txtMonth.getText();
        String salary = txtSalary.getText();
        String date = txtDate.getText();
        Employee employee = new Employee(id,employeeId, name, activity, month ,salary, date, userId);
        try {
            boolean isSaved = EmployeeRepo.employeeSave(employee);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

        String id = lblId.getText();
        String userId = cmbUserId.getValue();
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String activity = txtActivity.getText();
        String month = txtMonth.getText();
        String salary = txtSalary.getText();
        String date = txtDate.getText();
        Employee employee = new Employee(id,employeeId, name, activity, month ,salary, date, userId);
        try {
            boolean isUpdated = EmployeeRepo.employeeUpdate(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Employee updated!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbUserIdOnAction(ActionEvent event) {
        String userId = cmbUserId.getValue();


        try {
            User user = UserRepo.userSearchById(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        // lblUsername.setText(user.getUserName());

        ;
    }
    private String nextId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("O");
//            System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
            int id = Integer.parseInt(split[1]);    //2
            return "O" + ++id;

        }
        return "O1";
    }

    private void loadNextId() {
        try {
            String currentId = EmployeeRepo.currentId();
            String nextId = nextId(currentId);

            lblId.setText(nextId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
