module com.shelfsense.shelfsense {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.shelfsense.shelfsense to javafx.fxml;
    exports com.shelfsense.shelfsense.controller;
    opens com.shelfsense.shelfsense.controller to javafx.fxml;
    exports com.shelfsense.shelfsense.model;
    opens com.shelfsense.shelfsense.model to javafx.fxml;
    exports com.shelfsense.shelfsense;
}