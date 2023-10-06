package com.shelfsense.shelfsense.services;

import com.shelfsense.shelfsense.controller.AddBookController;
import com.shelfsense.shelfsense.dao.implementations.BookDaoImp;
import com.shelfsense.shelfsense.dao.interfaces.BookDao;
import com.shelfsense.shelfsense.model.Book;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
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

    public ObservableList<Integer> getAvailableQuantities() {

        ObservableList<Integer> availableQuantities = FXCollections.observableArrayList();
        for (int i = 0; i <= 100; i++) {
            availableQuantities.add(i);
        }

        return availableQuantities;

    }

    public void showAddBookScene() {

        // Show AddBook window
        Stage addBookStage = new Stage();
        addBookStage.initModality(Modality.APPLICATION_MODAL);

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(JavaFXUtils.FXMLPaths.ADD_BOOK.getPath())));
            Scene scene = new Scene(root);
            addBookStage.setScene(scene);
            addBookStage.showAndWait();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showEditBookScene(Book book) {

        AddBookController.setSelectedBook(book);

        showAddBookScene();

    }

    public List<Book> getAllBooks() {

        List<Book> allBooks = null;

        try {
            allBooks = bookDao.getAll();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return allBooks;

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

    public boolean isISBNAvailable(String isbn) {

        Book book = null;

        try {
            book = bookDao.getWithISBN(isbn);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        return book == null;

    }

    private boolean confirmDeletion(Book book) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Are you sure you want to delete the following Book?");
        alert.setContentText("Book ID: " + book.getBookId() + "\nTitle: " + book.getTitle());

        alert.showAndWait();

        return alert.getResult() == ButtonType.OK;
    }


}
