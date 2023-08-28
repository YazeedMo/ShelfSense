package com.shelfsense.shelfsense;

import com.shelfsense.shelfsense.dao.implementations.CustomerDAOImp;
import com.shelfsense.shelfsense.model.Customer;
import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Test {

    public static void main(String[] args) {

        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(Test.class.getResource(JavaFXUtils.FXMLPaths.MANAGE_LIBRARIANS.getPath())));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}
