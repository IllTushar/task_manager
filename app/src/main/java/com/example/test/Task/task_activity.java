package com.example.test.Task;

import android.app.DatePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test.R;
import com.example.test.assets.Assets;
import com.example.test.main_screen.MainActivity;

public class task_activity extends AppCompatActivity {
    ImageView btn_back;
    Spinner priority_level;
    TextView dueDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        find_Id();
        functions();
    }

    private void functions() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Assets assets = new Assets(task_activity.this);
                assets.intent(task_activity.this, MainActivity.class);
            }
        });
        drop_down();
        due_date();
    }

    private void due_date() {
        dueDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerFunction(dueDate);
            }
        });
    }

    private void datePickerFunction(TextView dueDate) {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
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

    private String formatDate(int dayOfMonth, int monthOfYear, int year) {
        // Format month to characters
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        String month = months[monthOfYear];

        // Format date string
        return dayOfMonth + "-" + month + "-" + year;
    }

    private void find_Id() {
        btn_back = findViewById(R.id.btn_back);
        priority_level = findViewById(R.id.priority_level);
        dueDate = findViewById(R.id.due_date);
    }


    // priority drop down..
    private void drop_down() {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.priority, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        priority_level.setAdapter(adapter);
    }
}