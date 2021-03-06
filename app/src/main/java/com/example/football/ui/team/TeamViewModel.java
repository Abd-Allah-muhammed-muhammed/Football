package com.example.football.ui.team;

import android.content.Context;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.football.model.TeamInfoResponse;
import com.example.football.repository.Repository;

public class TeamViewModel extends ViewModel {



    Repository repository ;
    public TeamViewModel() {

        repository = new Repository();

    }



    LiveData<TeamInfoResponse> getTeamInfo(int id, Context context, ProgressBar progInfo, ImageView imageFavorite){


        return repository.getTeamInfo(id,context,progInfo ,imageFavorite);
    }


    public void closeDataBase(){

        repository.closeDataBase();

    }



}
