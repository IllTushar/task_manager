package com.example.test.EditTask;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.test.EditTask.functions.EditTaskClass;
import com.example.test.R;
import com.example.test.assets.Assets;
import com.example.test.main_screen.MainActivity;

public class EditTaskScreen extends AppCompatActivity {
    TextView title, description, due_date, location;
    ImageView btn_back;
    Assets assets;
    Spinner status_spinner, priority_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_screen);
        findID();
        setData();
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assets.intent(MainActivity.class);
            }
        });

        EditTaskClass.drop_down_status(EditTaskScreen.this, status_spinner);
    }

    private void setData() {
        String uuid = getIntent().getStringExtra("uuid");
        String Title = getIntent().getStringExtra("title");
        if (Title != null) {
            title.setText(Title);
        }
        String Description = getIntent().getStringExtra("description");
        if (Description != null) {
            description.setText(Description);
        }
        String Due_date = getIntent().getStringExtra("due_date");
        if (Due_date != null) {
            due_date.setText(Due_date);
        }
        String Location = getIntent().getStringExtra("location");
        if (Location != null) {
            location.setText(Location);
        }
        String spinnerData = getIntent().getStringExtra("priority_level");
        // Retrieve the string array from resources
        String[] statusArray = getResources().getStringArray(R.array.priority);
        // Set the data to the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, statusArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        priority_level.setAdapter(adapter);
        // Set the selected item based on the passed priority level
        if (spinnerData != null) {
            int position = adapter.getPosition(spinnerData);
            priority_level.setSelection(position);
        }
    }

    private void findID() {

        title = findViewById(R.id.edit_title);
        description = findViewById(R.id.edit_description);
        due_date = findViewById(R.id.edit_due_date);
        location = findViewById(R.id.edit_location);
        btn_back = findViewById(R.id.edit_btn_back);
        assets = new Assets(EditTaskScreen.this);
        status_spinner = findViewById(R.id.edit_completiton_status);
        priority_level = findViewById(R.id.edit_priority_level);
    }
}