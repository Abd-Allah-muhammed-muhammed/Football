package com.example.football.room.deo;

import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Update;

import com.example.football.room.AppDatabase;
import com.example.football.room.model_room.RoomTeamInfo;

import java.util.List;

@Dao
public abstract class TeamInfoDeo implements BaseDeo<RoomTeamInfo> {


    @Query("SELECT   * from  RoomTeamInfo ")
    public abstract List<RoomTeamInfo> getAllTeam();


    @Query("SELECT * from   RoomTeamInfo where  team_id = :id ")
    public abstract RoomTeamInfo getSpicificTeam(long id);


    @Query("SELECT * from   RoomTeamInfo where  is_favorit = :isFavorite ")
  public   abstract List<RoomTeamInfo> getFavoriteTeam(boolean isFavorite);


    @Update()
    public abstract void updateSpicificTeam(RoomTeamInfo roomTeamInfo);


    public void updateTeam(int id, boolean isFavorite ) {
        RoomTeamInfo spicificTeam = getSpicificTeam(id);
        spicificTeam.setIsFavorit(isFavorite);
        updateSpicificTeam(spicificTeam);
    }










}
