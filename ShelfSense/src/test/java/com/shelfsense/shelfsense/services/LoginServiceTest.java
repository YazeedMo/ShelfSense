package com.shelfsense.shelfsense.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginServiceTest {

    private LoginService loginService;

    @BeforeEach
    void setup() {
        loginService = new LoginService();
    }

    // Should return true for all valid credentials
    @Test
    void trueForValidCredentials() {

        String[] usernames = {"admin", "jane", "david", "danny"};
        String[] passwords = {"A.Tester", "J.Smith", "D.Smith", "D.Wilson"};

        for (int i = 0; i < usernames.length; i++) {
            String username = usernames[i];
            String password = passwords[i];

            boolean isValid = loginService.isValidCredentials(username, password);

            assertTrue(isValid, "Failed with username: " + username + " and password: " + password);

        }
    }

    // Should return false for incorrect credentials
    @Test
    void falseForInvalidCredentials() {

        String username = "Impostor";
        String password = "FakeUser";

        boolean isValid = loginService.isValidCredentials(username, password);

        assertFalse(isValid);
    }

    // Should return false if username has incorrect casing
    @Test
    void falseForIncorrectUsernameCasing() {

        String username = "adMin";      // Correct spelling: admin
        String password = "A.Tester";

        boolean isValid = loginService.isValidCredentials(username, password);

        assertFalse(isValid);

    }

    // Should return false if password has incorrect casing
    @Test
    void falseForIncorrectPasswordCasing() {

        String username = "admin";
        String password = "A.TesTer";   // Correct spelling: A.Tester

        boolean isValid = loginService.isValidCredentials(username, password);

        assertFalse(isValid);

    }

    // Should return false for empty username
    @Test
    void falseForEmptyUsername() {

        String username = "";           // Correct spelling: admin
        String password = "A.Tester";

        boolean isValid = loginService.isValidCredentials(username, password);

        assertFalse(isValid);

    }

    // Should return false for empty password
    @Test
    void falseForEmptyPassword() {

        String username = "admin";
        String password = "";           // Correct spelling: A.Tester

        boolean isValid = loginService.isValidCredentials(username, password);

        assertFalse(isValid);

    }

    // Should return false for null username
    @Test
    void falseForNullUsername() {

        String username = null;         // Correct spelling: admin
        String password = "A.Tester";

        boolean isValid = loginService.isValidCredentials(username, password);

        assertFalse(isValid);

    }

    // Should return false for null password
    @Test
    void falseForNullPassword() {

        String username = "admin";
        String password = null;          // Correct spelling: A.Tester

        boolean isValid = loginService.isValidCredentials(username, password);

        assertFalse(isValid);

    }

    // Should return false for trailing whitespace in username
    @Test
    void falseForUsernameWhitespace() {

        String username = "  admin  ";  // Correct spelling: admin
        String password = "A.Tester";

        boolean isValid = loginService.isValidCredentials(username, password);

        assertFalse(isValid);

    }

    // Should return false for trailing whitespace in password
    @Test
    void falseForPasswordWhitespace() {

        String username = "admin";
        String password = "  A.Tester  ";  // Correct password: A.Tester

        boolean isValid = loginService.isValidCredentials(username, password);

        assertFalse(isValid);

    }

}