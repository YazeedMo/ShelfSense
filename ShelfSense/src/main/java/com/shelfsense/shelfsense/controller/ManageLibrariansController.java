package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.dao.implementations.EmployeeDAOImp;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ManageLibrariansController {

    @FXML
    private TableView<Employee> tblViewLibrarians;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    private Employee selectedEmployee;

    private final EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();

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

        // Show AddLibrarian window
        Stage addLibrarianStage = new Stage();
        addLibrarianStage.initModality(Modality.APPLICATION_MODAL);

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(JavaFXUtils.FXMLPaths.ADD_LIBRARIAN.getPath())));
            Scene scene = new Scene(root);
            addLibrarianStage.setScene(scene);
            addLibrarianStage.showAndWait();

            // Update tblViewLibrarians after new Librarian has been added
            updateTblViewLibrarians();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void btnEditClicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {

        if (selectedEmployee != null) {
            if (confirmDeletion(selectedEmployee)) {

                try {
                    employeeDAOImp.delete(selectedEmployee);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                updateTblViewLibrarians();
            }
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

        TableColumn<Employee, String> roleColumn = new TableColumn<>("Role");
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        // Add all columns to the Table View
        tblViewLibrarians.getColumns().add(userIdColumn);
        tblViewLibrarians.getColumns().add(firstNameColumn);
        tblViewLibrarians.getColumns().add(lastNameColumn);
        tblViewLibrarians.getColumns().add(usernameColumn);
        tblViewLibrarians.getColumns().add(hireDateColumn);
        tblViewLibrarians.getColumns().add(roleColumn);

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
                // A librarian is selected
                selectedEmployee = newValue;
                btnEdit.setDisable(false);
                btnDelete.setDisable(false);
            }
            else {
                // No librarian is selected
                btnEdit.setDisable(true);
                btnDelete.setDisable(true);
            }

        });

    }

    // Show an alert box to confirm User deletion
    private boolean confirmDeletion(Employee employee) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete the following employee?");
        alert.setContentText("Employee ID: " + employee.getUserId() + "\nFirst Name: " + employee.getFirstName());

        alert.showAndWait();

        return alert.getResult() == ButtonType.OK;

    }

}
