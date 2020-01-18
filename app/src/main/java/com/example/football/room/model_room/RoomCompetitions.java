package com.example.football.room.model_room;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity
public class RoomCompetitions {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo()
    private int roomId;

    @ColumnInfo(name = "competitions")
    private String competitions;


    @Ignore
    public RoomCompetitions(int roomId, String competitions) {
        this.roomId = roomId;
        this.competitions = competitions;
    }


    public RoomCompetitions(String competitions) {
        this.competitions = competitions;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int mRoomId) {
        this.roomId = mRoomId;
    }

    public String getCompetitions() {
        return competitions;
    }

    public void setCompetitions(String competitions) {
        this.competitions = competitions;
    }
}

