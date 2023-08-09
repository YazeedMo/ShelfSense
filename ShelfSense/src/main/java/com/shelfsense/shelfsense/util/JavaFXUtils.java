package com.shelfsense.shelfsense.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class JavaFXUtils {

    // Enum to store fxml paths
    public enum FXMLPaths {

        LOGIN("/com/shelfsense/shelfsense/Login.fxml"),
        MANAGER_MAIN_MENU("/com/shelfsense/shelfsense/ManagerMainMenu.fxml"),
        LIBRARIAN_MAIN_MENU("/com/shelfsense/shelfsense/LibrarianMainMenu.fxml"),
        CUSTOMER_MANI_MENU("/com/shelfsense/shelfsense/CustomerMainMenu.fxml");

        private final String path;

        FXMLPaths(String path) {
            this.path = path;
        }

        public String getPath() {
            return this.path;
        }

    }

    public static void switchScenes (Node node, String fxmlPath) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(JavaFXUtils.class.getResource(fxmlPath)));
            Scene scene = new Scene(root);
            getCurrentStage(node).setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static Stage getCurrentStage (Node node) {

        return (Stage) node.getScene().getWindow();

    }

}
