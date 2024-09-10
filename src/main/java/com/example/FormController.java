package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FormController {

    @FXML
    private void backToHome() throws IOException {
        App.setRoot("home");
    }

    
    @FXML private Button formdatetime;
    @FXML private TextField formdatetimeTF;

    @FXML
    private void currDate() throws IOException {
        LocalDateTime datetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy, hh:mm:ss a");
        String datetimeText = datetime.format(formatter);
        this.formdatetimeTF.setText(datetimeText);
    }

    public Button getformdate() {
        return formdatetime;
    }

    @FXML
    public void setformdate(String text) {
        this.formdatetime.setText(text);
    }
}