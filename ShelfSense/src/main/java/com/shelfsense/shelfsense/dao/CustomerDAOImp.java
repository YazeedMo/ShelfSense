package com.shelfsense.shelfsense.dao;

import com.shelfsense.shelfsense.model.User;

import java.sql.*;

public class CustomerDAOImp extends UserDAOImp implements CustomerDAO {

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
}
