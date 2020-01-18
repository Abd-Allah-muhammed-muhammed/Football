package com.example.football.ui.team;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.football.R;
import com.example.football.adapters.PlayerInfoAdapter;
import com.example.football.databinding.TeamFragmentBinding;
import com.example.football.helper.Utils;
import com.example.football.model.Squad;
import com.example.football.model.TeamInfoResponse;
import com.example.football.room.AppDatabase;
import com.example.football.room.model_room.RoomTeamInfo;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class TeamFragment extends Fragment implements View.OnClickListener {

    private AppDatabase database ;
    private TeamViewModel mViewModel;

    private boolean isFavorit  = false ;
    private TeamInfoResponse teamInfoResponse;
    private Gson gson;
    private int id;

    public static TeamFragment newInstance() {
        return new TeamFragment();
    }


    private TeamFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.team_fragment, container, false);
        mViewModel = ViewModelProviders.of(this).get(TeamViewModel.class);

        initDB();

        binding.imageFavorite.setOnClickListener(this);
        id = getArguments() != null ? getArguments().getInt("id") : 0;


        fetchData();


        return binding.getRoot();
    }





    private void fetchData() {

        mViewModel.getTeamInfo(id, getContext(),binding.progInfo ,binding.imageFavorite).observe(this, new Observer<TeamInfoResponse>() {
            @Override
            public void onChanged(TeamInfoResponse teamInfoRespons) {

                teamInfoResponse = teamInfoRespons;

                binding.setTeamInfo(teamInfoRespons);
                binding.progInfo.setVisibility(View.GONE);


                RoomTeamInfo spicificTeam = database.teamInfoDeo().getSpicificTeam(id);


                if (spicificTeam!=null){

                    boolean favorit = spicificTeam.getIsFavorit();


                    if (favorit) {
                        binding.imageFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
                        TeamFragment.this.isFavorit = true;

                    }else {

                        binding.imageFavorite.setImageResource(R.drawable.ic_not_favorite_black_24dp);
                        TeamFragment.this.isFavorit = false;

                    }
                }

                setplayerInfo(teamInfoRespons.getSquad());


                String crestUrl = teamInfoRespons.getCrestUrl();



                if (crestUrl!=null){

                    try {


                        if (!crestUrl.endsWith("svg")){
                            Glide.with(getContext()).load(crestUrl).into(binding.imageTeam);

                        }else {

//                                    if (isNetworkAvailable(getContext())){

                            Utils.fetchSvg(getContext(), crestUrl,binding.imageTeam , getActivity());
//                                    }

                        }
                    }catch (Exception e){



                    }

                }


            }
        });

    }

    private void initDB() {

        database = Room.databaseBuilder(getContext(), AppDatabase.class,
                "db").allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    private void setplayerInfo(List<Squad> squad) {
        binding.rvPlayerInfo.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvPlayerInfo.setHasFixedSize(true);
        PlayerInfoAdapter adapter = new PlayerInfoAdapter();
        binding.rvPlayerInfo.setAdapter(adapter);
        adapter.setSquadArrayList((ArrayList<Squad>) squad);


    }




    @Override
    public void onClick(View v) {



        if (!isFavorit){
            binding.imageFavorite.setImageResource(R.drawable.ic_favorite_black_24dp);
            isFavorit = true;

            database.teamInfoDeo().updateTeam(id,true);
            Toast.makeText(getActivity(), "Added to Your Favorite", Toast.LENGTH_SHORT).show();


        }else {

            binding.imageFavorite.setImageResource(R.drawable.ic_not_favorite_black_24dp);
            isFavorit = false;

            database.teamInfoDeo().updateTeam(id,false);
            Toast.makeText(getActivity(), "Removed from Your Favorite", Toast.LENGTH_SHORT).show();

        }


    }




    @Override
    public void onDestroy() {
        super.onDestroy();

        mViewModel.closeDataBase();
        database.close();
    }
}
