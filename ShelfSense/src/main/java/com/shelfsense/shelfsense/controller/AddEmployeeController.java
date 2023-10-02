package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.Librarian;
import com.shelfsense.shelfsense.model.Manager;
import com.shelfsense.shelfsense.services.EmployeeService;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class AddEmployeeController {

    @FXML
    public Label lblNotifications;

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
    private ComboBox<String> comboBoxPosition;

    @FXML
    private Button btnAdd;

    private final EmployeeService employeeService = new EmployeeService();

    private static Employee selectedEmployee;

    @FXML
    private void initialize() {

        // Setup comboBoxId
        comboBoxId.setItems(employeeService.getAvailableIds());

        // Setup datePickerHireDate
        datePickerHireDate.setEditable(false);

        // Setup comboBoxRole
        comboBoxPosition.getItems().addAll("Librarian", "Manager");

        // Initialize a FieldChangeListener and attach it to each field
        FieldChangeListener fieldChangeListener = new FieldChangeListener();
        comboBoxId.valueProperty().addListener(fieldChangeListener);
        txtFieldFirstName.textProperty().addListener(fieldChangeListener);
        txtFieldLastname.textProperty().addListener(fieldChangeListener);
        txtFieldUsername.textProperty().addListener(fieldChangeListener);
        psswrdFldPassword.textProperty().addListener(fieldChangeListener);
        datePickerHireDate.valueProperty().addListener(fieldChangeListener);
        comboBoxPosition.valueProperty().addListener(fieldChangeListener);

        // This will run if user chose "Edit" in ManageLibrarians window
        if (selectedEmployee != null) {
            comboBoxId.setDisable(true);
            comboBoxId.setValue(selectedEmployee.getUserId());
            txtFieldFirstName.setText(selectedEmployee.getFirstName());
            txtFieldLastname.setText(selectedEmployee.getLastName());
            txtFieldUsername.setText(selectedEmployee.getUsername());
            psswrdFldPassword.setText(selectedEmployee.getPassword());
            datePickerHireDate.setValue(selectedEmployee.getHireDate());
            comboBoxPosition.setValue(selectedEmployee.getPosition());
            btnAdd.setText("Submit");
        }

    }

    @FXML
    void btnAddClicked(ActionEvent event) {

        Employee employee = createEmployee();

        if (btnAdd.getText().equals("Add")) {
            employeeService.addEmployee(employee);
        }
        else {
            employeeService.editEmployee(employee);
        }

        selectedEmployee = null;

        // Close the window
        JavaFXUtils.getCurrentStage(btnAdd).close();

    }

    @FXML
    void btnCancelClicked(ActionEvent event) {

        JavaFXUtils.getCurrentStage(btnAdd).close();

    }

    public static void setSelectedEmployee(Employee employee) {
        selectedEmployee = employee;
    }

    // Handles the logic when user interacts with the fields
    private class FieldChangeListener implements ChangeListener<Object> {

        @Override
        public void changed(ObservableValue<?> observableValue, Object s, Object t1) {
            updateAddButtonState();
        }

        // Updates button state according to validity of details given in the fields
        private void updateAddButtonState() {

            boolean validDetails = false;

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
                    comboBoxPosition.getValue() != null;

        }

        // Checks if password meets complexity criteria
        private boolean isPasswordValid() {
            int numPasswordCharacters = psswrdFldPassword.getText().length();
            return numPasswordCharacters >= employeeService.getMinPasswordLength();
        }
    }

    private Employee createEmployee() {

        // Get the values in each field
        int id = comboBoxId.getValue();
        String firstName = txtFieldFirstName.getText();
        String lastName = txtFieldLastname.getText();
        String username = txtFieldUsername.getText();
        String password = psswrdFldPassword.getText();
        LocalDate hireDate = datePickerHireDate.getValue();
        String position = comboBoxPosition.getValue();

        if (position.equalsIgnoreCase("Manager")) {
            return new Manager(id, firstName, lastName, username, password, hireDate, position);
        }
        else {
            return new Librarian(id, firstName, lastName, username, password, hireDate, position);
        }
    }
}
