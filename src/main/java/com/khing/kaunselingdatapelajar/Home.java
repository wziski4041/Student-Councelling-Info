package com.khing.kaunselingdatapelajar;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Home {

    @FXML TableView<Student> table;
    @FXML TextField filter;

    Stage form = new Stage();
    Stage details = new Stage();
    static Scene detailsScene;
    boolean sceneOpened = false;

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
    }

    private void loadTable(){
        ObservableList<Student> list = table.getItems();
        list.clear();

        ArrayList<String[]> students = FileControl.readStudentList();
        for (int i = 1; i < students.size(); i++){
            String studentName_s = students.get(i)[1];
            if(studentName_s.contains("|")){
                studentName_s = studentName_s.replace("|", "\n");
            }
            String studentClass_s = students.get(i)[2];
            if(studentClass_s.contains("|")){
                studentClass_s = studentClass_s.replace("|", "\n");
            }

            list.add(new Student(students.get(i)[0], studentName_s, studentClass_s, students.get(i)[3], students.get(i)[5]));
        }
    }

    @FXML
    private void toAddStudentForm(){
        try {
            Scene formScene = new Scene(App.loadFXML("studentform"), 350, 300);
            
            form.setScene(formScene);
            form.show();
            form.setTitle("Tambah Pelajar");
            form.setAlwaysOnTop(true);

            Form.form = form;
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load student form.\nPlease try again.");
            alert.setContentText("如果这个跑出来很多次, 找Khing解决");
            alert.show();
        }
    }

    @FXML
    private void toEditStudentForm(){
        ObservableList<Student> selected = table.getSelectionModel().getSelectedItems();
        if(selected.size() == 0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Tiada Pelajar dipilih");
            alert.setHeaderText("Sila pilih Pelajar.");
            alert.show();
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

        try {
            Scene formScene = new Scene(App.loadFXML("studentform"), 350, 350);

            form.setScene(formScene);
            form.show();
            form.setTitle("Tukar Info Pelajar");
            form.setAlwaysOnTop(true);

            Form.form = form;
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load student form.\nPlease try again.");
            alert.setContentText("如果这个跑出来很多次, 找Khing解决");
            alert.show();
        }
    }

    @FXML
    private void deleteStudent(){
        ObservableList<Student> selected = table.getSelectionModel().getSelectedItems();
        if(selected.size() == 0){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Tiada Pelajar dipilih");
            alert.setHeaderText("Sila pilih Pelajar.");
            alert.show();
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
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Tiada Pelajar dipilih");
            alert.setHeaderText("Sila pilih Pelajar.");
            alert.show();
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
            detailsScene = new Scene(App.loadFXML("studentdetails"), 500, 800);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to load student details.\nPlease try again.");
            alert.setContentText("如果这个跑出来很多次, 找Khing解决");
            alert.show();
        }
        
        details.setScene(detailsScene);
        details.setResizable(false);
        details.show();
        details.setTitle("Data Kaunseling Student");
    }

    public void reloadDetails(){
        try {
            Home.detailsScene.setRoot(App.loadFXML("studentdetails"));
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to reload student details.\nSession datas not refreshed.");
            alert.setContentText("如果这个跑出来很多次, 找Khing解决");
            alert.show();
        }
    }
    
    @FXML
    public void filter(){
        String text = filter.getText().toLowerCase();
        ObservableList<Student> list = table.getItems();
        list.clear();

        ArrayList<String[]> students = FileControl.readStudentList();
        for (int i = 1; i < students.size(); i++){
            String name = students.get(i)[1];
            String cls = students.get(i)[2];
            if(name.contains("|")){
                name = name.replace("|", "\n");
            }
            if(cls.contains("|")){
                cls = cls.replace("|", "\n");
            }

            text = text.replace("/", "");
            text = text.replace("-", "");
            text = text.replace(":", "");
            text = text.replace(".", "");
    
            ArrayList<String[]> sessions = FileControl.readSessionList(students.get(i)[0]);

            for(int j = 1; j < sessions.size(); j++){
                String date = sessions.get(j)[2];
                String time = sessions.get(j)[3];
            
                date = date.replace("/", "");
                time = time.replace(":", "");
                time = time.replace(".", "");

                if(name.toLowerCase().contains(text) || cls.toLowerCase().contains(text) || students.get(i)[3].toLowerCase().contains(text) || students.get(i)[5].toLowerCase().contains(text) || date.contains(text) || time.contains(text)){
                    list.add(new Student(students.get(i)[0], name, cls, students.get(i)[3], students.get(i)[5]));
                    break;
                }
            }
        }
    }
}
