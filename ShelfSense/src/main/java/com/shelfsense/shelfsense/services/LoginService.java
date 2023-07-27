package com.shelfsense.shelfsense.services;

import com.shelfsense.shelfsense.dao.UserDAOImp;
import com.shelfsense.shelfsense.model.User;
import com.shelfsense.shelfsense.util.Session;

import java.sql.SQLException;

// Service class to validate user credentials when logging in
public class LoginService {

    private UserDAOImp udi = new UserDAOImp();

    public boolean isValidCredentials(String username, String password) {

        boolean validCredentials = false;

        // Check if username and password matches and is correct
        try {
            if (udi.usernameExists(username)) {
                if (udi.getUserPassword(username).equals(password)) {
                    validCredentials = true;
                    setCurrentUser(username);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return validCredentials;

    }

    // Stores current user in the Singleton Session class
    private void setCurrentUser(String username) {

        try {

            User currentUser = udi.getWithUsername(username);
            Session.getInstance().setCurrentUser(currentUser);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
