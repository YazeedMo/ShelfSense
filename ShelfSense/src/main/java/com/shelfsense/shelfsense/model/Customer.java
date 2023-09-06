package com.shelfsense.shelfsense.model;

import java.time.LocalDate;
import java.util.Date;

public class Customer extends User {

    // Customer attributes
    private LocalDate joinDate;
    private LocalDate expiryDate;

    // Public Constructor
    public Customer(int customerId, String firstName, String lastName, String username, String password, String type, LocalDate joinDate, LocalDate expiryDate) {
        super(customerId, firstName, lastName, username, password, type);
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + super.getUserId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", Type='" + super.getType() + '\'' +
                ", joinDate='" + this.joinDate + '\'' +
                ", expiryDate='" + this.expiryDate + '\'' +
                '}';
    }
}
