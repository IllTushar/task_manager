package com.example.test.assets;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Assets {

    Context context;

    public Assets(Context context) {
        this.context = context;
    }

    public void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void log(String tag, String message) {
        Log.d(tag, message);
    }

    public void intent(Context context, Class<?> targetClass) {
        context.startActivity(new Intent(context, targetClass));
    }

}
