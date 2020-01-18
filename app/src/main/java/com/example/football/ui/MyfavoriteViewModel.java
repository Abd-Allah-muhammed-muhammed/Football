package com.example.football.ui;

import android.content.Context;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.football.adapters.TeamsAdapter;
import com.example.football.model.TeamInfoResponse;
import com.example.football.repository.Repository;

import java.util.List;

public class MyfavoriteViewModel extends ViewModel {


    Repository repository ;
    public MyfavoriteViewModel() {

        repository = new Repository();
    }


    public MutableLiveData<List<TeamInfoResponse>> getmyFavorite(Context context, TextView nofavorite){

        return repository.getFavoriteList(context,nofavorite);
    }



    public void closeDataBase(){

        repository.closeDataBase();
    }


}
