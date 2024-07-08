package com.example.test.Fragments.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.R;
import com.example.test.Task.task_activity;
import com.example.test.assets.Assets;
import com.example.test.assets.toolbar_class;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Home extends Fragment {
    Toolbar toolbar;
    FloatingActionButton createTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findID(view);
        // Set the Toolbar as the ActionBar
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        toolbar_class.toolbar(getContext(), toolbar, "Task Manager");

        logic(createTask);

        return view;
    }

    private void logic(FloatingActionButton createTask) {
        Assets assets = new Assets(getContext());
        createTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assets.intent(getContext(), task_activity.class);
            }
        });
    }

    public void findID(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        createTask = view.findViewById(R.id.create_new_task);
    }

}