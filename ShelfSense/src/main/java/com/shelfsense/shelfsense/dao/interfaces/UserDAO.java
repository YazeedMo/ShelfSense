package com.shelfsense.shelfsense.dao.interfaces;

import com.shelfsense.shelfsense.dao.interfaces.GenericDAO;
import com.shelfsense.shelfsense.model.User;

import java.sql.SQLException;

public interface UserDAO extends GenericDAO<User> {

    boolean usernameExists(String username) throws SQLException;

    String getUserPassword(String username) throws SQLException;

    User getWithUsername(String username) throws SQLException;

}
