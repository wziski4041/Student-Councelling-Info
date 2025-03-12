package com.khing.kaunselingdatapelajar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

public class Session {
    
    public static VBox allSessions;

    public static String[] studentInfo;

    private String ID;
    private String Date;
    private String Time;
    private String Description;

    private GridPane card = new GridPane();
    private Label sessionID;

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

        date = new Label("Tarikh");
        date.setMinWidth(100);
        date.setMaxWidth(100);
        card.add(date, 1, 1);
        dateForm = new DatePicker();
        dateForm.setValue(LocalDate.now());
        dateForm.setMinWidth(150);
        dateForm.setMaxWidth(150);
        dateForm.setVisible(true);
        card.add(dateForm, 2, 1);
        dateLabel = new Label();
        dateLabel.setMinWidth(150);
        dateLabel.setMaxWidth(150);
        dateLabel.setVisible(false);
        card.add(dateLabel, 2, 1);

        time = new Label("Masa");
        time.setMinWidth(100);
        time.setMaxWidth(100);
        card.add(time, 1, 2);
        timeForm = new TextField();
        timeForm.setMinWidth(150);
        timeForm.setMaxWidth(150);
        timeForm.setVisible(true);
        card.add(timeForm, 2, 2);
        timeLabel = new Label();
        timeLabel.setMinWidth(150);
        timeLabel.setMaxWidth(150);
        timeLabel.setVisible(false);
        card.add(timeLabel, 2, 2);

        description = new Label("Tujuan Kaunseling");
        description.setMinWidth(100);
        description.setMaxWidth(100);
        card.add(description, 1, 3);
        descriptionForm = new TextArea();
        descriptionForm.setWrapText(false);
        descriptionForm.setMinWidth(235);
        descriptionForm.setMaxWidth(235);
        descriptionForm.setMinHeight(100);
        descriptionForm.setMaxHeight(100);
        descriptionForm.setVisible(true);
        card.add(descriptionForm, 2, 3, 3, 1);
        descriptionLabel = new Label();
        descriptionLabel.setMinWidth(235);
        descriptionLabel.setMaxWidth(235);
        descriptionLabel.setMinHeight(100);
        descriptionLabel.setMaxHeight(100);
        descriptionLabel.setVisible(false);
        card.add(descriptionLabel, 2, 3, 3, 1);

        saveOrEdit = new Button("✓");
        saveOrEdit.setMinSize(30, 30);
        saveOrEdit.setMaxSize(30, 30);
        saveOrEdit.setOnAction(e -> {
            buttonHandler();
        });
        card.add(saveOrEdit, 3, 0);

        delete = new Button("🗑");
        delete.setMinSize(30, 30);
        delete.setMaxSize(30, 30);
        delete.setOnAction(e -> {
            delete();
        });
        card.add(delete, 4, 0);

        allSessions.getChildren().add(card);
    }
    
    // Session exist
    public Session(String ID, String Date, String Time, String Description){
        newSession = false;

        this.ID = ID;
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

        date = new Label("Tarikh");
        date.setMinWidth(100);
        date.setMaxWidth(100);
        card.add(date, 1, 1);
        dateForm = new DatePicker();
        dateForm.setValue(LocalDate.parse(Date, DateTimeFormatter.ofPattern("dd/MM/uuuu")));
        dateForm.setMinWidth(150);
        dateForm.setMaxWidth(150);
        dateForm.setVisible(false);
        card.add(dateForm, 2, 1);
        dateLabel = new Label(Date);
        dateLabel.setMinWidth(150);
        dateLabel.setMaxWidth(150);
        dateLabel.setVisible(true);
        card.add(dateLabel, 2, 1);

        time = new Label("Masa");
        time.setMinWidth(100);
        time.setMaxWidth(100);
        card.add(time, 1, 2);
        timeForm = new TextField(Time);
        timeForm.setMinWidth(150);
        timeForm.setMaxWidth(150);
        timeForm.setVisible(false);
        card.add(timeForm, 2, 2);
        timeLabel = new Label(Time);
        timeLabel.setMinWidth(150);
        timeLabel.setMaxWidth(150);
        timeLabel.setVisible(true);
        card.add(timeLabel, 2, 2);

        description = new Label("Tujuan Kaunseling");
        description.setMinWidth(100);
        description.setMaxWidth(100);
        card.add(description, 1, 3);
        descriptionForm = new TextArea(Description);
        descriptionForm.setWrapText(false);
        descriptionForm.setMinWidth(235);
        descriptionForm.setMaxWidth(235);
        descriptionForm.setMinHeight(100);
        descriptionForm.setMaxHeight(100);
        descriptionForm.setVisible(false);
        card.add(descriptionForm, 2, 3, 3, 1);
        descriptionLabel = new Label(Description);
        descriptionLabel.setMinWidth(235);
        descriptionLabel.setMaxWidth(235);
        descriptionLabel.setMinHeight(100);
        descriptionLabel.setMaxHeight(100);
        descriptionLabel.setVisible(true);
        card.add(descriptionLabel, 2, 3, 3, 1);

        saveOrEdit = new Button("🖊");
        saveOrEdit.setMinSize(30, 30);
        saveOrEdit.setMaxSize(30, 30);
        saveOrEdit.setOnAction(e -> {
            buttonHandler();
        });
        card.add(saveOrEdit, 3, 0);

        delete = new Button("🗑");
        delete.setMinSize(30, 30);
        delete.setMaxSize(30, 30);
        delete.setOnAction(e -> {
            delete();
        });
        card.add(delete, 4, 0);

        allSessions.getChildren().add(card);
    }

    private void buttonHandler(){
        if(saveOrEdit.getText().equals("✓")){      // Save
            if(timeForm.getText().isEmpty() || descriptionForm.getText().isEmpty()){
                Alert emptyFields = new Alert(AlertType.ERROR);
                emptyFields.setTitle("Error");
                emptyFields.setHeaderText("Empty Fields!\nPlease fill in all fields.");
                emptyFields.showAndWait();
                
                return;
            }
            String date = dateForm.getValue().format(DateTimeFormatter.ofPattern("dd/MM/uuuu"));
            setDate(date);
            setTime(timeForm.getText());
            setDescription(descriptionForm.getText());

            String[] session = {getID(), studentInfo[0], getDate(), getTime(), getDescription()};

            dateLabel.setText(getDate());
            timeLabel.setText(getTime());
            descriptionLabel.setText(getDescription());

            dateForm.setVisible(false);
            timeForm.setVisible(false);
            descriptionForm.setVisible(false);

            dateLabel.setVisible(true);
            timeLabel.setVisible(true);
            descriptionLabel.setVisible(true);

            saveOrEdit.setText("🖊");

            if(newSession){
                newSession = false;
                Details.sessions.add(session);
                FileControl.addSession(session);
            }else{
                FileControl.editSession(session, studentInfo[0], ID);
            }

            Home home = new Home();
            home.reloadDetails();
        }else{                                              // Edit
            dateLabel.setVisible(false);
            timeLabel.setVisible(false);
            descriptionLabel.setVisible(false);

            dateForm.setVisible(true);
            timeForm.setVisible(true);
            descriptionForm.setVisible(true);

            saveOrEdit.setText("✓");
        }
    }

    private void delete(){
        FileControl.editSession(null, studentInfo[0], getID());

        Home home = new Home();
        home.reloadDetails();
    }
    
    public String getID(){
        return ID;
    }
    public void setID(String ID_){
        ID = ID_;
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
