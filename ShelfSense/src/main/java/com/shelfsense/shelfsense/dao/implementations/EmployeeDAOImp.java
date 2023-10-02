package com.shelfsense.shelfsense.dao.implementations;

import com.shelfsense.shelfsense.dao.Database;
import com.shelfsense.shelfsense.dao.interfaces.EmployeeDAO;
import com.shelfsense.shelfsense.dao.interfaces.LibrarianDAO;
import com.shelfsense.shelfsense.dao.interfaces.ManagerDAO;
import com.shelfsense.shelfsense.model.Employee;
import com.shelfsense.shelfsense.model.Librarian;
import com.shelfsense.shelfsense.model.Manager;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImp implements EmployeeDAO {

    private final ManagerDAO managerDAO = new ManagerDAOImp();
    private final LibrarianDAO librarianDAO = new LibrarianDAOImp();

    // region GenericDAO Methods

    @Override
    public List<Employee> getAll() throws SQLException {

        List<Employee> allEmployees = new ArrayList<>();

        // Get List of all Managers and Librarians
        List<Manager> allManagers = new ManagerDAOImp().getAll();
        List<Librarian> allLibrarians = new LibrarianDAOImp().getAll();

        // Combine both lists into allEmployees
        allEmployees.addAll(allManagers);
        allEmployees.addAll(allLibrarians);

        return allEmployees;
    }

    @Override
    public Employee getWithId(int id) throws SQLException {

        Employee employee;

        String employeePosition = getPositionWithId(id);

        if (employeePosition.equalsIgnoreCase("Manager")) {
            employee = new ManagerDAOImp().getWithId(id);
        }
        else {
            employee = new LibrarianDAOImp().getWithId(id);
        }

        return employee;
    }

    @Override
    public int insert(Employee employee) throws SQLException {

        String employeePosition = employee.getPosition();

        if (employeePosition.equalsIgnoreCase("Manager")) {
            return new ManagerDAOImp().insert((Manager) employee);
        }
        else {
            return new LibrarianDAOImp().insert((Librarian) employee);
        }

    }

    @Override
    public int update(Employee employee) throws SQLException {

        String oldPosition = getOldPosition(employee);
        String newPosition = employee.getPosition();

        if (oldPosition.equalsIgnoreCase(newPosition)) {
            if (newPosition.equalsIgnoreCase("Manager")) {
                return managerDAO.update((Manager) employee);
            }
            else {
                return librarianDAO.update((Librarian) employee);
            }
        }
        else if (oldPosition.equalsIgnoreCase("Manager")) {
            delete(employee);
            return librarianDAO.insert((Librarian) employee);
        }
        else {
            delete(employee);
            return managerDAO.insert((Manager) employee);
        }
    }

    @Override
    public int delete(Employee employee) throws SQLException {

        // SQL to update Users table
        // Cascade delete in database so only need to specify root table delete
        String usersSQL = "DELETE FROM Users WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(usersSQL)) {

            ps.setInt(1, employee.getUserId());

            return ps.executeUpdate();
        }
    }

    // endregion


    // region EmployeeDAOIMP Methods

    @Override
    public LocalDate getHireDate(int userId) throws SQLException {

        String query = "SELECT HireDate FROM Employees where userId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getObject("HireDate", LocalDate.class);
        }
    }

    @Override
    public String getPosition (Employee employee) {

        String employeePosition = null;

        String query = "SELECT Position FROM UsersAndEmployees " +
                "WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, employee.getUserId());
            ResultSet rs = ps.executeQuery();

            rs.next();
            employeePosition = rs.getString("Position");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePosition;
    }

    // endregion


    // region Private Methods

    private String getPositionWithId(int userId) throws SQLException {

        String query = "SELECT Position FROM UsersAndEmployees " +
                "WHERE UserId = ?";

        try (Connection connection = Database.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, String.valueOf(userId));
            ResultSet rs = ps.executeQuery();

            rs.next();
            return rs.getString("Position");

        }
    }

    // Determine previous position of an Employee
    private String getOldPosition(Employee employee) {

        String oldPosition = null;

        try {
            oldPosition = getWithId(employee.getUserId()).getPosition();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return oldPosition;

    }

    // endregion
}
