package com.example;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty age = new SimpleStringProperty("");
    private final SimpleStringProperty stuClass = new SimpleStringProperty("");
    private final SimpleStringProperty numSessions = new SimpleStringProperty("");
    
    public Student(){
        this("", "", "", "", "");
    }
    
    public Student(String id, String name, String age, String stuClass, String numSessions){
        setId(id);
        setName(name);
        setAge(age);
        setClass(stuClass);
        setNumSessions(numSessions);
    }
    
    public String getId(){
        return id.get();
    }
    public void setId(String id_){
        id.set(id_);
    }

    public String getName(){
        return name.get();
    }
    public void setName(String name_){
        name.set(name_);
    }
    
    public String getAge(){
        return age.get();
    }
    public void setAge(String age_){
        age.set(age_);
    }

    public String getStuClass(){
        return stuClass.get();
    }
    public void setClass(String stuClass_){
        stuClass.set(stuClass_);
    }
    
    public String getNumSessions(){
        return numSessions.get();
    }
    public void setNumSessions(String numSessions_){
        numSessions.set(numSessions_);
    }
}
