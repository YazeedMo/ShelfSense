package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.dao.implementations.UserDAOImp;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.User;
import com.shelfsense.shelfsense.services.LoginService;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblNotify;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private TextField txtFieldPassword;

    @FXML
    void btnLoginClicked(ActionEvent event) throws SQLException {

        // Get username and password
        String username = txtFieldUsername.getText();
        String password = txtFieldPassword.getText();

        if (validInput(username, password)) {
            boolean validCredentials = new LoginService().isValidCredentials(username, password);
            if (validCredentials) {
                resetScene();
                User user = new UserDAOImp().getWithUsername(username);
                showRelevantMenu(user);
            }
            else {
                lblNotify.setText("Invalid Username or password");
            }
        }
    }

    private boolean validInput(String username, String password) {

        if (username.isEmpty()) {
            lblNotify.setText("Please enter username");
            txtFieldUsername.requestFocus();
            return false;
        } else if (password.isEmpty()) {
            lblNotify.setText("Please enter password");
            txtFieldPassword.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }

    private void showRelevantMenu(User user) {

        if (user.getType().equals("Employee")) {
            Employee employee = (Employee) user;
            if (employee.getRole().equals("Manager")) {
                JavaFXUtils.showNextScene(btnLogin, JavaFXUtils.FXMLPaths.MANAGER_MAIN_MENU.getPath());
            }
            else if (employee.getRole().equals("Librarian")) {
                JavaFXUtils.showNextScene(btnLogin, JavaFXUtils.FXMLPaths.LIBRARIAN_MAIN_MENU.getPath());
            }
        }
        else if (user.getType().equals("Customer")) {
            JavaFXUtils.showNextScene(btnLogin, JavaFXUtils.FXMLPaths.CUSTOMER_MAIN_MENU.getPath());
        }

    }

    // Resets scene (so credentials are not shown when user returns to log in scene
    // during the same session)
    private void resetScene() {

        lblNotify.setText("Enter credentials");
        txtFieldUsername.clear();
        txtFieldPassword.clear();
        txtFieldUsername.requestFocus();

    }


}
