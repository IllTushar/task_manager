package com.example.test.RoomDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "TaskManager")
public class ResponseModel {
    @PrimaryKey
    @NonNull
    private String uuid;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "dueDate")
    private String dueDate;
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "priority")
    private String priority;
    @ColumnInfo(name = "status")
    private String status;


    // No-argument constructor required by Room
    public ResponseModel() {
    }
    @Ignore
    public ResponseModel(@NonNull String uuid, String title, String description, String dueDate, String location, String priority, String status) {
        this.uuid = uuid;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.location = location;
        this.priority = priority;
        this.status = status;
    }

    @NonNull
    public String getUuid() {
        return uuid;
    }

    public void setUuid(@NonNull String uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
