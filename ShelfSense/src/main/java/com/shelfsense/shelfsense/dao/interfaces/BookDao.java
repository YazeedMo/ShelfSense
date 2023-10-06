package com.shelfsense.shelfsense.dao.interfaces;

import com.shelfsense.shelfsense.model.Book;

import java.sql.SQLException;
import java.util.Set;

public interface BookDao extends GenericDAO<Book> {

    Set<Integer> getUsedIds() throws SQLException;

    Book getWithISBN(String bookISBN) throws SQLException;

}
