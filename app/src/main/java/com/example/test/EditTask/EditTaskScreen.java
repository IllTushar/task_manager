package com.example.test.EditTask;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
        EditTaskClass.setData(EditTaskScreen.this, getIntent(), title, description, due_date, location, priority_level, status_spinner);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assets.intent(MainActivity.class);
            }
        });


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