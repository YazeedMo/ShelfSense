package com.shelfsense.shelfsense.dao;

import com.shelfsense.shelfsense.model.Customer;

import java.sql.*;
import java.util.List;

public class CustomerDAOImp implements CustomerDAO {

    // region GenericDAO Methods

    @Override
    public Customer getWithId(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Customer> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Customer customer) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Customer customer) throws SQLException {
        return 0;
    }

    @Override
    public int update(Customer customer) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Customer customer) throws SQLException {
        return 0;
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
