package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class LibrarianMainMenuController {

    @FXML
    void btnManageBooksClicked(ActionEvent event) {

    }

    @FXML
    void btnManageCustomersClicked(ActionEvent event) {

    }

    @FXML
    void btnManageLoansClicked(ActionEvent event) {

    }

    @FXML
    void btnBackClicked(ActionEvent event) {

        JavaFXUtils.switchScenes((Node) event.getSource(), JavaFXUtils.FXMLPaths.LOGIN.getPath());

    }
}