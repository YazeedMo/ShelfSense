package com.shelfsense.shelfsense;

import com.shelfsense.shelfsense.dao.UserDAOImp;
import com.shelfsense.shelfsense.services.LoginService;

import java.sql.SQLException;

public class Test {

    public static void main(String[] args) {

        LoginService loginService = new LoginService();

        String username = null;
        String password = "J.Doe";

        System.out.println(loginService.isValidCredentials(username, password));

    }

}
