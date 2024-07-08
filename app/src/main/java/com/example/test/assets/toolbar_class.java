package com.example.test.assets;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

public class toolbar_class {
    public static void toolbar(Context context, androidx.appcompat.widget.Toolbar toolbar, String title) {
        ((AppCompatActivity) context).setSupportActionBar(toolbar);
        toolbar.setTitle(title);
    }
}
