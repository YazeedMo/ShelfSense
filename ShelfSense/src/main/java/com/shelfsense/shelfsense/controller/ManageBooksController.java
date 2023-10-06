package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.dao.implementations.BookDaoImp;
import com.shelfsense.shelfsense.dao.interfaces.BookDao;
import com.shelfsense.shelfsense.model.Book;
import com.shelfsense.shelfsense.services.BookService;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageBooksController {

    @FXML
    private TableView<Book> tblViewBooks;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    private Book selectedBook;

    private final BookDao bookDao = new BookDaoImp();
    private final BookService bookService = new BookService();

    @FXML
    private void initialize() {

        updateTblViewBooks();

        updateButtons();

    }

    @FXML
    void btnBackClicked(ActionEvent event) {

        JavaFXUtils.showPreviousScene(tblViewBooks);

    }

    @FXML
    void btnAddClicked(ActionEvent event) {

        bookService.showAddBookScene();

        // Update tblViewBooks after new Book has been added
        updateTblViewBooks();

    }

    @FXML
    void btnEditClicked(ActionEvent event) {

    }

    @FXML
    void btnDeleteClicked(ActionEvent event) {

    }

    private void updateTblViewBooks()   {

        List<Book> bookList = new ArrayList<>();

        // Clear initial Table View (any default columns created by Scene Builder
        tblViewBooks.getColumns().clear();

        // Add our own custom columns (For each attribute of the Book object)
        TableColumn<Book, Integer> bookIdColumn = new TableColumn<>("Book ID");
        bookIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookId"));

        TableColumn<Book, String> bookTitleColumn = new TableColumn<>("Title");
        bookTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book, String> bookAuthorColumn = new TableColumn<>("Author");
        bookAuthorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book, String> bookISBNColumn = new TableColumn<>("ISBN");
        bookISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));

        TableColumn<Book, String> bookGenreColumn = new TableColumn<>("Genre");
        bookGenreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Book, LocalDate> bookPublicationDateColumn = new TableColumn<>("Publication Date");
        bookPublicationDateColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));

        TableColumn<Book, String> bookPublisherColumn = new TableColumn<>("Publisher");
        bookPublisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        TableColumn<Book, String> bookEditionColumn = new TableColumn<>("Edition");
        bookEditionColumn.setCellValueFactory(new PropertyValueFactory<>("edition"));

        TableColumn<Book, Integer> bookQuantityColumn = new TableColumn<>("Quantity");
        bookQuantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        // Add all columns ot the Table View
        tblViewBooks.getColumns().add(bookIdColumn);
        tblViewBooks.getColumns().add(bookTitleColumn);
        tblViewBooks.getColumns().add(bookAuthorColumn);
        tblViewBooks.getColumns().add(bookISBNColumn);
        tblViewBooks.getColumns().add(bookGenreColumn);
        tblViewBooks.getColumns().add(bookPublicationDateColumn);
        tblViewBooks.getColumns().add(bookPublisherColumn);
        tblViewBooks.getColumns().add(bookEditionColumn);
        tblViewBooks.getColumns().add(bookQuantityColumn);

        // Adjust column widths
        tblViewBooks.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Get a list of all existing Books
        try {
            bookList = bookDao.getAll();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert bookList into an ObservableList
        ObservableList<Book> bookObservableList = FXCollections.observableList(bookList);

        tblViewBooks.setItems(bookObservableList);

    }

    private void updateButtons() {

        tblViewBooks.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {

            if (newValue != null) {
                // A Book was selected
                selectedBook = newValue;
                btnEdit.setDisable(false);
                btnDelete.setDisable(false);
            }
            else {
                btnEdit.setDisable(true);
                btnDelete.setDisable(true);
            }

        });

    }

}
