package com.example.football.retrofit;


import com.example.football.model.CompetitionsResponse;
import com.example.football.model.TeamInfoResponse;
import com.example.football.model.teams.TeamsResponse;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface Api {


    @GET("competitions")
    Observable<CompetitionsResponse> getCompetitions(@Header("X-Auth-Token") String token);

    @GET("competitions/{id}/teams")
    Observable <TeamsResponse> getTeam(@Header("X-Auth-Token") String token, @Path("id") long id);

    @GET("teams/{id}")
    Observable<TeamInfoResponse> getTeamInfo(@Header("X-Auth-Token") String token, @Path("id") long id);






}
