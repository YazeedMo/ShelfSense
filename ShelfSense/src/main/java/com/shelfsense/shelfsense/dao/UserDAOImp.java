package com.shelfsense.shelfsense.dao;

import com.shelfsense.shelfsense.model.Customer;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.User;

import java.sql.*;
import java.util.List;

public class UserDAOImp implements UserDAO {

    public int getIdWithUsername(String username) throws SQLException {

        String query = "SELECT UserId FROM Users WHERE Username = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getInt("userId");

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

    private User getCustomer(int userId) throws SQLException {

        User user = null;

        String query = "SELECT * FROM Users " +
                "JOIN Customers ON Users.UserId = Customers.CustomerId " +
                "WHERE Users.UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId1 = rs.getInt("UserId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                Date joinDate = rs.getDate("JoinDate");
                Date expirydate = rs.getDate("ExpiryDate");

                user = new Customer(userId1, firstName, lastName, username, password, joinDate, expirydate);

            }
        }
        return user;
    }

    private User getEmployee(int userId) throws SQLException {

        User user = null;

        String query = "SELECT * FROM Users " +
                "JOIN Employees ON Users.UserId = Employees.EmployeeId " +
                "WHERE Users.UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId1 = rs.getInt("UserId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                Date joinDate = rs.getDate("HireDate");
                String role = rs.getString("Role");

                user = new Employee(userId1, firstName, lastName, username, password, joinDate, role);

            }
        }
        return user;
    }

    @Override
    public User getWithId(int id) throws SQLException {

        User user = null;

        String userType = getTypeWithId(id);

        if (userType.equalsIgnoreCase("Customer")) {
            user = getCustomer(id);
        }
        else {
            user = getEmployee(id);
        }
        return user;
    }

    @Override
    public List<User> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(User person) throws SQLException {
        return 0;
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

    @Override
    public boolean usernameExists(String username) throws SQLException {

        String query = "SELECT * FROM Users WHERE username = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, username);
            // If a record is found, this line will return true, if rs is empty, it will return false
            ResultSet rs = ps.executeQuery();

            return rs.next();
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

}