package com.example.football.room.deo;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.football.room.model_room.RoomCompetitions;

@Dao
public interface CompetitionDeo extends BaseDeo<RoomCompetitions> {

    @Query("SELECT * fROM RoomCompetitions")
    RoomCompetitions getAllCompetitions();

}
