package com.shelfsense.shelfsense.dao.interfaces;

import com.shelfsense.shelfsense.model.Customer;

import java.sql.Date;
import java.sql.SQLException;

public interface CustomerDAO extends GenericDAO<Customer> {

    Date getJoinDate(int userId) throws SQLException;

    Date getExpiryDate(int userId) throws SQLException;

}
