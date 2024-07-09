package com.example.test.RoomDB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = ResponseModel.class, exportSchema = false, version = 1)
public abstract class RequestClass extends RoomDatabase {
    private static final String DB_NAME = "TaskManager";
    private static RequestClass requestClass;

    public static synchronized RequestClass getRequestClass(Context context) {
        if (requestClass == null) {
            requestClass = Room.databaseBuilder(context, RequestClass.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return requestClass;
    }
    public abstract RoomInterface roomInterface();
}
