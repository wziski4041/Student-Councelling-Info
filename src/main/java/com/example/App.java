package com.example;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        readFile();
        scene = new Scene(loadFXML("home") , 455, 480);
        scene.getStylesheets().add(getClass().getResource("test.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }


    private static File list = new File("studentlist.txt");

    public static void createFile(){
        try {
            if (list.createNewFile())
            System.out.println("File created: " + list.getName());
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String studentInfo){
        try {
            Scanner scan = new Scanner(list);
            String data = "";
            while (scan.hasNextLine()) {
                data += scan.nextLine() + "\n";
            }
            scan.close();

            FileWriter write = new FileWriter("studentlist.txt");
            write.write(data + studentInfo);
            write.flush();
            write.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static ArrayList<String []> fullList = new ArrayList<>();

    public static void readFile(){
        ArrayList<String> output = new ArrayList<>(); 
        try {
            Scanner scan = new Scanner(list);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                fullList.add(line.split("[|]"));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }

}