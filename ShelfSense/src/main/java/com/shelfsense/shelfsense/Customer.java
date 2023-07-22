package com.shelfsense.shelfsense;

import java.util.Date;

public class Customer extends Person {

    // Customer attributes
    private Date joinDate;
    private Date expiryDate;

    // Public Constructor
    public Customer(int id, String firstName, String lastName, String username, String password, Date joinDate, Date expiryDate) {
        super(id, firstName, lastName, username, password);
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
}
