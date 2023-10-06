package com.shelfsense.shelfsense.dao.implementations;

import com.shelfsense.shelfsense.dao.Database;
import com.shelfsense.shelfsense.dao.interfaces.UserDAO;
import com.shelfsense.shelfsense.model.Customer;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDAOImp implements UserDAO {


    // region GenericDAO Methods

    @Override
    public List<User> getAll() throws SQLException {

        List<User> allUsers = new ArrayList<>();

        // Get List of all Employees and Customers
        List<Employee> allEmployees = new EmployeeDAOImp().getAll();
        List<Customer> allCustomers = new CustomerDAOImp().getAll();

        // Combine both lists into allUsers
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

        String userType = getTypeWithId(person.getUserId());

        if (userType.equalsIgnoreCase("Customer")) {
            return new CustomerDAOImp().insert((Customer) person);
        }
        else {
            return new EmployeeDAOImp().insert((Employee) person);
        }
    }

    @Override
    public int update(User person) throws SQLException {

        String userType = getTypeWithId(person.getUserId());

        if (userType.equalsIgnoreCase("Customer")) {
            return new CustomerDAOImp().update((Customer) person);
        }
        else {
            return new EmployeeDAOImp().update((Employee) person);
        }
    }

    @Override
    public int delete(User person) throws SQLException {

        // SQL to update Users table
        // Cascade delete in database so only need to specify root table delete
        String usersSQL = "DELETE FROM Users WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(usersSQL)) {

            ps.setInt(1, person.getUserId());

            return ps.executeUpdate();

        }
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

    @Override
    public String getTypeWithId(int userId) throws SQLException {

        String query = "SELECT UserType FROM UserRoles " +
                "WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getString("UserType");
        }
    }

    // Returns all ID numbers that have already been assigned to employees
    @Override
    public Set<Integer> getUsedIds() throws SQLException {

        // Set used so that the checking of whether an ID is used is efficient
        Set<Integer> usedIds = new HashSet<>();

        String query = "SELECT UserId FROM Users";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                usedIds.add(rs.getInt("UserId"));
            }
        }
        return usedIds;
    }

    // endregion


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

    // endregion

}
