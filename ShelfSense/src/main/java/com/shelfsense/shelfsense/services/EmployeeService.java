package com.shelfsense.shelfsense.services;

import com.shelfsense.shelfsense.controller.AddLibrarianController;
import com.shelfsense.shelfsense.dao.implementations.EmployeeDAOImp;
import com.shelfsense.shelfsense.dao.implementations.UserDAOImp;
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

    // Returns an ObservableList of ID numbers that have not yet been used
    public ObservableList<Integer> getAvailableIds() {

        // Fetch used IDs from the database
        Set<Integer> usedIds = new UserDAOImp().getUsedIds();

        // Determine available IDs
        ObservableList<Integer> availableIds = FXCollections.observableArrayList();
        for (int i = 1; i <= 100; i++) {
            if (!usedIds.contains(i)) {
                availableIds.add(i);
            }
        }

        return availableIds;

    }

    public int getMinPasswordLength() {
        return MIN_PASSWORD_LENGTH;
    }

    public EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();

    public void showAddLibrarianScene() {

        // Show AddLibrarian window
        Stage addLibrarianStage = new Stage();
        addLibrarianStage.initModality(Modality.APPLICATION_MODAL);

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(JavaFXUtils.FXMLPaths.ADD_LIBRARIAN.getPath())));
            Scene scene = new Scene(root);
            addLibrarianStage.setScene(scene);
            addLibrarianStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addLibrarian(Employee librarian) {

        try {
            employeeDAOImp.insert(librarian);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void showEditLibrarianScene(Employee employee) {

        AddLibrarianController.setSelectedEmployee(employee);

        showAddLibrarianScene();

    }

    public void editLibrarian(Employee employee) {

        try {
            employeeDAOImp.update(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteEmployee(Employee employee) {

        if (confirmDeletion(employee)) {
            try {
                employeeDAOImp.delete(employee);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
