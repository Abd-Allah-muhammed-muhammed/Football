package com.example.football.room;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.football.room.deo.TeamInfoDeo;
import com.example.football.room.model_room.RoomCompetitions;
import com.example.football.room.model_room.RoomTeamInfo;
import com.example.football.room.model_room.RoomTeams;
import com.example.football.room.deo.CompetitionDeo;
import com.example.football.room.deo.TeamsDeo;

@Database(entities = {RoomCompetitions.class, RoomTeams.class , RoomTeamInfo.class},version = 9)

public abstract class AppDatabase  extends RoomDatabase {

    public abstract TeamsDeo teamsDeo ();
    public abstract CompetitionDeo competitionDeo ();
    public abstract TeamInfoDeo teamInfoDeo ();






}


