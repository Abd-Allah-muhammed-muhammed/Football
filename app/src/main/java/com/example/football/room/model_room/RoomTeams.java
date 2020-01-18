package com.example.football.room.model_room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity
public class RoomTeams {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo()
    private int roomId;


    @ColumnInfo(name ="teams_info")
    private String teamsData;


    @ColumnInfo(name ="competition_id")
    private long competition_id;




    @Ignore
    public RoomTeams(int roomId, String teamsData, long competition_id) {
        this.roomId = roomId;
        this.teamsData = teamsData;
        this.competition_id = competition_id;
    }


    public RoomTeams(String teamsData, long competition_id) {
        this.teamsData = teamsData;
        this.competition_id = competition_id;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getTeamsData() {
        return teamsData;
    }

    public void setTeamsData(String teamsData) {
        this.teamsData = teamsData;
    }

    public long getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(long competition_id) {
        this.competition_id = competition_id;
    }
}
