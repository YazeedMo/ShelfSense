package com.shelfsense.shelfsense.dao.implementations;

import com.shelfsense.shelfsense.dao.Database;
import com.shelfsense.shelfsense.dao.interfaces.LibrarianDAO;
import com.shelfsense.shelfsense.model.Librarian;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LibrarianDAOImp implements LibrarianDAO {


    // region GenericDAO Methods

    @Override
    public List<Librarian> getAll() throws SQLException {

        List<Librarian> alLibrarians = new ArrayList<>();

        String query = "SELECT UserId, FirstName, LastName, Username, Password, HireDate, Position " +
                "FROM UsersAndEmployees " +
                "WHERE Position = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, "Librarian");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("UserId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                LocalDate hireDate = rs.getObject("HireDate", LocalDate.class);
                String position = rs.getString("Position");

                alLibrarians.add(new Librarian(employeeId, firstName, lastName, username, password, hireDate, position));
            }
        }
        return alLibrarians;
    }

    @Override
    public Librarian getWithId(int id) throws SQLException {

        Librarian librarian = null;

        String query = "SELECT UserId, FirstName, LastName, Username, Password, HireDate, Position " +
                "FROM UsersAndEmployees " +
                "WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(id));
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int employeeId = rs.getInt("UserId");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String username = rs.getString("Username");
                String password = rs.getString("Password");
                LocalDate hireDate = rs.getObject("HireDate", LocalDate.class);
                String position = rs.getString("Position");

                librarian = new Librarian(employeeId, firstName, lastName, username, password, hireDate, position);

            }
        }
        return librarian;
    }

    @Override
    public int insert(Librarian librarian) throws SQLException {

        // SQL to insert into Users table
        String usersSQL = "INSERT INTO Users " +
                "VALUES (?, ?, ?, ?, ?)";

        // SQL to insert into Employee table
        String employeeSQL = "INSERT INTO Employees " +
                "VALUES (?, ?)";

        // SQL to insert into Librarians table
        String librarianSQL = "INSERT INTO Librarians " +
                "VALUES (?)";

        try (Connection connection = Database.getConnection();
             PreparedStatement psForUsers = connection.prepareStatement(usersSQL);
             PreparedStatement psForEmployees = connection.prepareStatement(employeeSQL);
             PreparedStatement psForLibrarians = connection.prepareStatement(librarianSQL)) {

            psForUsers.setInt(1, librarian.getUserId());
            psForUsers.setString(2, librarian.getFirstName());
            psForUsers.setString(3, librarian.getLastName());
            psForUsers.setString(4, librarian.getUsername());
            psForUsers.setString(5, librarian.getPassword());

            psForEmployees.setInt(1, librarian.getUserId());
            psForEmployees.setObject(2, librarian.getHireDate());

            psForLibrarians.setInt(1, librarian.getUserId());

            int usersResult = psForUsers.executeUpdate();
            int employeesResult = psForEmployees.executeUpdate();
            int librariansResult = psForLibrarians.executeUpdate();

            return usersResult + employeesResult + librariansResult;

        }
    }

    @Override
    public int update(Librarian librarian) throws SQLException {

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

            psForEmployees.setObject(1, librarian.getHireDate());
            psForEmployees.setInt(2, librarian.getUserId());

            psForUsers.setString(1, librarian.getFirstName());
            psForUsers.setString(2, librarian.getLastName());
            psForUsers.setString(3, librarian.getUsername());
            psForUsers.setString(4, librarian.getPassword());
            psForUsers.setInt(5, librarian.getUserId());

            int employeesResult = psForEmployees.executeUpdate();
            int usersResult = psForUsers.executeUpdate();

            return employeesResult + usersResult;

        }
    }

    @Override
    public int delete(Librarian librarian) throws SQLException {

        // SQL to update Users table
        // Cascade delete in database so only need to specify root table delete
        String usersSQL = "DELETE FROM Users WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(usersSQL)) {

            ps.setInt(1, librarian.getUserId());

            return ps.executeUpdate();

        }
    }

    // endregion

}
