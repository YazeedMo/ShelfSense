package com.shelfsense.shelfsense.dao.implementations;

import com.shelfsense.shelfsense.dao.Database;
import com.shelfsense.shelfsense.dao.interfaces.EmployeeDAO;
import com.shelfsense.shelfsense.model.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImp implements EmployeeDAO {

    // region GenericDAO Methods

    @Override
    public Employee getWithId(int id) throws SQLException {

        Employee employee = null;

        String query = "SELECT Users.*, Employees.* " +
                "FROM Users " +
                "INNER JOIN Employees ON Users.UserId = Employees.EmployeeId " +
                "Where Users.UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("EmployeeId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String type = rs.getString("Type");
                LocalDate joinDate = rs.getObject("HireDate", LocalDate.class);
                String role = rs.getString("Role");

                employee = new Employee(employeeId, firstName, lastName, username, password, type, joinDate, role);

            }
        }

        return employee;
    }

    @Override
    public List<Employee> getAll() throws SQLException {

        List<Employee> allEmployees = new ArrayList<>();

        String query = "SELECT Users.*, Employees.* " +
                "FROM Users " +
                "INNER JOIN Employees ON Users.UserId = Employees.EmployeeId";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("EmployeeId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                String type = rs.getString("Type");
                LocalDate hireDate = rs.getObject("HireDate", LocalDate.class);
                String role = rs.getString("Role");

                allEmployees.add(new Employee(employeeId, firstName, lastName, username, password, type, hireDate, role));
            }

        }

        return allEmployees;
    }

    @Override
    public int insert(Employee employee) throws SQLException {

        // SQL to insert details into Users table
        String usersSQL = "INSERT INTO Users " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        // SQL to insert details into Employees table
        String employeesSQL = "INSERT INTO Employees " +
                "VALUES (?, ?, ?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement psForUsers = connection.prepareStatement(usersSQL);
             PreparedStatement psForEmployees = connection.prepareStatement(employeesSQL)) {

            psForUsers.setInt(1, employee.getUserId());
            psForUsers.setString(2, employee.getFirstName());
            psForUsers.setString(3, employee.getLastName());
            psForUsers.setString(4, employee.getUsername());
            psForUsers.setString(5, employee.getPassword());
            psForUsers.setString(6, "Employee");

            psForEmployees.setInt(1, employee.getUserId());
            psForEmployees.setDate(2, java.sql.Date.valueOf(employee.getHireDate()));
            psForEmployees.setString(3, employee.getRole());

            int usersResult = psForUsers.executeUpdate();
            int employeesResult = psForEmployees.executeUpdate();

            return usersResult + employeesResult;

        }
    }

    @Override
    public int update(Employee employee) throws SQLException {

        // SQL to update the Employees table
        String employeesSQL = "UPDATE Employees SET " +
                "EmployeeID = ?, " +
                "HireDate = ?, " +
                "Role = ?, " +
                "WHERE EmployeeID = ?;";

        // SQL to update the User table
        String usersSQL = "UPDATE Users SET " +
                "UserId = ?, " +
                "FirstName = ?, " +
                "Lastname = ?, " +
                "Username = ?, " +
                "Password = ?, " +
                "Type = ?, " +
                "WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement psForEmployees = connection.prepareStatement(employeesSQL);
             PreparedStatement psForUsers = connection.prepareStatement(usersSQL)) {

            psForEmployees.setInt(1, employee.getUserId());
            psForEmployees.setDate(2, java.sql.Date.valueOf(employee.getHireDate()));
            psForEmployees.setString(3, employee.getRole());
            psForEmployees.setInt(4, employee.getUserId());

            psForUsers.setInt(1, employee.getUserId());
            psForUsers.setString(2, employee.getUsername());
            psForUsers.setString(3, employee.getLastName());
            psForUsers.setString(4, employee.getUsername());
            psForUsers.setString(5, employee.getPassword());
            psForUsers.setString(6, "Employee");
            psForUsers.setInt(7, employee.getUserId());

            int employeesResult = psForEmployees.executeUpdate();
            int usersResult = psForUsers.executeUpdate();

            return employeesResult + usersResult;

        }
    }

    @Override
    public int delete(Employee employee) throws SQLException {

        // SQL to update Employees table
        String employeesSQL = "DELETE FROM Employees WHERE EmployeeId = ?";

        // SQL to update Users table
        String userSQL = "DELETE FROM Users WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement psForEmployees = connection.prepareStatement(employeesSQL);
             PreparedStatement psForUsers = connection.prepareStatement(userSQL)) {

            psForEmployees.setInt(1, employee.getUserId());

            psForUsers.setInt(1, employee.getUserId());

            int employeesResult = psForEmployees.executeUpdate();
            int usersResult = psForUsers.executeUpdate();

            return employeesResult + usersResult;

        }
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
