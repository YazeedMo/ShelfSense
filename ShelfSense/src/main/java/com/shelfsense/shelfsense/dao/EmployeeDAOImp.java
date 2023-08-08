package com.shelfsense.shelfsense.dao;

import com.shelfsense.shelfsense.model.Employee;

import java.sql.*;
import java.util.List;

public class EmployeeDAOImp implements EmployeeDAO {

    // region GenericDAO methods

    @Override
    public Employee getWithId(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Employee> getAll() throws SQLException {
        return null;
    }

    @Override
    public int save(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public int insert(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public int update(Employee employee) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Employee employee) throws SQLException {
        return 0;
    }

    // endregion

    // region EmployeeDAOIMP Methods

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

    // endregion

}
