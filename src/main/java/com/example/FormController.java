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
    @FXML private TextField formname;
    @FXML private TextField formclass;
    @FXML private TextField formdatetimeTF;
    @FXML private TextField formproblem;

    @FXML
    private void currDate() throws IOException {
        LocalDateTime datetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy, hh:mm:ss a");
        String datetimeText = datetime.format(formatter);
        this.formdatetimeTF.setText(datetimeText);
    }

    @FXML
    private void initialize() {
        LocalDateTime datetime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy, hh:mm:ss a");
        String datetimeText = datetime.format(formatter);
        this.formdatetimeTF.setText(datetimeText);
    }

    @FXML
    private void submit(){
        String name = formname.getText();
        String classofstudent = formclass.getText();
        String datetime = formdatetimeTF.getText();
        String problem = formproblem.getText();

        App.createFile();

        String studentInfo = name + "|" + classofstudent + "|" + datetime + "|" + problem;
        App.writeToFile(studentInfo);
        System.out.println(name + " " + classofstudent + " " + datetime + " " + problem);
    }

    
}