package com.example.test.Task.Firebase;

public class firebase_model {
    public String title;
    public String description;
    public String priority;
    public String dueDate;
    public String location;
    public String fcmKeys;

    public firebase_model(String title, String description, String dueDate, String location, String priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.location = location;
    }

    public void setFcmKeys(String fcmKeys) {
        this.fcmKeys = fcmKeys;
    }
}
