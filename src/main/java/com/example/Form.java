package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Form {

    static Stage form = new Stage();

    @FXML TextArea formnama;
    @FXML TextArea formkelas;
    @FXML TextField formprofil;
    @FXML TextField formkes;

    @FXML Button formsubmit;

    static String studentID;
    static String[] studentInfo;
    boolean newStudent = true;

    @FXML
    public void initialize() {
        if(studentInfo != null) {
            String nama = studentInfo[1];
            if(nama.contains("|")) {
                 nama = nama.replace("|", "\n");
            }
            formnama.setText(nama);
            String kelas = studentInfo[2];
            if(kelas.contains("|")) {
                kelas = kelas.replace("|", "\n");
            }
            formkelas.setText(kelas);
            formprofil.setText(studentInfo[3]);
            formkes.setText(studentInfo[4]);

            formsubmit.setText("Edit Student");

            newStudent = false;
        }
    }

    @FXML
    private void cancel() {
        form.close();
        studentID = null;
        studentInfo = null;
    }

    @FXML
    private void submit() {
        if(newStudent) {
            studentInfo = new String[6];
            studentInfo[5] = "0";
        }

        studentInfo[1] = formnama.getText();
        if(studentInfo[1].contains("\n")) {
            studentInfo[1] = studentInfo[1].replace("\n", "|");
        }
        studentInfo[2] = formkelas.getText();
        if(studentInfo[2].contains("\n")) {
            studentInfo[2] = studentInfo[2].replace("\n", "|");
        }
        studentInfo[3] = formprofil.getText();
        studentInfo[4] = formkes.getText();

        if(newStudent) {
            form.close();
            FileControl.addStudent(studentInfo);
        } else {
            form.close();
            FileControl.editStudent(studentInfo, studentID, true);
        }

        studentID = null;
        studentInfo = null;

        App.reloadHome();
    }
}