package com.shelfsense.shelfsense.services;

import com.shelfsense.shelfsense.dao.UserDAOImp;
import com.shelfsense.shelfsense.model.User;
import com.shelfsense.shelfsense.util.Session;

import java.sql.SQLException;

public class LoginService {

    public boolean isValidCredentials(String username, String password) {

        boolean validCredentials = false;

        UserDAOImp udi = new UserDAOImp();

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

    private void setCurrentUser(String username) {

        UserDAOImp udi = new UserDAOImp();

        try {

            User currentUser = udi.getWithUsername(username);
            Session.getInstance().setCurrentUser(currentUser);

            System.out.println(Session.getInstance().getCurrentUser());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }

}
