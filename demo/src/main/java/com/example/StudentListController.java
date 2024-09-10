package com.example;

import java.io.IOException;
import javafx.fxml.FXML;

public class StudentListController {

    @FXML
    private void backToHome() throws IOException {
        App.setRoot("home");
    }
}