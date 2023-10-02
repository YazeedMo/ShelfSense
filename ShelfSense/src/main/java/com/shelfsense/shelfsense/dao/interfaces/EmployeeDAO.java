package com.shelfsense.shelfsense.dao.interfaces;

import com.shelfsense.shelfsense.model.Employee;

import java.sql.SQLException;
import java.time.LocalDate;

public interface EmployeeDAO extends GenericDAO<Employee> {

    LocalDate getHireDate(int userId) throws SQLException;

    public String getPosition (Employee employee);

}
