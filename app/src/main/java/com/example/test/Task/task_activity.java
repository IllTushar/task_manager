package com.example.test.Task;

import android.content.pm.PackageManager;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.test.R;
import com.example.test.RoomDB.RequestClass;
import com.example.test.assets.Assets;


import android.Manifest;
import android.widget.Toast;


public class task_activity extends AppCompatActivity {

    ImageView btn_back;
    Spinner priority_level;
    TextView dueDate, location;
    private static final int REQUEST_LOCATION_PERMISSION = 1;
    Assets assets;
    Location_Manager locationManager;
    AppCompatButton save_task;
    AppCompatEditText title, description;
    RequestClass requestClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        find_Id();
        TaskFunctions.functions(task_activity.this, btn_back, dueDate, priority_level);
        getLocationPermission();
        TaskFunctions.saveTask(task_activity.this,save_task,title,description,dueDate,location,priority_level,requestClass);

    }

    private void getLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            locationManager.getLocation(location);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationManager.getLocation(location);
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void find_Id() {
        btn_back = findViewById(R.id.btn_back);
        priority_level = findViewById(R.id.priority_level);
        dueDate = findViewById(R.id.due_date);
        assets = new Assets(task_activity.this);
        location = findViewById(R.id.location);
        locationManager = new Location_Manager(task_activity.this);
        save_task = findViewById(R.id.save_task);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);
        requestClass = RequestClass.getRequestClass(task_activity.this);
    }
}