package com.shelfsense.shelfsense.model;

import java.time.LocalDate;

public class Employee extends User {

    // Employee attributes
    private LocalDate hireDate;
    private String role;

    // Public constructor
    public Employee(int employeeId, String firstName, String lastName, String username, String password, String type, LocalDate hireDate, String role) {
        super(employeeId, firstName, lastName, username, password, type);
        this.hireDate = hireDate;
        this.role = role;
    }

    // Getters
    public LocalDate getHireDate() {
        return hireDate;
    }
    public String getRole() {
        return role;
    }

    // Setters
    public void setHireDate(LocalDate hireDate) {
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
