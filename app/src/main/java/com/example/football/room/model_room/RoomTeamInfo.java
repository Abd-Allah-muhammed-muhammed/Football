package com.example.football.room.model_room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class RoomTeamInfo {


    @PrimaryKey(autoGenerate = true)
    @ColumnInfo()
    private int roomId;


    @ColumnInfo(name ="team_info")
    private String teamInfoData;


    @ColumnInfo(name ="team_id")
    private long team_id;

    @ColumnInfo(name ="is_favorit")
    private boolean isFavorit;


    @Ignore
    public RoomTeamInfo(int roomId, String teamInfoData, long team_id, boolean isFavorit) {
        this.roomId = roomId;
        this.teamInfoData = teamInfoData;
        this.team_id = team_id;
        this.isFavorit = isFavorit;
    }


    public RoomTeamInfo(String teamInfoData, long team_id, boolean isFavorit) {
        this.teamInfoData = teamInfoData;
        this.team_id = team_id;
        this.isFavorit = isFavorit;
    }


    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getTeamInfoData() {
        return teamInfoData;
    }

    public void setTeamInfoData(String teamInfoData) {
        this.teamInfoData = teamInfoData;
    }

    public long getTeam_id() {
        return team_id;
    }

    public void setTeam_id(long team_id) {
        this.team_id = team_id;
    }

    public boolean getIsFavorit() {
        return isFavorit;
    }

    public void setIsFavorit(boolean isFavorit) {
        this.isFavorit = isFavorit;
    }
}
