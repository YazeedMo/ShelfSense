package com.shelfsense.shelfsense.dao;

import com.shelfsense.shelfsense.model.User;

import java.sql.SQLException;

public interface UserDAO extends GenericDAO<User>{

    boolean usernameExists(String username) throws SQLException;

    String getUserPassword(String username) throws SQLException;

    User getWithUsername(String username) throws SQLException;

}
