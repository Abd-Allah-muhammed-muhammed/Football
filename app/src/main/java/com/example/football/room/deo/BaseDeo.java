package com.example.football.room.deo;

import androidx.room.Delete;
import androidx.room.Insert;



public interface BaseDeo <T> {


    @Insert
    void insert(T... entity);


    @Delete
    void delete(T... entity);




}
