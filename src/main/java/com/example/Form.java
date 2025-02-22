package com.example;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Form {

    static Stage form = new Stage();

    @FXML TextField formname;
    @FXML TextField formage;
    @FXML TextField formclass;
    @FXML TextField formyearstart;
    @FXML TextField formyearend;

    @FXML Button formsubmit;

    @FXML DatePicker formdate;
    @FXML TextField formtime;
    @FXML TextArea formdescription;

    static String studentID, sessionID;
    static String[] studentInfo, sessionInfo;
    static boolean studentForm = false, sessionForm = false;
    boolean newStudent = true, newSession = true;

    @FXML
    public void initialize() {
        if(sessionForm && sessionInfo != null) {
            formclass.setText(sessionInfo[1]);
            formdate.setValue(LocalDate.parse(sessionInfo[2]));
            formtime.setText(sessionInfo[3]);
            formdescription.setText(sessionInfo[4]);

            formsubmit.setText("Edit Session");

            newSession = false;
        }else if(studentForm && studentInfo != null) {
            formname.setText(studentInfo[1]);
            formage.setText(studentInfo[2]);
            formclass.setText(studentInfo[3]);
            formyearstart.setText(studentInfo[4]);
            formyearend.setText(studentInfo[5]);

            formsubmit.setText("Edit Student");

            newStudent = false;
        }
    }

    @FXML
    private void cancel() {
        form.close();
        studentID = null;
        sessionID = null;
        studentInfo = null;
        sessionInfo = null;
        studentForm = false;
        sessionForm = false;
    }

    @FXML
    private void submit() {
        if(sessionForm) {
            if(newSession) {
                sessionInfo = new String[5];
            }

            sessionInfo[1] = formclass.getText();
            sessionInfo[2] = formdate.getValue().toString();
            sessionInfo[3] = formtime.getText();
            sessionInfo[4] = formdescription.getText();
        }else if(studentForm) {
            if(newStudent) {
                studentInfo = new String[7];
                studentInfo[6] = "0";
            }

            studentInfo[1] = formname.getText();
            studentInfo[2] = formage.getText();
            studentInfo[3] = formclass.getText();
            studentInfo[4] = formyearstart.getText();
            studentInfo[5] = formyearend.getText();
        }

        if(studentForm && newStudent) {
            form.close();
            FileControl.addStudent(studentInfo);
        } else if(studentForm && !newStudent) {
            form.close();
            FileControl.editStudent(studentInfo, studentID);
        }else if(sessionForm && newSession) {
            form.close();
            FileControl.addSession(sessionInfo);
        } else if(sessionForm && !newSession) {
            form.close();
            FileControl.editSession(sessionInfo, studentID);
        }

        studentID = null;
        sessionID = null;
        studentInfo = null;
        sessionInfo = null;
        studentForm = false;
        sessionForm = false;

        App.reloadHome();
    }
}