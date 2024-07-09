package com.example.test.RoomDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.*;
@Dao
public interface RoomInterface {
    @Insert
    void insertTheEntity(ResponseModel responseModel);

    @Update
    void updateTheEntity(ResponseModel responseModel);


    @Delete
    void deleteTheEntity(ResponseModel responseModel);

    @Query("SELECT * FROM TaskManager")
    List<ResponseModel> getAllData();
}
