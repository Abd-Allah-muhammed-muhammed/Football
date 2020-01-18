package com.example.football.ui.home;

;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.football.model.Competition;
import com.example.football.repository.Repository;

import java.util.List;


public class HomeViewModel extends ViewModel {


    Repository repository ;

    public HomeViewModel() {

        repository = new Repository();
    }




    public MutableLiveData<List<Competition>> getdataCompetition(Context context, ProgressBar progHome, TextView textChose){


        return repository.getdataCompetitions(context,progHome,textChose);

    }




    public void closeDataBase(){

        repository.closeDataBase();
    }

    }

