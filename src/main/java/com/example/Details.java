package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Details {

    Stage form = new Stage();

    @FXML Label detailsname;
    @FXML Label detailsage;
    @FXML Label detailsclass;
    @FXML Label detailsstartschool;
    @FXML Label detailsendschool;
    @FXML Label detailsnumSessions;

    @FXML VBox detailsallSessions;
    
    static String id;
    static String[] studentInfo;
    static List<String[]> sessions;
    static List<Session> sessionCards = new ArrayList<>();

    // List<GridPane> cards = new ArrayList<>();
    
    @FXML
    private void initialize(){
        detailsname.setText(studentInfo[1]);
        detailsage.setText(studentInfo[2]);
        detailsclass.setText(studentInfo[3]);
        detailsstartschool.setText(studentInfo[4]);
        detailsendschool.setText(studentInfo[5]);
        detailsnumSessions.setText(studentInfo[6]);

        Session.studentID = id;
        Session.allSessions = detailsallSessions;
        for(int i = 1; i < sessions.size(); i++){
            sessionCards.add(new Session(sessions.get(i)[0], sessions.get(i)[2], sessions.get(i)[3], sessions.get(i)[4], sessions.get(i)[5]));
        }


    }

    @FXML
    private void addSessionCard(){
        Session.allSessions = detailsallSessions;
        sessionCards.add(new Session(sessions.size()));
    }

    @FXML
    private void printStudentDetails(){

    }

    public static void save(){

    }

    // @FXML
    // public void toAddSessionForm(){
    //     try {
    //         Scene formScene = new Scene(App.loadFXML("sessionform"), 350, 250);
            
    //         form.setScene(formScene);
    //         form.show();
    //         form.setTitle("Session Form");
    //         form.setAlwaysOnTop(true);

    //         Form.sessionForm = true;
    //         Form.form = form;
    //     } catch (IOException e) {
    //         System.out.println("Error: Failed to load session form.");
    //     }
    // }

    // @FXML
    // private void createCard(){
    //     GridPane card = new GridPane();
    //     card.getStyleClass().add("card");
    //     card.setHgap(10);
    //     card.setVgap(10);
    //     card.setMinWidth(410);
    //     card.setMaxWidth(410);
    //     card.setAlignment(Pos.CENTER);
    //     card.setPadding(new Insets(15));
    //     card.setBackground(new Background(new BackgroundFill(Paint.valueOf("fff"), new CornerRadii(20), null)));

    //     Label sessionID = new Label("ID");
    //     sessionID.setMinWidth(15);
    //     sessionID.setMaxWidth(15);
    //     card.add(sessionID, 0, 0);

    //     Label curclass = new Label("Class");
    //     curclass.setMinWidth(70);
    //     curclass.setMaxWidth(70);
    //     card.add(curclass, 1, 1);
    //     TextField curClassForm = new TextField();
    //     curClassForm.setMinWidth(200);
    //     curClassForm.setMaxWidth(250);
    //     card.add(curClassForm, 2, 1, 5, 1);

    //     Label date = new Label("Date");
    //     date.setMinWidth(70);
    //     date.setMaxWidth(70);
    //     card.add(date, 1, 2);
    //     DatePicker dateForm = new DatePicker();
    //     dateForm.setMinWidth(120);
    //     dateForm.setMaxWidth(120);
    //     card.add(dateForm, 2, 2);

    //     Label time = new Label("Time");
    //     time.setMinWidth(30);
    //     time.setMaxWidth(30);
    //     card.add(time, 3, 2);
    //     TextField timeForm = new TextField();
    //     timeForm.setMinWidth(80);
    //     timeForm.setMaxWidth(80);
    //     card.add(timeForm, 4, 2, 3, 1);

    //     Label description = new Label("Description");
    //     description.setMinWidth(70);
    //     description.setMaxWidth(70);
    //     card.add(description, 1, 3);
    //     TextArea descriptionForm = new TextArea();
    //     descriptionForm.setMinWidth(200);
    //     descriptionForm.setMaxWidth(250);
    //     descriptionForm.setMinHeight(100);
    //     descriptionForm.setMaxHeight(100);
    //     card.add(descriptionForm, 2, 3, 5, 1);

    //     Button save = new Button("✓");
    //     save.setMinSize(30, 30);
    //     save.setMaxSize(30, 30);
    //     save.setOnAction(e -> {
    //         String curClass = curClassForm.getText();
    //         String dateFilled = dateForm.getValue().toString();
    //         String timeFilled = timeForm.getText();
    //         String descriptionFilled = descriptionForm.getText();

    //         curClassForm.setVisible(false);

    //         Label curClassLabel = new Label(curClass);
    //         curClassLabel.setMinWidth(200);
    //         curClassLabel.setMaxWidth(200);
    //         card.add(curClassLabel, 2, 1, 5, 1);

    //         Label dateLabel = new Label(dateFilled);
    //         dateLabel.setMinWidth(120);
    //         dateLabel.setMaxWidth(120);
    //         card.add(dateLabel, 2, 2);

    //         Label timeLabel = new Label(timeFilled);
    //         timeLabel.setMinWidth(80);
    //         timeLabel.setMaxWidth(80);
    //         card.add(timeLabel, 4, 2, 3, 1);

    //         Label descriptionLabel = new Label(descriptionFilled);
    //         descriptionLabel.setMinWidth(200);
    //         descriptionLabel.setMaxWidth(200);
    //         descriptionLabel.setMinHeight(100);
    //         descriptionLabel.setMaxHeight(100);
    //         card.add(descriptionLabel, 2, 3, 5, 1);
    //     });
    //     card.add(save, 5, 0);

    //     Button delete = new Button("🗑");
    //     delete.setMinSize(30, 30);
    //     delete.setMaxSize(30, 30);
    //     card.add(delete, 6, 0);

    //     detailsallSessions.getChildren().add(card);
    // }
    
    // @FXML
    // public void toEditSessionForm(){
    //     String studentID = "";   // Get student ID from selected student
        
        
    //     String id = "";          // Get session ID from button pressed
    //     Form.sessionID = id;

    //     ArrayList<String[]> sessions = FileControl.readSessionList(studentID);
    //     for (String[] session : sessions) {
    //         if (session[0].equals(id)) {
    //             Form.sessionInfo = session;
    //             break;
    //         }
    //     }

    //     Form.sessionForm = true;

    //     try {
    //         Scene formScene = new Scene(App.loadFXML("sessionform"), 350, 250);

    //         form.setScene(formScene);
    //         form.show();
    //         form.setTitle("Edit Session Info");
    //         form.setAlwaysOnTop(true);

    //         Form.form = form;
    //     } catch (IOException e) {
    //         System.out.println("Error: Failed to load session form.");
    //     }
    // }
}