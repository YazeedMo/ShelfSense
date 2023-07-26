package com.shelfsense.shelfsense;

import com.shelfsense.shelfsense.dao.UserDAOImp;
import com.shelfsense.shelfsense.services.LoginService;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {

        LoginService loginService = new LoginService();

        String username = "John";
        String password = "J.Doe";

        loginService.isValidCredentials(username, password);

    }

}
