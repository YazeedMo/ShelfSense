package com.shelfsense.shelfsense.model;

import java.util.Date;

public class Employee extends User {

    // Employee attributes
    private Date hireDate;
    private String role;

    // Public constructor
    public Employee(int employeeId, String firstName, String lastName, String username, String password, String type, Date hireDate, String role) {
        super(employeeId, firstName, lastName, username, password, type);
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + super.getUserId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", Type='" + super.getType() + '\'' +
                ", hireDate='" + this.hireDate + '\'' +
                ", role='" + this.role + '\'' +
                '}';
    }

}
