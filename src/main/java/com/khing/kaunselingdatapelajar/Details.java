package com.khing.kaunselingdatapelajar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;


public class Details {
    Stage form = new Stage();

    @FXML Label detailsname;
    @FXML Label detailsclass;
    @FXML Label detailsprofile;
    @FXML Label detailscase;
    @FXML Label detailsnumSessions;

    @FXML VBox detailsallSessions;
    
    static String[] studentInfo;
    static List<String[]> sessions;                         // Sessions read from file
    static List<Session> sessionCards = new ArrayList<>();  // Sessions displayed

    @FXML
    private void initialize(){
        sessions = FileControl.readSessionList(studentInfo[0]);
        studentInfo[5] = String.valueOf(sessions.size() - 1);               // Save this to file
        FileControl.editStudent(studentInfo, studentInfo[0], false);
        App.reloadHome();

        String studentName_s = studentInfo[1];
        if(studentName_s.contains("|")){
            studentName_s = studentName_s.replace("|", "\n");
            detailsname.setWrapText(true);
        }
        detailsname.setText(studentName_s);
        String studentClass_s = studentInfo[2];
        if(studentClass_s.contains("|")){
            studentClass_s = studentClass_s.replace("|", "\n");
            detailsclass.setWrapText(true);
        }
        detailsclass.setText(studentClass_s);
        detailsprofile.setText(studentInfo[3]);
        detailscase.setText(studentInfo[4]);
        detailsnumSessions.setText(studentInfo[5]);

        Session.studentInfo = studentInfo;
        Session.allSessions = detailsallSessions;
        for(int i = 1; i < sessions.size(); i++){
            sessionCards.add(new Session(sessions.get(i)[0], sessions.get(i)[2], sessions.get(i)[3], sessions.get(i)[4]));
        }
    }

    @FXML
    private void addSessionCard(){
        Session.allSessions = detailsallSessions;
        sessionCards.add(new Session(sessions.size()));
        studentInfo[5] = String.valueOf(sessionCards.size());
    }

    @FXML
    private void printStudentDetails(){
        try (XWPFDocument doc = new XWPFDocument()) {
            
            XWPFParagraph title = doc.createParagraph();

            XWPFRun run = title.createRun();
            title.setAlignment(ParagraphAlignment.CENTER);
            run.setFontSize(20);
            run.setBold(true);
            run.setText("Student Details");

            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun studentInfoRun = paragraph.createRun();
            studentInfoRun.setFontSize(12);

            String name = studentInfo[1];
            if(name.contains("|")){
                name = name.replace("|", ", ");
            }
            studentInfoRun.setText("Nama: ");
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.setText(name);
            studentInfoRun.addBreak();

            String cls = studentInfo[2];
            if(cls.contains("|")){
                cls = cls.replace("|", ", ");
            }
            studentInfoRun.setText("Kelas: ");
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.setText(cls);
            studentInfoRun.addBreak();

            studentInfoRun.setText("Profil Pelajar: ");
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.setText(studentInfo[3]);
            studentInfoRun.addBreak();

            studentInfoRun.setText("Kes: ");
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.setText(studentInfo[4]);
            studentInfoRun.addBreak();

            studentInfoRun.setText("Bilangan Sesi: ");
            studentInfoRun.addTab();
            studentInfoRun.addTab();
            studentInfoRun.setText(studentInfo[5]);
            studentInfoRun.addBreak();

            studentInfoRun.setText("All Sessions:- ");

            XWPFTable sessionTable = doc.createTable();
            XWPFTableRow tableRowOne = sessionTable.getRow(0);
            tableRowOne.getCell(0).setText("Tarikh");
            tableRowOne.getCell(0).setWidth("3000");
            tableRowOne.addNewTableCell().setText("Masa");
            tableRowOne.getCell(1).setWidth("3000");
            tableRowOne.addNewTableCell().setText("Tujuan Kaunseling");
            tableRowOne.getCell(2).setWidth("15000");

            for (int i = 1; i < sessions.size(); i++) {
                XWPFTableRow tableRow = sessionTable.createRow();
                tableRow.getCell(0).setText(sessions.get(i)[2]);
                tableRow.getCell(1).setText(sessions.get(i)[3]);
                String des = sessions.get(i)[4];
                if(des.contains("|")){
                    des = des.replace("|", " ");
                }
                tableRow.getCell(2).setText(des);
            }

            //Creating a File chooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save");
            if(name.contains(", ")){
                name = name.replace(", ", "_");
            }
            fileChooser.setInitialFileName(name + ".docx");
            String home = System.getProperty("user.home");
            fileChooser.setInitialDirectory(new File(home, "Downloads"));
            fileChooser.getExtensionFilters().add(new ExtensionFilter("Word Document", "*.docx"));

            //Opening a dialog box
            Stage saveDialog = new Stage();
            OutputStream os = new FileOutputStream(fileChooser.showSaveDialog(saveDialog));
            doc.write(os);
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Successfully Saved Sessions Data");
            alert.setHeaderText("Data written successfully to word document.");
            
            doc.close();
        } catch (IOException e1) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to write as word document.");
        }
    }
}