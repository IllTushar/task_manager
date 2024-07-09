package com.example.test.EditTask.functions;

import android.content.Context;
import android.content.Intent;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.test.R;

public class EditTaskClass {
    public static void setData(Context context,Intent intent, TextView title, TextView description, TextView due_date, TextView location, Spinner priority_level, Spinner status_spinner) {
        String uuid = intent.getStringExtra("uuid");
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

    // Priority drop down..
    public static void drop_down_status(Context context, Spinner priority_level) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.status, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        priority_level.setAdapter(adapter);
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
}
