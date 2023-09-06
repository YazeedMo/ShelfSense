package com.shelfsense.shelfsense.dao.implementations;

import com.shelfsense.shelfsense.dao.Database;
import com.shelfsense.shelfsense.dao.interfaces.CustomerDAO;
import com.shelfsense.shelfsense.model.Customer;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO {

    // region GenericDAO Methods

    @Override
    public Customer getWithId(int id) throws SQLException {

        Customer customer = null;

        String query = "SELECT Users.*, Customers.* " +
                "FROM Users " +
                "INNER JOIN Customers On Users.UserId = Customers.CustomerId " +
                "Where Users.UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("CustomerId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String type = rs.getString("Type");
                LocalDate joinDate = rs.getObject("JoinDate", LocalDate.class);
                LocalDate expiryDate = rs.getObject("ExpiryDate", LocalDate.class);

                customer = new Customer(customerId, firstName, lastName, username, password, type, joinDate, expiryDate);

            }
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() throws SQLException {


        List<Customer> allCustomers = new ArrayList<>();

        String query = "SELECT Users.*, Customers.* " +
                "FROM Users " +
                "INNER JOIN Customers ON Users.UserId = Customers.CustomerId";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int customerId = rs.getInt("CustomerId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String type = rs.getString("Type");
                LocalDate joinDate = rs.getObject("JoinDate", LocalDate.class);
                LocalDate expiryDate = rs.getObject("ExpiryDate", LocalDate.class);

                allCustomers.add(new Customer(customerId, firstName, lastName, username, password, type, joinDate, expiryDate));

            }

        }

        return allCustomers;

    }

    @Override
    public int insert(Customer customer) throws SQLException {

        // SQL to insert details into Users table
        String usersSQL = "INSERT INTO Users " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        // SQL to insert details into Customers table
        String customersSQL = "INSERT INTO Customers " +
                "VALUES (?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement psForUsers = connection.prepareStatement(usersSQL);
             PreparedStatement psForCustomers = connection.prepareStatement(customersSQL)) {

            psForUsers.setInt(1, customer.getUserId());
            psForUsers.setString(2, customer.getFirstName());
            psForUsers.setString(3, customer.getLastName());
            psForUsers.setString(4, customer.getUsername());
            psForUsers.setString(5, customer.getPassword());
            psForUsers.setString(6, "Customer");

            psForCustomers.setInt(1, customer.getUserId());
            psForCustomers.setDate(2, java.sql.Date.valueOf(customer.getJoinDate()));
            psForCustomers.setDate(3, java.sql.Date.valueOf(customer.getExpiryDate()));

            int usersResult = psForUsers.executeUpdate();
            int customersResult = psForCustomers.executeUpdate();

            return usersResult + customersResult;

        }
    }

    @Override
    public int update(Customer customer) throws SQLException {

        // SQL to update the Customers table
        String customersSQL = "UPDATE Customers SET " +
                "CustomerId = ?, " +
                "JoinDate = ?, " +
                "ExpiryDate = ? " +
                "WHERE CustomerID = ?;";

        // SQL to update Users table
        String usersSQL = "UPDATE Users SET " +
                "UserId = ?, " +
                "FirstName = ?, " +
                "LastName = ?, " +
                "Username = ?, " +
                "Password = ?, " +
                "Type = ? " +
                "WHERE UserID = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement psForCustomers = connection.prepareStatement(customersSQL);
             PreparedStatement psForUsers = connection.prepareStatement(usersSQL)) {

            psForCustomers.setInt(1, customer.getUserId());
            psForCustomers.setDate(2, java.sql.Date.valueOf(customer.getJoinDate()));
            psForCustomers.setDate(3, java.sql.Date.valueOf(customer.getExpiryDate()));
            psForCustomers.setInt(4, customer.getUserId());

            psForUsers.setInt(1, customer.getUserId());
            psForUsers.setString(2, customer.getFirstName());
            psForUsers.setString(3, customer.getLastName());
            psForUsers.setString(4, customer.getUsername());
            psForUsers.setString(5, customer.getPassword());
            psForUsers.setString(6, "Customer");
            psForUsers.setInt(7, customer.getUserId());

            int customersResult = psForCustomers.executeUpdate();
            int usersResult = psForUsers.executeUpdate();

            return customersResult + usersResult;

        }
    }

    @Override
    public int delete(Customer customer) throws SQLException {


        // SQL to update Customers table
        String customersSQL = "DELETE FROM Customers WHERE CustomerId = ?";

        // SQL to update Users table
        String userSQL = "DELETE FROM Users WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement psForCustomers = connection.prepareStatement(customersSQL);
             PreparedStatement psForUsers = connection.prepareStatement(userSQL)) {

            psForCustomers.setInt(1, customer.getUserId());

            psForUsers.setInt(1, customer.getUserId());

            int customersResult = psForCustomers.executeUpdate();
            int usersResult = psForUsers.executeUpdate();

            return customersResult + usersResult;

        }
    }

    // endregion

    // region CustomerDAOImp Methods

    @Override
    public Date getJoinDate(int userId) throws SQLException {

        String query = "SELECT JoinDate FROM Customers where userId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getDate("JoinDate");

        }

    }

    @Override
    public Date getExpiryDate(int userId) throws SQLException {

        String query = "SELECT ExpiryDate FROM Customers where userId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getDate("ExpiryDate");

        }

    }

    // endregion

}
