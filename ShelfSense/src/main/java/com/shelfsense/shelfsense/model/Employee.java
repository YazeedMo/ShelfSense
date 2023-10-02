package com.shelfsense.shelfsense.model;

import java.time.LocalDate;

public abstract class Employee extends User {

    // Employee attributes
    private LocalDate hireDate;
    private String position;

    // Public constructor
    public Employee(int employeeId, String firstName, String lastName, String username, String password, LocalDate hireDate, String position) {
        super(employeeId, firstName, lastName, username, password);
        this.hireDate = hireDate;
        this.position = position;
    }

    // Getters
    public LocalDate getHireDate() {
        return hireDate;
    }
    public String getPosition() {
        return position;
    }

    // Setters
    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + super.getUserId() +
                ", firstName='" + super.getFirstName() + '\'' +
                ", lastName='" + super.getLastName() + '\'' +
                ", username='" + super.getUsername() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", hireDate='" + this.hireDate + '\'' +
                ", position=" + this.position + '\'' +
                '}';
    }

}
