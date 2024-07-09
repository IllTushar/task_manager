package com.example.test.EditTask.functions;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.test.R;
import com.example.test.assets.Assets;
import com.example.test.main_screen.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditTaskClass {
    public static void setData(Context context, Intent intent, TextView title, TextView description, TextView due_date, TextView location, Spinner priority_level, Spinner status_spinner) {

        String Title = intent.getStringExtra("title");
        if (Title != null) {
            title.setText(Title);
        }
        String Description = intent.getStringExtra("description");
        if (Description != null) {
            description.setText(Description);
        }
        String Due_date = intent.getStringExtra("due_date");
        if (Due_date != null) {
            due_date.setText(Due_date);
        }
        String Location = intent.getStringExtra("location");
        if (Location != null) {
            location.setText(Location);
        }
        priority_level_function(context, intent, priority_level);
        status_function(context, intent, status_spinner);

    }

    private static void status_function(Context context, Intent intent, Spinner status_spinner) {
        String spinnerData = intent.getStringExtra("status");
        // Retrieve the string array from resources
        String[] statusArray = context.getResources().getStringArray(R.array.status);
        // Set the data to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, statusArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status_spinner.setAdapter(adapter);
        // Set the selected item based on the passed priority level
        if (spinnerData != null) {
            int position = adapter.getPosition(spinnerData);
            status_spinner.setSelection(position);
        }
    }

    private static void priority_level_function(Context context, Intent intent, Spinner priority_level) {
        String spinnerData = intent.getStringExtra("priority_level");
        // Retrieve the string array from resources
        String[] statusArray = context.getResources().getStringArray(R.array.priority);
        // Set the data to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, statusArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority_level.setAdapter(adapter);
        // Set the selected item based on the passed priority level
        if (spinnerData != null) {
            int position = adapter.getPosition(spinnerData);
            priority_level.setSelection(position);
        }
    }

    public static void update_data(Assets assets, String uuid, TextView title, TextView description, TextView due_date, TextView location, Spinner status_spinner, Spinner priority_level) {
        assets.showProgressDialog("Please wait...");

        // Get a reference to the Firebase Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference taskRef = database.getReference("Task").child(uuid);

        // Get the values from the UI elements
        String Title = title.getText().toString().trim();
        String Description = description.getText().toString().trim();
        String Due_Date = due_date.getText().toString().trim();
        String Location = location.getText().toString().trim();
        String Priority_Level = priority_level.getSelectedItem().toString().trim();
        String Status = status_spinner.getSelectedItem().toString().trim();

        // Create a map to hold the updates
        Map<String, Object> map = new HashMap<>();
        map.put("title", Title);
        map.put("description", Description);
        map.put("dueDate", Due_Date);
        map.put("location", Location);
        map.put("priority", Priority_Level);
        map.put("status", Status);

        // Update the data in Firebase
        taskRef.updateChildren(map).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                // Dismiss the progress dialog
                assets.dismissDialog();

                if (task.isSuccessful()) {
                    // Show a success message
                    assets.toast("Updated!!");

                    // Navigate to the MainActivity
                    assets.intent(MainActivity.class);
                } else {
                    // Handle the error
                    assets.toast("Update failed: " + task.getException().getMessage());
                }
            }
        });
    }
}
