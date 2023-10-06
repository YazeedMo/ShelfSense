package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.model.Book;
import com.shelfsense.shelfsense.services.BookService;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;

public class AddBookController {

    @FXML
    private Label lblNotifications;

    @FXML
    private ComboBox<Integer> comboBoxId;

    @FXML
    private TextField txtFieldTitle;

    @FXML
    private TextField txtFieldAuthor;

    @FXML
    private TextField txtFieldISBN;

    @FXML
    private TextField txtFieldGenre;

    @FXML
    private DatePicker datePickerPublicationDate;

    @FXML
    private TextField txtFieldPublisher;

    @FXML
    private TextField txtFieldEdition;

    @FXML
    private ComboBox<Integer> comboBoxQuantity;

    @FXML
    private Button btnAdd;

    private final BookService bookService = new BookService();

    private static Book selectedBook;

    @FXML
    private void initialize() {

        // Setup comboBoxes
        comboBoxId.setItems(bookService.getAvailableIds());
        comboBoxQuantity.setItems(bookService.getAvailableQuantities());

        // Setup datePickerHireDate
        datePickerPublicationDate.setEditable(false);

        // Initialize a FieldChangeListener and attach it to each field
        FieldChangeListener fieldChangeListener = new FieldChangeListener();
        comboBoxId.valueProperty().addListener(fieldChangeListener);
        txtFieldTitle.textProperty().addListener(fieldChangeListener);
        txtFieldAuthor.textProperty().addListener(fieldChangeListener);
        txtFieldISBN.textProperty().addListener(fieldChangeListener);
        txtFieldGenre.textProperty().addListener(fieldChangeListener);
        datePickerPublicationDate.valueProperty().addListener(fieldChangeListener);
        txtFieldPublisher.textProperty().addListener(fieldChangeListener);
        txtFieldEdition.textProperty().addListener(fieldChangeListener);
        comboBoxQuantity.valueProperty().addListener(fieldChangeListener);

        // This will run if user chose "Edit" in the ManageBooks window
        if (selectedBook != null) {
            comboBoxId.setDisable(true);
            txtFieldTitle.setText(selectedBook.getTitle());
            txtFieldAuthor.setText(selectedBook.getAuthor());
            txtFieldISBN.setText(selectedBook.getISBN());
            txtFieldGenre.setText(selectedBook.getGenre());
            datePickerPublicationDate.setValue(selectedBook.getPublicationDate());
            txtFieldPublisher.setText(selectedBook.getPublisher());
            txtFieldEdition.setText(selectedBook.getEdition());
            comboBoxQuantity.setValue(selectedBook.getQuantity());
        }

    }

    @FXML
    void btnAddClicked(ActionEvent event) {

        Book book = createBook();

        if (btnAdd.getText().equalsIgnoreCase("Add")) {
            bookService.addBook(book);
        }
        else {
            bookService.editBook(book);
        }

        selectedBook = null;

        // Close the window
        JavaFXUtils.getCurrentStage(btnAdd).close();

    }

    @FXML
    void btnCancelClicked(ActionEvent event) {

    }

    private Book createBook() {

        // Get the values in each field
        int id = comboBoxId.getValue();
        String title = txtFieldTitle.getText();
        String author = txtFieldAuthor.getText();
        String isbn = txtFieldISBN.getText();
        String genre = txtFieldGenre.getText();
        LocalDate publicationDate = datePickerPublicationDate.getValue();
        String publisher = txtFieldPublisher.getText();
        String edition = txtFieldEdition.getText();
        int quantity = comboBoxQuantity.getValue();

        return new Book(id, title, author, isbn, genre, publicationDate, publisher, edition, quantity);

    }

    // Handles the logic when user interacts with the fields
    private class FieldChangeListener implements ChangeListener<Object> {

        @Override
        public void changed(ObservableValue<?> observableValue, Object o, Object t1) {

            updateAddButtonState();

        }

        private void updateAddButtonState() {

            boolean validDetails = false;

            boolean allFieldsFilled = areAllFieldsFilled();
            boolean validISBN = isValidISBN();

            // Update label to notify users of any errors
            if (!allFieldsFilled) {
                lblNotifications.setText("Enter New Book Details");
            }
            else if (!validISBN) {
                lblNotifications.setText("ISBN already exists");
            }
            else {
                lblNotifications.setText("Good to go!");
                validDetails = true;
            }

            // Only enable add button when all fields are filled in correctly
            btnAdd.setDisable(!validDetails);

        }

        private boolean areAllFieldsFilled() {

            return comboBoxId.getValue() != null &&
                    !txtFieldTitle.getText().isEmpty() &&
                    !txtFieldAuthor.getText().isEmpty() &&
                    !txtFieldISBN.getText().isEmpty() &&
                    !txtFieldGenre.getText().isEmpty() &&
                    datePickerPublicationDate.getValue() != null &&
                    !txtFieldPublisher.getText().isEmpty() &&
                    !txtFieldEdition.getText().isEmpty() &&
                    comboBoxQuantity.getValue() != null;
        }

        private boolean isValidISBN() {

            return bookService.isISBNAvailable(txtFieldISBN.getText());

        }
    }
}
