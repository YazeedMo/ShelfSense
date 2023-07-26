package com.shelfsense.shelfsense.util;

import com.shelfsense.shelfsense.model.User;

public class Session {

    // Singleton
    private static Session instance;

    private User currentUser;

    private Session() {
        // Private constructor to prevent instantiation from outside
    }

    public static Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
