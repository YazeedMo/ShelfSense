package com.shelfsense.shelfsense.services;

import com.shelfsense.shelfsense.dao.implementations.UserDAOImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Set;

public class EmployeeService {

    private static final int MIN_PASSWORD_LENGTH = 5;

    // Returns an ObservableList of ID numbers that have not yet been used
    public ObservableList<Integer> getAvailableIds() {

        // Fetch used IDs from the database
        Set<Integer> usedIds = new UserDAOImp().getUsedIds();

        // Determine available IDs
        ObservableList<Integer> availableIds = FXCollections.observableArrayList();
        for (int i = 1; i <= 100; i++) {
            if (!usedIds.contains(i)) {
                availableIds.add(i);
            }
        }

        return availableIds;

    }

    public int getMinPasswordLength() {
        return MIN_PASSWORD_LENGTH;
    }
}
