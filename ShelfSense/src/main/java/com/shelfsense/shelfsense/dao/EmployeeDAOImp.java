package com.shelfsense.shelfsense.dao;

import java.sql.*;

public class EmployeeDAOImp extends UserDAOImp implements EmployeeDAO {

    @Override
    public Date getHiredate(int userId) throws SQLException {

        String query = "SELECT HireDate FROM Employees where userId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getDate("HireDate");

        }

    }

    @Override
    public String getRole(int userId) throws SQLException {

        String query = "SELECT Role FROM Employees where userId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getString("HireDate");

        }

    }
}
