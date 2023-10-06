package com.shelfsense.shelfsense.model;

import java.time.LocalDate;
import java.util.Date;

public class Book {

    // Book attributes
    private int bookId;
    private String title;
    private String author;
    private String ISBN;
    private String genre;
    private LocalDate publicationDate;
    private String publisher;
    private String edition;
    private int quantity;

    // Public constructor
    public Book(int bookId, String title, String author, String ISBN, String genre, LocalDate publicationDate, String publisher, String edition, int quantity) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.edition = edition;
        this.quantity = quantity;
    }

    // Getters
    public int getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getISBN() {
        return ISBN;
    }
    public String getGenre() {
        return genre;
    }
    public LocalDate getPublicationDate() {
        return publicationDate;
    }
    public String getPublisher() {
        return publisher;
    }
    public String getEdition() {
        return edition;
    }
    public int getQuantity() {
        return quantity;
    }

    // Setters
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
