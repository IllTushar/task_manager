package com.example.test.Fragments.Home.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.EditTask.EditTaskScreen;
import com.example.test.Fragments.Home.Model.Task;
import com.example.test.R;
import com.example.test.assets.Assets;


import java.util.*;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.myViewHolder> {
    private List<Task> taskList;
    Context context;
    Assets assets;

    public TaskAdapter(Context context, List<Task> taskList) {
        this.taskList = taskList;
        this.context = context;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_single_row_xml, parent, false);
        return new myViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        holder.title.setText(taskList.get(position).getTitle());
        holder.description.setText(taskList.get(position).getDescription());
        holder.due_date.setText(taskList.get(position).getDueDate());
        String priority = taskList.get(position).getPriority();
        holder.priority.setText(taskList.get(position).getPriority());
        holder.status.setText(taskList.get(position).getStatus());
        if (priority.equals("High")) {
            // Setting background color
            holder.priority.setBackgroundResource(R.color.light_red); // Assuming you have defined this color resource
            // Setting text color
            holder.priority.setTextColor(Color.RED);
        } else if (priority.equals("Medium")) {
            // Setting background color
            holder.priority.setBackgroundResource(R.color.light_yellow); // Assuming you have defined this color resource
            // Setting text color
            holder.priority.setTextColor(Color.YELLOW);
        } else if (priority.equals("Low")) {
            // Setting background color
            holder.priority.setBackgroundResource(R.color.light_purple); // Assuming you have defined this color resource
            // Setting text color
            holder.priority.setTextColor(ContextCompat.getColor(context, R.color.toolbar));
        }
        holder.location.setText(taskList.get(position).getLocation());

        holder.task_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, EditTaskScreen.class);
                i.putExtra("uuid", taskList.get(position).getUuid());
                i.putExtra("title", holder.title.getText().toString().trim());
                i.putExtra("description", holder.description.getText().toString().trim());
                i.putExtra("due_date", holder.due_date.getText().toString().trim());
                i.putExtra("location", holder.location.getText().toString().trim());
                i.putExtra("priority_level", holder.priority.getText().toString().trim());
                i.putExtra("status", holder.status.getText().toString().trim());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, due_date, priority, location, status;
        CardView task_card;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.rec_title);
            description = itemView.findViewById(R.id.rec_description);
            due_date = itemView.findViewById(R.id.rec_date);
            priority = itemView.findViewById(R.id.rec_priority);
            location = itemView.findViewById(R.id.rec_location);
            task_card = itemView.findViewById(R.id.task_card);
            assets = new Assets(context);
            status = itemView.findViewById(R.id.rec_status);
        }
    }
}
