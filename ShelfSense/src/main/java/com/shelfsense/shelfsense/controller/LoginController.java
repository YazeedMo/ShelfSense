package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.dao.implementations.EmployeeDAOImp;
import com.shelfsense.shelfsense.dao.implementations.UserDAOImp;
import com.shelfsense.shelfsense.dao.interfaces.UserDAO;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.User;
import com.shelfsense.shelfsense.services.LoginService;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {

    @FXML
    private Label lblNotify;

    @FXML
    private TextField txtFieldUsername;

    @FXML
    private PasswordField psswrdFldPassword;

    @FXML
    private Button btnLogin;

    private final LoginService loginService = new LoginService();
    private final UserDAO userDAO = new UserDAOImp();

    @FXML
    void btnLoginClicked(ActionEvent event) throws SQLException {

        // Get username and password
        String username = txtFieldUsername.getText();
        String password = psswrdFldPassword.getText();

        if (validInput(username, password)) {
            boolean validCredentials = loginService.isValidCredentials(username, password);
            if (validCredentials) {
                resetScene();
                User user = userDAO.getWithUsername(username);
                loginService.showRelevantMenu(user, btnLogin);
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
            psswrdFldPassword.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }

    // Resets scene (so credentials are not shown when user returns to log in scene
    // during the same session)
    private void resetScene() {

        lblNotify.setText("Enter credentials");
        txtFieldUsername.clear();
        psswrdFldPassword.clear();
        txtFieldUsername.requestFocus();

    }


}
