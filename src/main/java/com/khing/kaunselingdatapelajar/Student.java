package com.khing.kaunselingdatapelajar;

import javafx.beans.property.SimpleStringProperty;

public class Student {
    private final SimpleStringProperty id = new SimpleStringProperty("");
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty stuClass = new SimpleStringProperty("");
    private final SimpleStringProperty profile = new SimpleStringProperty("");
    private final SimpleStringProperty numSessions = new SimpleStringProperty("");
    
    public Student(){
        this("", "", "", "", "");
    }
    
    public Student(String id, String name, String stuClass, String profile, String numSessions){
        setId(id);
        setName(name);
        setProfile(profile);
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

    public String getStuClass(){
        return stuClass.get();
    }
    public void setClass(String stuClass_){
        stuClass.set(stuClass_);
    }
    
    public String getProfile(){
        return profile.get();
    }
    public void setProfile(String profile_){
        profile.set(profile_);
    }

    public String getNumSessions(){
        return numSessions.get();
    }
    public void setNumSessions(String numSessions_){
        numSessions.set(numSessions_);
    }
}
