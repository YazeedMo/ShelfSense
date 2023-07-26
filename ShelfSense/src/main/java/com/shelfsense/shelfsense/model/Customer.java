package com.shelfsense.shelfsense.model;

import java.util.Date;

public class Customer extends User {

    // Customer attributes
    private Date joinDate;
    private Date expiryDate;

    // Public Constructor
    public Customer(int customerId, String firstName, String lastName, String username, String password, Date joinDate, Date expiryDate) {
        super(customerId, firstName, lastName, username, password);
        this.joinDate = joinDate;
        this.expiryDate = expiryDate;
    }

    // Getters
    public Date getJoinDate() {
        return joinDate;
    }
    public Date getExpiryDate() {
        return expiryDate;
    }

    // Setters
    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + super.getUserId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", joinDate='" + this.joinDate + '\'' +
                ", expiryDate='" + this.expiryDate + '\'' +
                '}';
    }
}
