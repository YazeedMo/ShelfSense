package com.shelfsense.shelfsense.services;

import com.shelfsense.shelfsense.dao.implementations.EmployeeDAOImp;
import com.shelfsense.shelfsense.dao.implementations.UserDAOImp;
import com.shelfsense.shelfsense.dao.interfaces.EmployeeDAO;
import com.shelfsense.shelfsense.dao.interfaces.UserDAO;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.User;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import com.shelfsense.shelfsense.util.Session;
import javafx.scene.Node;

import java.sql.SQLException;

// Service class to validate user credentials when logging in
public class LoginService {

    private final UserDAO userDAO = new UserDAOImp();
    private final EmployeeDAO employeeDAO = new EmployeeDAOImp();

    public boolean isValidCredentials(String username, String password) {

        boolean validCredentials = false;

        // Check if username and password matches and is correct
        try {
            if (userDAO.usernameExists(username)) {
                if (userDAO.getUserPassword(username).equals(password)) {
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
            User currentUser = userDAO.getWithUsername(username);
            Session.getInstance().setCurrentUser(currentUser);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void showRelevantMenu(User user, Node node) {

        String userType;

        try {
            userType = userDAO.getTypeWithId(user.getUserId());
            if (userType.equalsIgnoreCase("Customer")) {
                JavaFXUtils.showNextScene(node, JavaFXUtils.FXMLPaths.CUSTOMER_MAIN_MENU.getPath());
            }
            else {
                Employee employee = (Employee) user;
                if (employeeDAO.getPosition(employee).equalsIgnoreCase("Manager")) {
                    JavaFXUtils.showNextScene(node, JavaFXUtils.FXMLPaths.MANAGER_MAIN_MENU.getPath());
                }
                else {
                    JavaFXUtils.showNextScene(node, JavaFXUtils.FXMLPaths.LIBRARIAN_MAIN_MENU.getPath());
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
