package com.shelfsense.shelfsense.dao.implementations;

import com.shelfsense.shelfsense.dao.Database;
import com.shelfsense.shelfsense.dao.interfaces.ManagerDAO;
import com.shelfsense.shelfsense.model.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManagerDAOImp implements ManagerDAO {


    // region GenericDAO Methods

    @Override
    public List<Manager> getAll() throws SQLException {

        List<Manager> allManagers = new ArrayList<>();

        String query = "SELECT UserId, FirstName, LastName, Username, Password, HireDate, Position " +
                "FROM UsersAndEmployees " +
                "WHERE Position = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, "Manager");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("UserId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                LocalDate hireDate = rs.getObject("HireDate", LocalDate.class);
                String position = rs.getString("Position");

                allManagers.add(new Manager(employeeId, firstName, lastName, username, password, hireDate, position));

            }
        }
        return allManagers;
    }

    @Override
    public Manager getWithId(int id) throws SQLException {

        Manager manager = null;

        String query = "SELECT UserId, FirstName, LastName, Username, Password, HireDate, Position " +
                "FROM UsersAndEmployees " +
                "WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("UserId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                LocalDate hireDate = rs.getObject("HireDate", LocalDate.class);
                String position = rs.getString("Position");

                manager = new Manager(employeeId, firstName, lastName, username, password, hireDate, position);

            }
        }
        return manager;
    }

    @Override
    public int insert(Manager manager) throws SQLException {

        // SQL to insert into Users table
        String usersSQL = "INSERT INTO Users " +
                "VALUES (?, ?, ?, ?, ?)";

        // SQL to insert into Employee table
        String employeeSQL = "INSERT INTO Employees " +
                "VALUES (?, ?)";

        // SQL to insert into Managers table
        String managerSQL = "INSERT INTO Managers " +
                "VALUES (?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement psForUsers = connection.prepareStatement(usersSQL);
             PreparedStatement psForEmployees = connection.prepareStatement(employeeSQL);
             PreparedStatement psForManagers = connection.prepareStatement(managerSQL)) {

            psForUsers.setInt(1, manager.getUserId());
            psForUsers.setString(2, manager.getFirstName());
            psForUsers.setString(3, manager.getLastName());
            psForUsers.setString(4, manager.getUsername());
            psForUsers.setString(5, manager.getPassword());

            psForEmployees.setInt(1, manager.getUserId());
            psForEmployees.setObject(2, manager.getHireDate());

            psForManagers.setInt(1, manager.getUserId());

            int usersResult = psForUsers.executeUpdate();
            int employeesResult = psForEmployees.executeUpdate();
            int managersResult = psForManagers.executeUpdate();

            return usersResult + employeesResult + managersResult;

        }
    }

    @Override
    public int update(Manager manager) throws SQLException {

        // SQL to update Employees table
        String employeesSQL = "UPDATE Employees SET " +
                "HireDate = ?" +
                "WHERE EmployeeId = ?";

        // SQL to update Users table
        String usersSQL = "UPDATE Users SET " +
                "FirstName = ?, " +
                "LastName = ?, " +
                "Username = ?, " +
                "Password = ? " +
                "WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement psForEmployees = connection.prepareStatement(employeesSQL);
             PreparedStatement psForUsers = connection.prepareStatement(usersSQL)) {

            psForEmployees.setObject(1, manager.getHireDate());
            psForEmployees.setInt(2, manager.getUserId());

            psForUsers.setString(1, manager.getFirstName());
            psForUsers.setString(2, manager.getLastName());
            psForUsers.setString(3, manager.getUsername());
            psForUsers.setString(4, manager.getPassword());
            psForUsers.setInt(5, manager.getUserId());

            int employeesResult = psForEmployees.executeUpdate();
            int usersResult = psForUsers.executeUpdate();

            return employeesResult + usersResult;
        }
    }

    @Override
    public int delete(Manager manager) throws SQLException {

        // SQL to update Users table
        // Cascade delete in database so only need to specify root table delete
        String usersSQL = "DELETE FROM Users WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(usersSQL)) {

            ps.setInt(1, manager.getUserId());

            return ps.executeUpdate();

        }
    }

    // endregion

}
