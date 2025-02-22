package com.example;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class Session {
    
    public static VBox allSessions;

    public static String studentID;

    private String ID;
    private String curClass;
    private String Date;
    private String Time;
    private String Description;

    private GridPane card = new GridPane();
    private Label sessionID;

    private Label curclass;
    private TextField curClassForm;
    private Label curClassLabel;

    private Label date;
    private DatePicker dateForm;
    private Label dateLabel;

    private Label time;
    private TextField timeForm;
    private Label timeLabel;

    private Label description;
    private TextArea descriptionForm;
    private Label descriptionLabel;

    private Button saveOrEdit;
    private Button delete;

    boolean newSession;

    // New Session
    public Session(int ID){
        newSession = true;

        card.getStyleClass().add("card");
        card.setHgap(10);
        card.setVgap(10);
        card.setMinWidth(410);
        card.setMaxWidth(410);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.setBackground(new Background(new BackgroundFill(Paint.valueOf("fff"), new CornerRadii(20), null)));

        sessionID = new Label(String.valueOf(ID));
        sessionID.setMinWidth(15);
        sessionID.setMaxWidth(15);
        card.add(sessionID, 0, 0);

        curclass = new Label("Class");
        curclass.setMinWidth(70);
        curclass.setMaxWidth(70);
        card.add(curclass, 1, 1);
        curClassForm = new TextField();
        curClassForm.setMinWidth(200);
        curClassForm.setMaxWidth(250);
        curClassForm.setVisible(true);
        card.add(curClassForm, 2, 1, 5, 1);
        curClassLabel = new Label();
        curClassLabel.setMinWidth(200);
        curClassLabel.setMaxWidth(200);
        curClassLabel.setVisible(false);
        card.add(curClassLabel, 2, 1, 5, 1);

        date = new Label("Date");
        date.setMinWidth(70);
        date.setMaxWidth(70);
        card.add(date, 1, 2);
        dateForm = new DatePicker();
        dateForm.setValue(LocalDate.now());
        dateForm.setMinWidth(120);
        dateForm.setMaxWidth(120);
        dateForm.setVisible(true);
        card.add(dateForm, 2, 2);
        dateLabel = new Label();
        dateLabel.setMinWidth(120);
        dateLabel.setMaxWidth(120);
        curClassLabel.setVisible(false);
        card.add(dateLabel, 2, 2);

        time = new Label("Time");
        time.setMinWidth(30);
        time.setMaxWidth(30);
        card.add(time, 3, 2);
        timeForm = new TextField();
        timeForm.setMinWidth(80);
        timeForm.setMaxWidth(80);
        timeForm.setVisible(true);
        card.add(timeForm, 4, 2, 3, 1);
        timeLabel = new Label();
        timeLabel.setMinWidth(80);
        timeLabel.setMaxWidth(80);
        timeLabel.setVisible(false);
        card.add(timeLabel, 4, 2, 3, 1);

        description = new Label("Description");
        description.setMinWidth(70);
        description.setMaxWidth(70);
        card.add(description, 1, 3);
        descriptionForm = new TextArea();
        descriptionForm.setWrapText(true);
        descriptionForm.setMinWidth(200);
        descriptionForm.setMaxWidth(250);
        descriptionForm.setMinHeight(100);
        descriptionForm.setMaxHeight(100);
        descriptionForm.setVisible(true);
        card.add(descriptionForm, 2, 3, 5, 1);
        descriptionLabel = new Label();
        descriptionLabel.setMinWidth(200);
        descriptionLabel.setMaxWidth(200);
        descriptionLabel.setMinHeight(100);
        descriptionLabel.setMaxHeight(100);
        descriptionLabel.setVisible(false);
        card.add(descriptionLabel, 2, 3, 5, 1);

        saveOrEdit = new Button("✓");
        saveOrEdit.setMinSize(30, 30);
        saveOrEdit.setMaxSize(30, 30);
        saveOrEdit.setOnAction(e -> {
            buttonHandler();
        });
        card.add(saveOrEdit, 5, 0);

        delete = new Button("🗑");
        delete.setMinSize(30, 30);
        delete.setMaxSize(30, 30);
        delete.setOnAction(e -> {
            allSessions.getChildren().remove(card);
        });
        card.add(delete, 6, 0);

        allSessions.getChildren().add(card);
    }
    
    // Session exist
    public Session(String ID, String curClass, String Date, String Time, String Description){
        newSession = false;

        this.ID = ID;
        this.curClass = curClass;
        this.Date = Date;
        this.Time = Time;
        this.Description = Description;

        card.getStyleClass().add("card");
        card.setHgap(10);
        card.setVgap(10);
        card.setMinWidth(410);
        card.setMaxWidth(410);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(15));
        card.setBackground(new Background(new BackgroundFill(Paint.valueOf("fff"), new CornerRadii(20), null)));

        sessionID = new Label(ID);
        sessionID.setMinWidth(15);
        sessionID.setMaxWidth(15);
        card.add(sessionID, 0, 0);

        curclass = new Label("Class");
        curclass.setMinWidth(70);
        curclass.setMaxWidth(70);
        card.add(curclass, 1, 1);
        curClassForm = new TextField(curClass);
        curClassForm.setMinWidth(200);
        curClassForm.setMaxWidth(250);
        curClassForm.setVisible(false);
        card.add(curClassForm, 2, 1, 5, 1);
        curClassLabel = new Label(curClass);
        curClassLabel.setMinWidth(200);
        curClassLabel.setMaxWidth(200);
        curClassLabel.setVisible(true);
        card.add(curClassLabel, 2, 1, 5, 1);

        date = new Label("Date");
        date.setMinWidth(70);
        date.setMaxWidth(70);
        card.add(date, 1, 2);
        dateForm = new DatePicker();
        dateForm.setValue(LocalDate.parse(Date));
        dateForm.setMinWidth(120);
        dateForm.setMaxWidth(120);
        dateForm.setVisible(false);
        card.add(dateForm, 2, 2);
        dateLabel = new Label(Date);
        dateLabel.setMinWidth(120);
        dateLabel.setMaxWidth(120);
        curClassLabel.setVisible(true);
        card.add(dateLabel, 2, 2);

        time = new Label("Time");
        time.setMinWidth(30);
        time.setMaxWidth(30);
        card.add(time, 3, 2);
        timeForm = new TextField(Time);
        timeForm.setMinWidth(80);
        timeForm.setMaxWidth(80);
        timeForm.setVisible(false);
        card.add(timeForm, 4, 2, 3, 1);
        timeLabel = new Label(Time);
        timeLabel.setMinWidth(80);
        timeLabel.setMaxWidth(80);
        timeLabel.setVisible(true);
        card.add(timeLabel, 4, 2, 3, 1);

        description = new Label("Description");
        description.setMinWidth(70);
        description.setMaxWidth(70);
        card.add(description, 1, 3);
        descriptionForm = new TextArea(Description);
        descriptionForm.setWrapText(false);
        descriptionForm.setMinWidth(200);
        descriptionForm.setMaxWidth(250);
        descriptionForm.setMinHeight(100);
        descriptionForm.setMaxHeight(100);
        descriptionForm.setVisible(false);
        card.add(descriptionForm, 2, 3, 5, 1);
        descriptionLabel = new Label(Description);
        descriptionLabel.setMinWidth(200);
        descriptionLabel.setMaxWidth(200);
        descriptionLabel.setMinHeight(100);
        descriptionLabel.setMaxHeight(100);
        descriptionLabel.setVisible(true);
        card.add(descriptionLabel, 2, 3, 5, 1);

        saveOrEdit = new Button("🖊");
        saveOrEdit.setMinSize(30, 30);
        saveOrEdit.setMaxSize(30, 30);
        saveOrEdit.setOnAction(e -> {
            buttonHandler();
        });
        card.add(saveOrEdit, 5, 0);

        delete = new Button("🗑");
        delete.setMinSize(30, 30);
        delete.setMaxSize(30, 30);
        delete.setOnAction(e -> {
            allSessions.getChildren().remove(card);
        });
        card.add(delete, 6, 0);

        allSessions.getChildren().add(card);
    }

    private void buttonHandler(){
        if(saveOrEdit.getText().equals("✓")){      // Save
            setCurClass(curClassForm.getText());
            setDate(dateForm.getValue().toString());
            setTime(timeForm.getText());
            setDescription(descriptionForm.getText());

            String[] session = {getID(), studentID, getCurClass(), getDate(), getTime(), getDescription()};

            curClassLabel.setText(getCurClass());
            dateLabel.setText(getDate());
            timeLabel.setText(getTime());
            descriptionLabel.setText(getDescription());

            curClassForm.setVisible(false);
            dateForm.setVisible(false);
            timeForm.setVisible(false);
            descriptionForm.setVisible(false);

            curClassLabel.setVisible(true);
            dateLabel.setVisible(true);
            timeLabel.setVisible(true);
            descriptionLabel.setVisible(true);

            saveOrEdit.setText("🖊");

            if(newSession){
                Details.sessions.add(session);
                FileControl.addSession(session);
            }else{
                FileControl.editSession(session, ID);
            }
        }else{                                              // Edit
            curClassLabel.setVisible(false);
            dateLabel.setVisible(false);
            timeLabel.setVisible(false);
            descriptionLabel.setVisible(false);

            curClassForm.setVisible(true);
            dateForm.setVisible(true);
            timeForm.setVisible(true);
            descriptionForm.setVisible(true);

            saveOrEdit.setText("✓");
        }
    }
    
    public void editSession(){
        String studentID = "";   // Get student ID from selected student
        
        
        String id = "";          // Get session ID from button pressed
        Form.sessionID = id;

        ArrayList<String[]> sessions = FileControl.readSessionList(studentID);
        for (String[] session : sessions) {
            if (session[0].equals(id)) {
                Form.sessionInfo = session;
                break;
            }
        }

        Form.sessionForm = true;

        // try {
        //     Scene formScene = new Scene(App.loadFXML("sessionform"), 350, 250);

        //     form.setScene(formScene);
        //     form.show();
        //     form.setTitle("Edit Session Info");
        //     form.setAlwaysOnTop(true);

        //     Form.form = form;
        // } catch (IOException e) {
        //     System.out.println("Error: Failed to load session form.");
        // }
    }
    
    public String getID(){
        return ID;
    }
    public void setID(String ID_){
        ID = ID_;
    }

    public String getCurClass(){
        return curClass;
    }
    public void setCurClass(String curClass_){
        curClass = curClass_;
    }

    public String getDate(){
        return Date;
    }
    public void setDate(String Date_){
        Date = Date_;
    }
    
    public String getTime(){
        return Time;
    }
    public void setTime(String Time_){
        Time = Time_;
    }

    public String getDescription(){
        return Description;
    }
    public void setDescription(String Description_){
        Description = Description_;
    }
}
