package com.example.football.ui.teams;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.football.model.teams.Team;
import com.example.football.repository.Repository;

import java.util.List;



public class TeamsViewModel extends ViewModel {

    Repository repository ;

    public TeamsViewModel() {

        repository = new Repository();

    }





    public LiveData<List<Team>> getTeams(Context context, long id, ProgressBar progTeams, TextView textChose){

        return repository.getTeams(context,id,progTeams,textChose);
    }



    public void closeDataBase(){

        repository.closeDataBase();

    }
}
