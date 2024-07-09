package com.example.test.EditTask.functions;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.test.R;

public class EditTaskClass {
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
}
