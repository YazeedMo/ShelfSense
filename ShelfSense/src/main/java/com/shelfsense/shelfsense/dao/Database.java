package com.shelfsense.shelfsense.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    // Database connectivity parameters
    private static String url = "jdbc:mysql://localhost:3306/ShelfSenseDB";
    private static String username = "otheruser";
    private static String password = "swordfish";

    // Method to return a database connection
    public static Connection getConnection() throws SQLException {

        Connection connection;
        connection = DriverManager.getConnection(url, username, password);

        return connection;

    }

}
