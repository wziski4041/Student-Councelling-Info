package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class FileControl {
    private static final String[] filePath = {"./src/main/resources/studentlist.csv", 
                                              "./src/main/resources/sessions.csv",};

    private static String toCSV(String[] data){
        String csv = "";
        for (int i = 0; i < data.length; i++){
            csv += data[i];
            if (i < data.length - 1){
                csv += ",";
            }
        }
        csv += "\n";
        return csv;
    }

    private static String toCSV(ArrayList<String[]> data){
        StringBuilder csv = new StringBuilder();
        csv.append(toCSV(data.get(0)));
        for (int i = 1; i < data.size(); i++){
            data.get(i)[0] = Integer.toString(i);
            csv.append(toCSV(data.get(i)));
        }
        return csv.toString();
    }

    private static String toCSV(ArrayList<String[]> data, ArrayList<String[]> otherData){
        StringBuilder csv = new StringBuilder();
        csv.append(toCSV(data.get(0)));
        for (int i = 1; i < data.size(); i++){
            data.get(i)[0] = Integer.toString(i);
            csv.append(toCSV(data.get(i)));
        }
        for(int i = 0; i < otherData.size(); i++){
            csv.append(toCSV(otherData.get(i)));
        }
        return csv.toString();
    }

    private static void createFile(String filePath){
        try {
            File file = new File(filePath);
            if (file.createNewFile()){
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists - " + file.getName());
            }
        } catch (IOException e) {
            System.out.println("Error creating new file: " + filePath);
        }
    }

    //Append to file
    private static void writeToFile(String filePath, String data) throws IOException{
        FileWriter write = new FileWriter(filePath, true);
        write.write(data);
        write.flush();
        write.close();
    }

    //Overwrite whole file
    private static void overwriteToFile(String filePath, ArrayList<String[]> data) throws IOException {
        FileWriter write = new FileWriter(filePath, false);
        write.write(toCSV(data));
        write.flush();
        write.close();
    }

    private static void overwriteToFile(String filePath, ArrayList<String[]> data, ArrayList<String[]> otherData) throws IOException {
        FileWriter write = new FileWriter(filePath, false);
        write.write(toCSV(data, otherData));
        write.flush();
        write.close();
    }

    private static ArrayList<String[]> readFile(String filePath){
        ArrayList<String []> fullList = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                fullList.add(line.split(","));
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading file: " + filePath);
        }

        return fullList;
    }




    public static void createStudentList(){
        createFile(filePath[0]);
    }

    public static void createSessionList(){
        createFile(filePath[1]);
    }

    public static void addStudent(String[] studentInfo){
        String path = filePath[0];

        ArrayList<String[]> studentList = readStudentList();
        int studentID = studentList.size();
        studentInfo[0] = Integer.toString(studentID);

        File file = new File(path);
        if(!file.exists()){
            createStudentList();

            try{
                writeToFile(path, "Student ID,Name,Age,Class,Year Start School,Year End School,Number of Session\n");
            }catch(IOException e){}

            studentList = readStudentList();
            studentID = studentList.size();
            studentInfo[0] = Integer.toString(studentID);
        }
        
        try{
            writeToFile(path, toCSV(studentInfo));

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Student Added");
            alert.setHeaderText("Student has been added successfully.\nData saved to file - " + (new File(path)).getAbsolutePath());
            alert.show();
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An Error occurred!\nStudent is not added!\nPlease try again.");
            alert.show();
        }
    }

    public static void editStudent(String[] studentInfo, String studentID, boolean giveAlert){
        String path = filePath[0];

        ArrayList<String[]> studentList = readStudentList();
        for(int i = 1; i < studentList.size(); i++){
            if(studentList.get(i)[0].equals(studentID)){
                if(studentInfo == null){
                    studentList.remove(i);
                    break;
                }
                studentList.set(i, studentInfo);
                break;
            }
        }


        try{
            overwriteToFile(path, studentList);

            if(giveAlert){
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Student Edited");
                alert.setHeaderText("Student info has been editted successfully.\nData saved to file - " + (new File(path)).getAbsolutePath());
                alert.show();
            }
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An Error occurred!\nStudent is not editted!\nPlease try again.");
            alert.show();
        }
        
    }

    public static void addSession(String[] sessionInfo){
        String path = filePath[1];

        ArrayList<String[]> sessionList = readSessionList(sessionInfo[1]);
        int sessionID = sessionList.size();
        sessionInfo[0] = Integer.toString(sessionID);

        File file = new File(path);
        if(!file.exists()){
            createSessionList();

            try{
                writeToFile(path, "Session ID,Student ID,Class,Session Date and Time,Description\n");
            }catch(IOException e){}
        }

        try{
            writeToFile(path, toCSV(sessionInfo));

            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Session Added");
            alert.setHeaderText("Session has been added successfully.\nData saved to file - " + (new File(path)).getAbsolutePath());
            alert.show();
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An Error occurred!\nSession is not added!\nPlease try again.");
            alert.show();
        }
    }

    public static void editSession(String[] sessionInfo, String studentID, String ID){
        String path = filePath[1];

        ArrayList<String[]> sessionList = readSessionList(studentID);
        for(int i = 1; i < sessionList.size(); i++){
            if(sessionList.get(i)[0].equals(ID)){
                if(sessionInfo == null){                        //studentInfo == null to delete session
                    sessionList.remove(i);
                    break;
                }
                sessionList.set(i, sessionInfo);
                break;
            }
        }

        try{
            overwriteToFile(path, sessionList, otherSessionList);
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Session Edited");
            alert.setHeaderText("Session info has been editted successfully.\nData saved to file - " + (new File(path)).getAbsolutePath());
            alert.show();
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An Error occurred!\nSession is not editted!\nPlease try again.");
            alert.show();
        }
    }

    public static void deleteAllSessions(String studentID){
        String path = filePath[1];

        ArrayList<String[]> sessionList = readSessionList(studentID);
        sessionList.subList(1, sessionList.size()).clear();

        for(int i = 0; i < otherSessionList.size(); i++){
            if(Integer.parseInt(otherSessionList.get(i)[1]) > Integer.parseInt(studentID)){
                otherSessionList.get(i)[1] = Integer.toString(Integer.parseInt(otherSessionList.get(i)[1]) - 1);
            }
        }

        try{
            overwriteToFile(path, sessionList, otherSessionList);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error!");
            alert.setHeaderText("An Error occurred!\nSessions of the Student is not editted!\nPlease try again.");
            alert.show();
        }
    }

    public static ArrayList<String[]> readStudentList(){
        String path = filePath[0];

        File file = new File(path);
        if(!file.exists()){
            createStudentList();
            try{
                writeToFile(path, "Student ID,Name,Age,Year Start School,Year End School,Number of Session\n");
            }catch(IOException e){}
        }

        ArrayList<String[]> studentList = readFile(path);
        return studentList;
    }

    public static ArrayList<String[]> readSessionList(){
        String path = filePath[1];

        File file = new File(path);
        if(!file.exists()){
            createSessionList();
            try{
                writeToFile(path, "Session ID,Student ID,Class,Session Date,Time,Description\n");
            }catch(IOException e){}
        }

        ArrayList<String[]> sessionList = readFile(path);
        return sessionList;
    }

    //Specific student's session list
    static ArrayList<String[]> otherSessionList;
    static boolean specificSession = false;
    public static ArrayList<String[]> readSessionList(String studentID){        // Still has problem when delete session of a student while there are sessions of other students in csv
        String path = filePath[1];

        File file = new File(path);
        if(!file.exists()){
            createSessionList();
            try{
                writeToFile(path, "Session ID,Student ID,Class,Session Date,Time,Description\n");
            }catch(IOException e){}
        }

        ArrayList<String[]> sessionList = readFile(path);
        otherSessionList = new ArrayList<>();
        for (int i = 1; i < sessionList.size(); i++){
            if (!sessionList.get(i)[1].equals(studentID)){
                otherSessionList.add(sessionList.get(i));
                sessionList.remove(i);
                specificSession = true;
                i--;
            }
        }

        return sessionList;
    }
}
