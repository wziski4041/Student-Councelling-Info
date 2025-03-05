package com.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Home {

    @FXML TableView<Student> table;
    @FXML TextField filter;

    Stage form = new Stage();
    Stage details = new Stage();
    static Scene detailsScene;

    @FXML
    private void initialize(){
        loadTable();

        table.setRowFactory( tv -> {
            TableRow<Student> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    toStudentDetails();
                }
            });
            return row ;
        });

        filter.setOnKeyTyped(e -> {
            String text = filter.getText();
            ObservableList<Student> list = table.getItems();
            list.clear();

            ArrayList<String[]> students = FileControl.readStudentList();
            for (int i = 1; i < students.size(); i++){
                if(students.get(i)[1].contains(text) || students.get(i)[2].contains(text) || students.get(i)[3].contains(text) || students.get(i)[6].contains(text)){
                    list.add(new Student(students.get(i)[0], students.get(i)[1], students.get(i)[2], students.get(i)[3], students.get(i)[6]));
                }
            }
        });
    }

    private void loadTable(){
        ObservableList<Student> list = table.getItems();
        list.clear();

        ArrayList<String[]> students = FileControl.readStudentList();
        for (int i = 1; i < students.size(); i++){
            list.add(new Student(students.get(i)[0], students.get(i)[1], students.get(i)[2], students.get(i)[3], students.get(i)[6]));
        }
    }

    @FXML
    private void toAddStudentForm(){
        try {
            Scene formScene = new Scene(App.loadFXML("studentform"), 350, 300);
            
            form.setScene(formScene);
            form.show();
            form.setTitle("Student Form");
            form.setAlwaysOnTop(true);

            Form.studentForm = true;
            Form.form = form;
        } catch (IOException e) {
            System.out.println("Error: Failed to load student form.");
        }
    }

    @FXML
    private void toEditStudentForm(){
        ObservableList<Student> selected = table.getSelectionModel().getSelectedItems();
        if(selected.size() == 0){
            System.out.println("No student selected");
            return;
        }
        
        String id = selected.get(0).getId();
        Form.studentID = id;

        ArrayList<String[]> students = FileControl.readStudentList();
        for (String[] student : students) {
            if (student[0].equals(id)) {
                Form.studentInfo = student;
                break;
            }
        }

        Form.studentForm = true;

        try {
            Scene formScene = new Scene(App.loadFXML("studentform"), 350, 300);

            form.setScene(formScene);
            form.show();
            form.setTitle("Edit Student Info");
            form.setAlwaysOnTop(true);

            Form.form = form;
        } catch (IOException e) {
            System.out.println("Error: Failed to load student form.");
        }
    }

    @FXML
    private void deleteStudent(){
        ObservableList<Student> selected = table.getSelectionModel().getSelectedItems();
        if(selected.size() == 0){
            System.out.println("No student selected");
            return;
        }
        
        String id = selected.get(0).getId();

        FileControl.editStudent(null, id, true);
        FileControl.deleteAllSessions(id);

        App.reloadHome();
    }

    @FXML
    private void toStudentDetails(){
        ObservableList<Student> selected = table.getSelectionModel().getSelectedItems();
        if(selected.size() == 0){
            System.out.println("No student selected");
            return;
        }
        
        String id = selected.get(0).getId();

        ArrayList<String[]> students = FileControl.readStudentList();
        for (String[] student : students) {
            if (student[0].equals(id)) {
                Details.studentInfo = student;
                break;
            }
        }

        try {
            detailsScene = new Scene(App.loadFXML("studentdetails"), 500, 700);
        } catch (IOException e) {
            System.out.println("Error: Failed to load student details.");
        }
        
        details.setScene(detailsScene);
        details.show();
        details.setTitle("Student Details");
        details.setOnCloseRequest(e -> {
            Details.save();
        });
    }

    public void reloadDetails(){
        try {
            Home.detailsScene.setRoot(App.loadFXML("studentdetails"));
        } catch (IOException e) {
            System.out.println("Error: Failed to reload student details.");
        }
    }

    @FXML
    public void submit(){

    }

    @FXML
    public void listoutStudents(){

    }
    
    @FXML
    public void filter(){

    }
}
