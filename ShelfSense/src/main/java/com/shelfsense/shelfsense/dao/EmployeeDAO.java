package com.shelfsense.shelfsense.dao;

import java.sql.Date;
import java.sql.SQLException;

public interface EmployeeDAO extends UserDAO {

    Date getHiredate(int userId) throws SQLException;

    String getRole(int userId) throws SQLException;

}
