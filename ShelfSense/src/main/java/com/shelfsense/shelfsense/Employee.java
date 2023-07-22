package com.shelfsense.shelfsense;

import java.util.Date;

public class Employee extends Person {

    // Employee attributes
    private Date hireDate;
    private String role;

    // Public constructor
    public Employee(int id, String firstName, String lastName, String username, String password, Date hireDate, String role) {
        super(id, firstName, lastName, username, password);
        this.hireDate = hireDate;
        this.role = role;
    }

    // Getters
    public Date getHireDate() {
        return hireDate;
    }
    public String getRole() {
        return role;
    }

    // Setters
    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
    public void setRole(String role) {
        this.role = role;
    }
}
