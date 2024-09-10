package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class HomeController {

    @FXML
    private void switchToForm() throws IOException {
        App.setRoot("form");
    }

    @FXML
    private void switchToStudentList() throws IOException {
        App.setRoot("studentlist");
    }
}
