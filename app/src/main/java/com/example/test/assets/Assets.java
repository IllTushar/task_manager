package com.example.test.assets;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class Assets {

    Context context;
    ProgressDialog pd;

    public Assets(Context context) {
        this.context = context;
        pd = new ProgressDialog(context);

    }

    public void toast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public void toast(String message) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show();
    }

    public void log(String tag, String message) {
        Log.d(tag, message);
    }

    public void intent(Context context, Class<?> targetClass) {
        context.startActivity(new Intent(context, targetClass));
    }

    public void intent(Class<?> targetClass) {
        context.startActivity(new Intent(context, targetClass));
    }

    public void showProgressDialog(String message) {
        pd.show();
        pd.setMessage(message);
    }

    public void dismissDialog() {
        pd.dismiss();
    }

}
