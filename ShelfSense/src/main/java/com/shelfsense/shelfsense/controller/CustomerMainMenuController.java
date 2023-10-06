package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class CustomerMainMenuController {

    @FXML
    void btnViewAccountClicked(ActionEvent event) {

    }

    @FXML
    void btnLibraryClicked(ActionEvent event) {

    }

    @FXML
    void btnBackClicked(ActionEvent event) {

        JavaFXUtils.showPreviousScene((Node) event.getSource());

    }

}
