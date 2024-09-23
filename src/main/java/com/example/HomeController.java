package com.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class HomeController {

    @FXML
    private void switchToForm() throws IOException {
        App.setRoot("form");
    }

    @FXML
    private void switchToStudentList() throws IOException {
        App.setRoot("studentlist");
    }

    @FXML private VBox namelist;

    @FXML
    private void initialize() throws IOException {
        for(int i = 0; i < App.fullList.size(); i++){
            String studentName = App.fullList.get(i)[0];
            int temp = i;

            Button btn = new Button(studentName);
            btn.setPrefHeight(20);
            btn.setPrefWidth(400);
            btn.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent arg0){
                    try {
                        StudentController.idx = temp;
                        App.setRoot("student");
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
            namelist.getChildren().add(btn);
        }
    }
}
