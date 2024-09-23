package com.example;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class StudentController {

    @FXML
    private void backToHome() throws IOException {
        App.setRoot("home");
    }

    @FXML private GridPane studentinfo;

    public static int idx = 0;
    
    @FXML
    public void initialize(){
        Label studentname = new Label("Name: " + App.fullList.get(idx)[0]);
        Label studentclass = new Label("Class: " + App.fullList.get(idx)[1]);
        Label datetime = new Label("Date & Time: " + App.fullList.get(idx)[2]);
        Label problem = new Label("Problem: " + App.fullList.get(idx)[3]);

        studentname.setId("info");
        studentclass.setId("info");
        datetime.setId("info");
        problem.setId("info");

        studentinfo.getChildren().addAll(studentname, studentclass, datetime, problem);

        GridPane.setRowIndex(studentname, 0);
        GridPane.setRowIndex(studentclass, 1);
        GridPane.setRowIndex(datetime, 2);
        GridPane.setRowIndex(problem, 3);
    }
}