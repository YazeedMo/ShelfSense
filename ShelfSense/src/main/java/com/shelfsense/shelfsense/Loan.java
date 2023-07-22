package com.shelfsense.shelfsense;

import java.util.Date;

public class Loan {

    // Loan attributes
    private int id;
    private int bookId;
    private int customerId;
    private Date borrowedDate;
    private Date returnedDate;

    // Public constructor
    public Loan(int id, int bookId, int customerId, Date borrowedDate, Date returnedDate) {
        this.id = id;
        this.bookId = bookId;
        this.customerId = customerId;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    // Getters
    public int getId() {
        return id;
    }
    public int getBookId() {
        return bookId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public Date getBorrowedDate() {
        return borrowedDate;
    }
    public Date getReturnedDate() {
        return returnedDate;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }
    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }
}
