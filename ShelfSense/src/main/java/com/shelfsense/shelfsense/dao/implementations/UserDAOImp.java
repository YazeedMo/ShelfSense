package com.shelfsense.shelfsense.dao.implementations;

import com.shelfsense.shelfsense.dao.Database;
import com.shelfsense.shelfsense.dao.interfaces.UserDAO;
import com.shelfsense.shelfsense.model.Customer;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImp implements UserDAO {

    // region Private Methods

    private int getIdWithUsername(String username) throws SQLException {

        String query = "SELECT UserId FROM Users WHERE Username = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getInt("UserId");

        }
    }

    private String getTypeWithId(int userId) throws SQLException {

        String query = "SELECT Type FROM Users WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getString("Type");

        }

    }

    // endregion

    // region GenericDAO Methods

    @Override
    public List<User> getAll() throws SQLException {

        List<User> allUsers = new ArrayList<>();

        // Get List of all Employees and Customers
        List<Employee> allEmployees = new EmployeeDAOImp().getAll();
        List<Customer> allCustomers = new CustomerDAOImp().getAll();

        // Combine both lists into allUser
        allUsers.addAll(allEmployees);
        allUsers.addAll(allCustomers);

        return allUsers;
    }

    @Override
    public User getWithId(int id) throws SQLException {

        User user;

        String userType = getTypeWithId(id);

        if (userType.equalsIgnoreCase("Customer")) {
            user = new CustomerDAOImp().getWithId(id);
        }
        else {
            user = new EmployeeDAOImp().getWithId(id);
        }
        return user;
    }

    @Override
    public int insert(User person) throws SQLException {
        return 0;
    }

    @Override
    public int update(User person) throws SQLException {
        return 0;
    }

    @Override
    public int delete(User person) throws SQLException {
        return 0;
    }

    // endregion

    // region UserDAOImp methods

    @Override
    public boolean usernameExists(String username) throws SQLException {

        boolean usernameExists = false;

        String query = "SELECT * FROM Users WHERE username = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            // Get actual username from database instead of checking if a record was returned
            // Database returns username regardless of casing (Database querying is case-insensitive)
            // Get actual username from database to ensure case sensitivity when user logs in
            if (rs.next()) {
                String dbUsername = rs.getString("Username");
                if (username.equals(dbUsername)) {
                    usernameExists = true;
                }
            }
            return usernameExists;
        }
    }

    @Override
    public String getUserPassword(String username) throws SQLException {

        String query = "SELECT Password FROM Users WHERE username = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            rs.next();

            return rs.getString("Password");

        }

    }

    @Override
    public User getWithUsername(String username) throws SQLException {

        int userId = getIdWithUsername(username);
        return getWithId(userId);

    }

    // endregion

}
