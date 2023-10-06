package com.shelfsense.shelfsense.services;

import com.shelfsense.shelfsense.controller.AddEmployeeController;
import com.shelfsense.shelfsense.dao.implementations.EmployeeDAOImp;
import com.shelfsense.shelfsense.dao.implementations.UserDAOImp;
import com.shelfsense.shelfsense.dao.interfaces.EmployeeDAO;
import com.shelfsense.shelfsense.dao.interfaces.UserDAO;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Set;

public class EmployeeService {

    private static final int MIN_PASSWORD_LENGTH = 5;

    private final UserDAO userDAO = new UserDAOImp();
    private final EmployeeDAO employeeDAO = new EmployeeDAOImp();

    // Returns an ObservableList of ID numbers that have not yet been used
    public ObservableList<Integer> getAvailableIds() {

        // Fetch used IDs from the database
        Set<Integer> usedIds = null;
        try {
            usedIds = userDAO.getUsedIds();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Determine available IDs
        ObservableList<Integer> availableIds = FXCollections.observableArrayList();
        for (int i = 1; i <= 100; i++) {
            if (usedIds != null && !usedIds.contains(i)) {
                availableIds.add(i);
            }
        }
        return availableIds;
    }

    public int getMinPasswordLength() {
        return MIN_PASSWORD_LENGTH;
    }

    public void showAddEmployeeScene() {

        // Show AddLibrarian window
        Stage addLibrarianStage = new Stage();
        addLibrarianStage.initModality(Modality.APPLICATION_MODAL);

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(JavaFXUtils.FXMLPaths.ADD_EMPLOYEE.getPath())));
            Scene scene = new Scene(root);
            addLibrarianStage.setScene(scene);
            addLibrarianStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(Employee employee) {

        try {
            employeeDAO.insert(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void showEditEmployeeScene(Employee employee) {

        AddEmployeeController.setSelectedEmployee(employee);

        showAddEmployeeScene();

    }

    public void editEmployee(Employee employee) {

        try {
            employeeDAO.update(employee);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteEmployee(Employee employee) {

        if (confirmDeletion(employee)) {
            try {
                employeeDAO.delete(employee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean canDeleteAdmin() {
        try {
            return employeeDAO.getManagerCount() > 1;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Show an alert box to confirm Employee deletion
    private boolean confirmDeletion(Employee employee) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete the following employee?");
        alert.setContentText("Employee ID: " + employee.getUserId() + "\nFirst Name: " + employee.getFirstName());

        alert.showAndWait();

        return alert.getResult() == ButtonType.OK;

    }

}
