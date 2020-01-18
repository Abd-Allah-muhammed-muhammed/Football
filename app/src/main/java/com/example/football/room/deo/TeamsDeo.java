package com.example.football.room.deo;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.football.room.model_room.RoomTeams;

import java.util.List;

@Dao
public interface TeamsDeo extends BaseDeo<RoomTeams> {


 @Query("SELECT   * from  RoomTeams ")
    List<RoomTeams> getAllTeams();


    @Query("SELECT * from  RoomTeams where  competition_id = :id ")
    RoomTeams getSpicificTeams(long id);
}
