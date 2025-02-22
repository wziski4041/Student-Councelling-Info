package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public static Stage home;

    @Override
    public void start(Stage stage) throws IOException {
        home = stage;

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        scene = new Scene(loadFXML("home") , 440, 580);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Counselling Data");
    }

    public static void reloadHome() {
        try {
            scene.setRoot(loadFXML("home"));
        } catch (IOException e) {
            System.out.println("Error: Failed to reload home.");
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