package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.dao.implementations.EmployeeDAOImp;
import com.shelfsense.shelfsense.dao.interfaces.EmployeeDAO;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.services.EmployeeService;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageEmployeesController {

    @FXML
    private TableView<Employee> tblViewLibrarians;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    private Employee selectedEmployee;

    private final EmployeeService employeeService = new EmployeeService();

    @FXML
    private void initialize() {

        updateTblViewLibrarians();

        updateButtons();

    }

    @FXML
    void btnBackClicked(ActionEvent event) {

        JavaFXUtils.showPreviousScene(tblViewLibrarians);

    }

    @FXML
    void btnAddClicked(ActionEvent event) {

        employeeService.showAddEmployeeScene();

        // Update tblViewLibrarians after new Librarian has been added
        updateTblViewLibrarians();

    }

    @FXML
    void btnEditClicked(ActionEvent event) {

        employeeService.showEditEmployeeScene(selectedEmployee);

        updateTblViewLibrarians();

    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {

        if (selectedEmployee != null) {
            employeeService.deleteEmployee(selectedEmployee);
            updateTblViewLibrarians();
        }
    }

    private void updateTblViewLibrarians() {

        List<Employee> employeeList = new ArrayList<>();

        // Clear initial Table View (any default columns created by Scene Builder)
        tblViewLibrarians.getColumns().clear();

        // Add our own custom columns (for each attribute of the User object)
        TableColumn<Employee, Integer> userIdColumn = new TableColumn<>("User ID");
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        TableColumn<Employee, String> firstNameColumn = new TableColumn<>("First Name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<Employee, String> lastNameColumn = new TableColumn<>("Last Name");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<Employee, String> usernameColumn = new TableColumn<>("Username");
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn<Employee, String> hireDateColumn = new TableColumn<>("Hire Date");
        hireDateColumn.setCellValueFactory(new PropertyValueFactory<>("hireDate"));

        TableColumn<Employee, String> positionColumn = new TableColumn<>("Position");
        positionColumn.setCellValueFactory(new PropertyValueFactory<>("position"));

        // Add all columns to the Table View
        tblViewLibrarians.getColumns().add(userIdColumn);
        tblViewLibrarians.getColumns().add(firstNameColumn);
        tblViewLibrarians.getColumns().add(lastNameColumn);
        tblViewLibrarians.getColumns().add(usernameColumn);
        tblViewLibrarians.getColumns().add(hireDateColumn);
        tblViewLibrarians.getColumns().add(positionColumn);

        // Adjust column widths
        tblViewLibrarians.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Get a list of all existing Employees
        try {
            employeeList = new EmployeeDAOImp().getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert employeeList into an ObservableList
        ObservableList<Employee> employeeObservableList = FXCollections.observableList(employeeList);

        tblViewLibrarians.setItems(employeeObservableList);

    }

    private void updateButtons() {

        tblViewLibrarians.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {

            if (newValue != null) {
                // An Employee is selected
                selectedEmployee = newValue;

                // Make sure last admin cannot be deleted
                if (selectedEmployee.getPosition().equalsIgnoreCase("Manager")) {
                    btnDelete.setDisable(!employeeService.canDeleteAdmin());
                }
                else {
                    btnDelete.setDisable(false);
                }

                btnEdit.setDisable(false);
            }
            else {
                // No Employee is selected
                btnEdit.setDisable(true);
                btnDelete.setDisable(true);
            }

        });
    }
}
