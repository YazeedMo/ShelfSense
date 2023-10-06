package com.shelfsense.shelfsense.services;

import com.shelfsense.shelfsense.dao.implementations.BookDaoImp;
import com.shelfsense.shelfsense.dao.interfaces.BookDao;
import com.shelfsense.shelfsense.model.Book;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.sql.SQLException;
import java.util.Set;

public class BookService {

    private final BookDao bookDao = new BookDaoImp();

    // Returns an ObservableList of ID numbers that have not yet been used
    public ObservableList<Integer> getAvailableIds() {

        // Fetch used IDs from the database
        Set<Integer> usedIds = null;
        try {
            usedIds = bookDao.getUsedIds();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Determine available IDs
        ObservableList<Integer> availableIds = FXCollections.observableArrayList();
        for (int i = 1; i <= 100; i++) {
            if (usedIds != null && !usedIds.contains(i)) {
                availableIds.add(i);
            }
        }
        return availableIds;
    }

    public void addBook(Book book) {

        try {
            bookDao.insert(book);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void editBook(Book book) {

        try {
            bookDao.update(book);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deleteBook(Book book) {

        if (confirmDeletion(book)) {
            try {
                bookDao.delete(book);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean confirmDeletion(Book book) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete the following Book?");
        alert.setContentText("Book ID: " + book.getBookId() + "\nTitle: " + book.getTitle());

        alert.showAndWait();

        return alert.getResult() == ButtonType.OK;
    }

}
