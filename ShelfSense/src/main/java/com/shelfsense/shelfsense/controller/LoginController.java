package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.services.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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
    void btnLoginClicked(ActionEvent event) {

        // Get username and password
        String username = txtFieldUsername.getText();
        String password = txtFieldPassword.getText();

        if (validInput(username, password)) {
            boolean validCredentials = new LoginService().isValidCredentials(username, password);
            if (validCredentials) {
                System.out.println("Login successful!!!");
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            }
            else {
                lblNotify.setText("Invalid Username or password");
            }
        }

    }

    public boolean validInput(String username, String password) {

        if (txtFieldUsername.getText().isEmpty()) {
            lblNotify.setText("Please enter username");
            txtFieldUsername.requestFocus();
            return false;
        } else if (txtFieldPassword.getText().isEmpty()) {
            lblNotify.setText("Please enter password");
            txtFieldPassword.requestFocus();
            return false;
        }
        else {
            return true;
        }
    }

}