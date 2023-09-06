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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Test {

    public static void main(String[] args) {


        LocalDate date = LocalDate.parse("2023-09-06");
        String formattedDate = date.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"));
        System.out.println(formattedDate);


    }

}
