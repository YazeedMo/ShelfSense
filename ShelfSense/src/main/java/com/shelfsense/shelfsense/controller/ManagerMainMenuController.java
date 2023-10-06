package com.shelfsense.shelfsense.controller;

import com.shelfsense.shelfsense.util.JavaFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class ManagerMainMenuController {

    @FXML
    void btnReportsClicked(ActionEvent event) {

    }

    @FXML
    void btnManageEmployeesClicked(ActionEvent event) {
        JavaFXUtils.showNextScene((Node) event.getSource(), JavaFXUtils.FXMLPaths.MANAGE_LIBRARIANS.getPath());
    }

    @FXML
    void btnManageBooksClicked(ActionEvent event) {
        JavaFXUtils.showNextScene((Node) event.getSource(), JavaFXUtils.FXMLPaths.MANAGE_BOOKS.getPath());
    }

    @FXML
    void btnManageCustomersClicked(ActionEvent event) {

    }

    @FXML
    void btnManageLoansClicked(ActionEvent event) {

    }

    @FXML
    void btnBackClicked(ActionEvent event) {

        JavaFXUtils.showPreviousScene((Node) event.getSource());

    }


}
