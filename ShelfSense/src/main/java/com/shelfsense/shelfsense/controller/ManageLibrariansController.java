package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.dao.implementations.EmployeeDAOImp;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.User;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageLibrariansController {

    @FXML
    private TableView<Employee> tblViewLibrarians;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;



    @FXML
    private void initialize() {

        List<Employee> employeeList = new ArrayList<>();

        // Clear initial Table View (any default columns created by Scene Builder)
        tblViewLibrarians.getColumns().clear();

        // Add out own custom columns (for each attribute of the User object)
        TableColumn<Employee, String> userIdColumn = new TableColumn<>("User ID");
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

    @FXML
    void btnBackClicked(ActionEvent event) {

        JavaFXUtils.showPreviousScene(tblViewLibrarians);

    }

    @FXML
    void btnAddClicked(ActionEvent event) {

    }

    @FXML
    void btnEditClicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {

    }

}
