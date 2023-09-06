package com.shelfsense.shelfsense.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

public class JavaFXUtils {

    // Enum to store fxml paths
    public enum FXMLPaths {

        LOGIN("/com/shelfsense/shelfsense/Login.fxml"),
        MANAGER_MAIN_MENU("/com/shelfsense/shelfsense/ManagerMainMenu.fxml"),
        ADD_LIBRARIAN("/com/shelfsense/shelfsense/AddLibrarian.fxml"),
        LIBRARIAN_MAIN_MENU("/com/shelfsense/shelfsense/LibrarianMainMenu.fxml"),
        CUSTOMER_MAIN_MENU("/com/shelfsense/shelfsense/CustomerMainMenu.fxml"),
        MANAGE_LIBRARIANS("/com/shelfsense/shelfsense/ManageLibrarians.fxml");

        private final String path;

        FXMLPaths(String path) {
            this.path = path;
        }

        public String getPath() {
            return this.path;
        }

    }

    // Stack to store previous Scenes
    private static final Stack<Scene> sceneHistory = new Stack<>();

    // Method to show next scene that will be stored on the sceneHistory stack
    public static void showNextScene(Node node, String fxmlPath) {

        try {
            sceneHistory.push(getCurrentStage(node).getScene());
            Parent root = FXMLLoader.load(Objects.requireNonNull(JavaFXUtils.class.getResource(fxmlPath)));
            Scene scene = new Scene(root);
            getCurrentStage(node).setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Method to change to scene that should not be stored on the sceneHistory stack
    public static void changeScene(Node node, String fxmlPath) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(JavaFXUtils.class.getResource(fxmlPath)));
            Scene scene = new Scene(root);
            getCurrentStage(node).setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static void showPreviousScene (Node node) {
        getCurrentStage(node).setScene(sceneHistory.pop());
    }

    public static Stage getCurrentStage (Node node) {
        return (Stage) node.getScene().getWindow();
    }

}
