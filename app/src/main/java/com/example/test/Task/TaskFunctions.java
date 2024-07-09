package com.example.test.Task;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import com.example.test.R;
import com.example.test.Task.Firebase.firebase_database;
import com.example.test.assets.Assets;
import com.example.test.main_screen.MainActivity;

import java.util.*;

public class TaskFunctions {

    public static void functions(Context context, ImageView btn_back, TextView dueDate, Spinner priority_level) {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Assets assets = new Assets(context);
                assets.intent(context, MainActivity.class);
            }
        });
        drop_down(context, priority_level);
        due_date(context, dueDate);
    }

    //Due Date
    private static void due_date(Context context, TextView dueDate) {
        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFunction(context, dueDate);
            }
        });
    }

    //Date Picker
    private static void datePickerFunction(Context context, TextView dueDate) {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                (view, year1, monthOfYear, dayOfMonth1) -> {
                    // Handle date selection
                    String selectedDate = formatDate(dayOfMonth1, monthOfYear, year1);
                    dueDate.setText(selectedDate);
                },
                year, month, dayOfMonth);

        // Restrict DatePicker to only future dates
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);

        // Show DatePickerDialog
        datePickerDialog.show();
    }

    //Formated-Date
    private static String formatDate(int dayOfMonth, int monthOfYear, int year) {
        // Format month to characters
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String month = months[monthOfYear];

        // Format date string
        return dayOfMonth + "-" + month + "-" + year;
    }


    // Priority drop down..
    private static void drop_down(Context context, Spinner priority_level) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.priority, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        priority_level.setAdapter(adapter);
    }

    //Save Task
    public static void saveTask(Context context, AppCompatButton save_task, AppCompatEditText title, AppCompatEditText description, TextView dueDate, TextView location, Spinner priority_level) {
        Assets assets = new Assets(context);
        save_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation(context, title, description, dueDate, location, priority_level)) {
                    String uuid = UUID.randomUUID().toString().trim();
                    String Title = title.getText().toString().trim();
                    String Description = description.getText().toString().trim();
                    String DueDate = dueDate.getText().toString().trim();
                    String Location = location.getText().toString().trim();
                    String Priority = priority_level.getSelectedItem().toString().trim();
                    String Status = "Pending";
                    if (!uuid.isEmpty() && uuid != null) {
                        firebase_database.firebase_function(assets, uuid, Title, Description, DueDate, Location, Priority,Status);
                    }
                }
            }
        });
    }

    //Validation of the task..
    private static boolean validation(Context context, AppCompatEditText title, AppCompatEditText description, TextView dueDate, TextView location, Spinner priority_level) {
        Assets assets = new Assets(context);
        if (title.getText().toString().trim().isEmpty()) {
            assets.toast(context, "Title is empty!!");
            return false;
        }
        if (description.getText().toString().trim().isEmpty()) {
            assets.toast(context, "description is empty!!");
            return false;
        }
        if (dueDate.getText().toString().isEmpty() || dueDate.getText().toString().trim().equals("Due-Date")) {
            assets.toast(context, "Check the due-date may be it is empty");
            return false;
        }
        if (location.getText().toString().trim().isEmpty() || dueDate.getText().toString().trim().equals("Location")) {
            assets.toast(context, "check location is may be empty or not filled");
            return false;
        }
        if (priority_level.getSelectedItem().toString().trim().equals("Select Priority")) {
            assets.toast(context, "Select a priority level!!");
            return false;
        }
        return true;
    }
}
