package com.example.test.Fragments.Home.Model;

public class Task {
    private String description;
    private String dueDate;
    private String location;
    private String priority;
    private String title;

    // Constructors, getters, and setters

    public Task() {
        // Default constructor required for calls to DataSnapshot.getValue(Task.class)
    }

    public Task(String description, String dueDate, String location, String priority, String title) {
        this.description = description;
        this.dueDate = dueDate;
        this.location = location;
        this.priority = priority;
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
