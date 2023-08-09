package com.shelfsense.shelfsense.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {

    List<T> getAll() throws SQLException;

    T getWithId(int id) throws SQLException;

    int insert(T t) throws SQLException;

    int update(T t) throws SQLException;

    int delete(T t) throws SQLException;

}
