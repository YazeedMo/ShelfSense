package com.shelfsense.shelfsense.model;

import java.util.Date;

public class Loan {

    // Loan attributes
    private int loanId;
    private int bookId;
    private int customerId;
    private Date borrowedDate;
    private Date returnedDate;

    // Public constructor
    public Loan(int loanId, int bookId, int customerId, Date borrowedDate, Date returnedDate) {
        this.loanId = loanId;
        this.bookId = bookId;
        this.customerId = customerId;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
    }

    // Getters
    public int getLoanId() {
        return loanId;
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
    public void setLoanId(int loanId) {
        this.loanId = loanId;
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
