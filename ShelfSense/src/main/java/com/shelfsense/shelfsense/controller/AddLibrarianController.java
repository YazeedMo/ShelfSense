package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.dao.implementations.EmployeeDAOImp;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.services.EmployeeService;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.time.LocalDate;

public class AddLibrarianController {

    @FXML
    private Label lblNotifications;

    @FXML
    private ComboBox<Integer> comboBoxId;

    @FXML
    private TextField txtFieldFirstName;

    @FXML
    private TextField txtFieldLastname;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private PasswordField psswrdFldPassword;

    @FXML
    private DatePicker datePickerHireDate;

    @FXML
    private ComboBox<String> comboBoxRole;

    @FXML
    private Button btnAdd;

    private final EmployeeService employeeService = new EmployeeService();

    @FXML
    private void initialize() {

        // Setup comboBoxId
        comboBoxId.setItems(employeeService.getAvailableIds());

        // Setup datePickerHireDate
        datePickerHireDate.setEditable(false);

        // Setup comboBoxRole
        comboBoxRole.getItems().addAll("Librarian", "Manager");

        // Initialize a FieldChangeListener and attach it to each field
        FieldChangeListener fieldChangeListener = new FieldChangeListener();
        comboBoxId.valueProperty().addListener(fieldChangeListener);
        txtFieldFirstName.textProperty().addListener(fieldChangeListener);
        txtFieldLastname.textProperty().addListener(fieldChangeListener);
        txtFieldUsername.textProperty().addListener(fieldChangeListener);
        psswrdFldPassword.textProperty().addListener(fieldChangeListener);
        datePickerHireDate.valueProperty().addListener(fieldChangeListener);
        comboBoxRole.valueProperty().addListener(fieldChangeListener);
    }

    @FXML
    void btnAddClicked(ActionEvent event) {

        EmployeeDAOImp employeeDAOImp = new EmployeeDAOImp();

        // Get the values in each field
        int id = comboBoxId.getValue();
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastname.getText();
        String username = txtFieldUsername.getText();
        String password = psswrdFldPassword.getText();
        LocalDate hireDate = datePickerHireDate.getValue();
        String role = comboBoxRole.getValue();

        // Create Employee object using details from each field
        Employee employee = new Employee(id, firstName, lastName, username, password, "Employee", hireDate, role);

        // Insert new Employee into the Database
        try {
            employeeDAOImp.insert(employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Close the window
        JavaFXUtils.getCurrentStage(btnAdd).close();

    }

    @FXML
    void btnCancelClicked(ActionEvent event) {

        JavaFXUtils.getCurrentStage(btnAdd).close();

    }

    // Handles the logic when user interacts with the fields
    private class FieldChangeListener implements ChangeListener<Object> {

        @Override
        public void changed(ObservableValue<?> observableValue, Object s, Object t1) {
            updateAddButtonState();
        }

        boolean validDetails = false;

        // Updates button state according to validity of details given in the fields
        private void updateAddButtonState() {

            boolean allFieldsFilled = areAllFieldsFilled();
            boolean validPassword = isPasswordValid();

            // Update label to notify users of any errors
            if (!allFieldsFilled) {
                lblNotifications.setText("Enter New Librarian Details");
            }
            else if (!validPassword) {
                lblNotifications.setText("Password must be at least " + employeeService.getMinPasswordLength() + " characters");
            }
            else {
                lblNotifications.setText("Good to go!");
                validDetails = true;
            }

            // Only enable add button when all fields are filled in correctly
            btnAdd.setDisable(!validDetails);
        }

        // Checks if all fields have been filled in
        private boolean areAllFieldsFilled() {

            return  comboBoxId.getValue() != null &&
                    !txtFieldFirstName.getText().isEmpty() &&
                    !txtFieldLastname.getText().isEmpty() &&
                    !txtFieldUsername.getText().isEmpty() &&
                    !psswrdFldPassword.getText().isEmpty() &&
                    datePickerHireDate.getValue() != null &&
                    comboBoxRole.getValue() != null;

        }

        // Checks if password meets complexity criteria
        private boolean isPasswordValid() {
            int numPasswordCharacters = psswrdFldPassword.getText().length();
            return numPasswordCharacters >= employeeService.getMinPasswordLength();
        }
    }
}
