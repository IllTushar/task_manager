package com.example.test.Task.Firebase;

import com.example.test.assets.Assets;
import com.example.test.main_screen.MainActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class firebase_database {
    public static void firebase_function(Assets assets, String uuid, String title, String description, String dueDate, String location, String priority) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference taskRef = database.getReference("Task").child(uuid);
        firebase_model task = new firebase_model(title, description, dueDate, location, priority);
        taskRef.setValue(task);
        assets.toast("Done");
        assets.intent(MainActivity.class);
    }
}
