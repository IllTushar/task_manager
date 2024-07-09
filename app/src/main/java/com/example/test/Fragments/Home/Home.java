package com.example.test.Fragments.Home;

import android.annotation.SuppressLint;
import android.os.Bundle;

import java.util.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.Fragments.Home.Adapter.TaskAdapter;
import com.example.test.Fragments.Home.Model.Task;

import com.example.test.R;
import com.example.test.Task.task_activity;
import com.example.test.assets.Assets;
import com.example.test.assets.toolbar_class;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends Fragment {
    Toolbar toolbar;
    FloatingActionButton createTask;
    RecyclerView taskListRecyclerView;
    private TaskAdapter taskAdapter;
    private List<Task> taskList;
    Assets assets;
    AlertDialog alertDialog;

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
        actionBar();

        // Get task from firebase..
        getDataFromFirebase();

        //Create new task
        createNewTask(createTask);

        // Swipe the list to delete the task..
        addSwipeToDelete();
        return view;
    }


    private void addSwipeToDelete() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // We don't want drag & drop
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (taskAdapter == null) {
                    assets.toast("TaskAdapter is not initialized");
                    return;
                }

                Task task = taskList.get(position);

                // Show confirmation dialog
                new AlertDialog.Builder(requireContext())
                        .setTitle("Delete Task")
                        .setMessage("Are you sure you want to delete this task?")
                        .setPositiveButton("Yes", (dialog, which) -> {
                            deleteTaskFromFirebase(task);
                        })
                        .setNegativeButton("No", (dialog, which) -> {
                            taskAdapter.notifyItemChanged(position);
                        })
                        .setOnCancelListener(dialog -> taskAdapter.notifyItemChanged(position))
                        .show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(taskListRecyclerView);
    }


    private void actionBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        if (activity != null) {
            activity.setSupportActionBar(toolbar);
        }

        toolbar_class.toolbar(getContext(), toolbar, "Task Manager");
    }

    private void createNewTask(FloatingActionButton createTask) {
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
        taskListRecyclerView = view.findViewById(R.id.task_list);
        assets = new Assets(getContext());

    }


    public void getDataFromFirebase() {
        assets.showProgressDialog("Please wait!!");
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(getContext(), taskList);
        taskListRecyclerView.setAdapter(taskAdapter);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference taskRef = database.getReference("Task");

        taskRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                taskList.clear();
                for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
                    Task task = taskSnapshot.getValue(Task.class);
                    if (task != null) {
                        taskList.add(task);
                    }
                }
                taskAdapter.notifyDataSetChanged();
                assets.dismissDialog();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                assets.dismissDialog();
                assets.toast("Something went wrong!!");
                // Handle possible errors.
            }
        });
    }

    public void deleteTaskFromFirebase(Task task) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference taskRef = database.getReference("Task").child(task.getUuid());
        taskRef.removeValue().addOnCompleteListener(taskCompletion -> {
            if (taskCompletion.isSuccessful()) {
                taskList.remove(task);
                taskAdapter.notifyDataSetChanged();

                assets.toast("Task removed successfully");
            } else {
                assets.toast("Failed to remove task");
            }
        });
    }
}