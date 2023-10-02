package com.shelfsense.shelfsense.model;

import java.time.LocalDate;

public class Customer extends User {

    // Customer attributes
    private LocalDate joinDate;
    private LocalDate expiryDate;

    // Public Constructor
    public Customer(int customerId, String firstName, String lastName, String username, String password, LocalDate joinDate, LocalDate expiryDate) {
        super(customerId, firstName, lastName, username, password);
        this.joinDate = joinDate;
        this.expiryDate = expiryDate;
    }

    // Getters
    public LocalDate getJoinDate() {
        return joinDate;
    }
    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    // Setters
    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }
    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Add Customer-specific functionalities here

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
