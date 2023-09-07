package com.shelfsense.shelfsense;

import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Test {

    public static void main(String[] args) {

        Stage stage = new Stage();
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Test.class.getResource(JavaFXUtils.FXMLPaths.ADD_LIBRARIAN.getPath())));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
