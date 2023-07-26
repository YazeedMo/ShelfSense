package com.shelfsense.shelfsense.dao;

import com.shelfsense.shelfsense.model.User;

import java.sql.Date;
import java.sql.SQLException;

public interface CustomerDAO extends UserDAO {

    Date getJoinDate(int userId) throws SQLException;

    Date getExpiryDate(int userId) throws SQLException;

}
