package com.shelfsense.shelfsense.dao;

import com.shelfsense.shelfsense.model.Employee;

import java.sql.Date;
import java.sql.SQLException;

public interface EmployeeDAO extends GenericDAO<Employee> {

    Date getHiredate(int userId) throws SQLException;

    String getRole(int userId) throws SQLException;

}
