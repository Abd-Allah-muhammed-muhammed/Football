package com.example.football.repository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.football.R;
import com.example.football.model.Competition;
import com.example.football.model.CompetitionsResponse;
import com.example.football.model.TeamInfoResponse;
import com.example.football.model.teams.Team;
import com.example.football.model.teams.TeamsResponse;
import com.example.football.retrofit.Api;
import com.example.football.room.AppDatabase;
import com.example.football.room.model_room.RoomCompetitions;
import com.example.football.room.model_room.RoomTeamInfo;
import com.example.football.room.model_room.RoomTeams;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.example.football.helper.HelperMethods.isNetworkAvailable;
import static com.example.football.helper.HelperMethods.replace;
import static com.example.football.retrofit.ApiClient.getClient;
import static io.reactivex.android.schedulers.AndroidSchedulers.mainThread;
import static io.reactivex.schedulers.Schedulers.io;

public class Repository {



    private Api api;
    private AppDatabase db;
    private RoomCompetitions allCompetitions;
    private MutableLiveData<List<Competition>> dataCompetition;
    private MutableLiveData<List<Team>> dataTeams;
    private MutableLiveData<TeamInfoResponse> dataInfoPlayers ;

    private Gson gson ;
    private MutableLiveData<List<TeamInfoResponse>> dataListMyFavorite;

    public Repository() {
        api = getClient().create(Api.class);
        dataCompetition = new MutableLiveData<>();
        dataTeams = new MutableLiveData<>();
        dataInfoPlayers = new MutableLiveData<>();
        dataListMyFavorite = new MutableLiveData<>();

       gson = new Gson();
    }


    public MutableLiveData<List<Competition>> getdataCompetitions(final Context context, ProgressBar progHome, TextView textChose) {


        initDB(context);

        if (isNetworkAvailable(context)) {


            getCompetitionsFromInternet(context,progHome,textChose);

        } else {


            getCompetitionsFromDataBase(context ,progHome,textChose);



        }


        return dataCompetition;
    }

    private void initDB(Context context) {

        db = Room.databaseBuilder(context, AppDatabase.class,
                "db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }





    private void getCompetitionsFromDataBase(Context context, ProgressBar progHome, TextView textChose) {

        allCompetitions = db.competitionDeo().getAllCompetitions();

        if (allCompetitions ==null){

            progHome.setVisibility(View.GONE);
            textChose.setText("No Data saved yet please open your internet \n to save the data offline" );


        }else {


            RoomCompetitions allCompetitions = db.competitionDeo().getAllCompetitions();

            String competitions = allCompetitions.getCompetitions();
            Gson gson = new Gson();

            CompetitionsResponse competitionsResponse = gson.fromJson(competitions, CompetitionsResponse.class);


            dataCompetition.setValue(competitionsResponse.getCompetitions());
            progHome.setVisibility(View.GONE);


        }

//


    }




    @SuppressLint("CheckResult")
    private void getCompetitionsFromInternet(final Context context, final ProgressBar progHome, final TextView textChose) {


        api.getCompetitions(context.getString(R.string.token)).subscribeOn(io()).
                observeOn(mainThread()).subscribeWith(new Observer<CompetitionsResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CompetitionsResponse competitionsResponse) {

                dataCompetition.setValue(competitionsResponse.getCompetitions());

                progHome.setVisibility(View.GONE);

                allCompetitions = db.competitionDeo().getAllCompetitions();

                if (null == allCompetitions) {



                    insertCompetitionToDataBase(competitionsResponse);


                }
            }

            @Override
            public void onError(Throwable e) {
                progHome.setVisibility(View.GONE);

            }

            @Override
            public void onComplete() {

            }
        });

    }




    private void insertCompetitionToDataBase(CompetitionsResponse competitionsResponse) {



        String allCompetitions = gson.toJson(competitionsResponse);

        RoomCompetitions roomCompetitions =  new RoomCompetitions(allCompetitions);
        db.competitionDeo().insert(roomCompetitions);

    }



    public void closeDataBase(){

        if (db.isOpen()){
            db.close();
        }


    }









    // repository for Teams
    public LiveData<List<Team>> getTeams(Context context, final long id, ProgressBar progTeams, TextView textChose) {

        initDB(context);


        if (isNetworkAvailable(context)) {

            getTeamsFromInternet(context, id ,progTeams,textChose);


        } else {


            getTeamsFromDataBase( id , textChose ,progTeams);

        }


        return dataTeams;

    }


    private void getTeamsFromDataBase(long id, TextView textChose, ProgressBar progTeams) {


        RoomTeams spicificTeam = db.teamsDeo().getSpicificTeams(id);


        if (spicificTeam != null) {

            String teamsData = spicificTeam.getTeamsData();

            TeamsResponse teamsResponse = gson.fromJson(teamsData, TeamsResponse.class);

            dataTeams.setValue(teamsResponse.getTeams());

            progTeams.setVisibility(View.GONE);

        }else {

            progTeams.setVisibility(View.GONE);
            textChose.setText("No data available now  \n please try another competition you have selected before \n or open your internet to load the new data");


        }
        }




    @SuppressLint("CheckResult")
    private void getTeamsFromInternet(final Context context, final long id, final ProgressBar progTeams, final TextView textChose) {


        api.getTeam(context.getString(R.string.token), id).subscribeOn(io()).observeOn(mainThread())
                .subscribeWith(new Observer<TeamsResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamsResponse teamsResponse) {


                        if (!teamsResponse.getTeams().isEmpty()) {

                            dataTeams.setValue(teamsResponse.getTeams());
                            progTeams.setVisibility(View.GONE);
                            insetrTeamsToRoom(teamsResponse , id);

                        }else {

                            progTeams.setVisibility(View.GONE);

                            textChose.setText("No data available now  \n please try another competition");



                        }




                    }

                    @Override
                    public void onError(Throwable e) {

                        progTeams.setVisibility(View.GONE);




                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void insetrTeamsToRoom(TeamsResponse teamsList , Long comp_id) {

        List<RoomTeams> allTeams = db.teamsDeo().getAllTeams();

        List<Long> id_list = new ArrayList<>();
        String teamsGson = gson.toJson(teamsList);

        RoomTeams roomTeams = new RoomTeams(teamsGson,comp_id);


        if (!allTeams.isEmpty()){


            for (int i = 0; i < allTeams.size(); i++) {

                id_list.add(allTeams.get(i).getCompetition_id());
            }


            if (!id_list.contains(comp_id)) {

                db.teamsDeo().insert(roomTeams);

            }

        }else {

            db.teamsDeo().insert(roomTeams);

        }


    }






    // repository for Info Player

    public LiveData<TeamInfoResponse> getTeamInfo(int id, Context context, ProgressBar progInfo, ImageView imageFavorite){



        initDB(context);


        if (isNetworkAvailable(context)) {

            getTeamFromInternet(context, id,progInfo,imageFavorite);


        } else {


            getTeamFromDataBase(context,id,progInfo,imageFavorite);

        }



        return dataInfoPlayers;
    }

    private void getTeamFromDataBase(Context context, int id, ProgressBar progInfo, ImageView imageFavorite) {

        RoomTeamInfo spicificTeam = db.teamInfoDeo().getSpicificTeam(id);


        if (spicificTeam!=null){

            String teamInfoData = spicificTeam.getTeamInfoData();
            TeamInfoResponse teamInfoResponse = gson.fromJson(teamInfoData, TeamInfoResponse.class);
            dataInfoPlayers.setValue(teamInfoResponse);
            progInfo.setVisibility(View.GONE);

        }else {

            progInfo.setVisibility(View.GONE);
            imageFavorite.setVisibility(View.GONE);
            Toast.makeText(context, "no data saved yet", Toast.LENGTH_SHORT).show();
        }


    }

    @SuppressLint("CheckResult")
    private void getTeamFromInternet(final Context context, final int id, final ProgressBar progInfo, final ImageView imageFavorite) {

        api.getTeamInfo(context.getString(R.string.token),id).subscribeOn(io()).observeOn(mainThread())
                .subscribeWith(new Observer<TeamInfoResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(TeamInfoResponse teamInfoResponse) {




                        if (!teamInfoResponse.getSquad().isEmpty()){

                            dataInfoPlayers.setValue(teamInfoResponse);
                            insetrTeamsToRoom(teamInfoResponse , id);
                            progInfo.setVisibility(View.GONE);
                        }else {

                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        progInfo.setVisibility(View.GONE);

                        imageFavorite.setVisibility(View.GONE);
                        Toast.makeText(context, "Please try again later", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    private void insetrTeamsToRoom(TeamInfoResponse teamInfoResponse, int id) {


        List<RoomTeamInfo> allTeam = db.teamInfoDeo().getAllTeam();

        List<Long> id_list = new ArrayList<>();

        String teaminfoGson = gson.toJson(teamInfoResponse);

        RoomTeamInfo roomTeamInfo = new RoomTeamInfo(teaminfoGson,id,false);

        if (!allTeam.isEmpty()){


            for (int i = 0; i < allTeam.size(); i++) {

                id_list.add(allTeam.get(i).getTeam_id());


            }


            if (!id_list.contains((long)id)){

                db.teamInfoDeo().insert(roomTeamInfo);

            }


        }else {

            db.teamInfoDeo().insert(roomTeamInfo);
        }



    }





    public MutableLiveData<List<TeamInfoResponse>> getFavoriteList(Context context, TextView nofavorite){

        initDB(context);

        List<RoomTeamInfo> favoriteTeam = db.teamInfoDeo().getFavoriteTeam(true);


        if (!favoriteTeam.isEmpty()){
            List<TeamInfoResponse> teamInfoResponses = new ArrayList<>();
            for (int i = 0; i < favoriteTeam.size(); i++) {

                String teamInfoData = favoriteTeam.get(i).getTeamInfoData();

                TeamInfoResponse teamInfoResponse = gson.fromJson(teamInfoData, TeamInfoResponse.class);

                teamInfoResponses.add(teamInfoResponse);
            }


            dataListMyFavorite.setValue(teamInfoResponses);

        }else {


            nofavorite.setVisibility(View.VISIBLE);


        }



        return dataListMyFavorite;
    }




}
