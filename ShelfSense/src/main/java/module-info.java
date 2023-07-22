module com.shelfsense.shelfsense {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.shelfsense.shelfsense to javafx.fxml;
    exports com.shelfsense.shelfsense;
}