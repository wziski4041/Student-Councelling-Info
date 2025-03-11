package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage home;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setResizable(false);
        home = stage;

        scene = new Scene(loadFXML("home") , 440, 580);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Data Kaunseling");
    }

    // Reloads the whole home.fxml file, refreshing the table
    public static void reloadHome() {
        try {
            scene.setRoot(loadFXML("home"));
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Failed to reload home. Table data not refreshed.");
            alert.setContentText("如果这个跑出来很多次，找Khing解决");
            alert.show();
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}