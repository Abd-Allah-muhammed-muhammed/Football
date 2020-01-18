package com.example.football.ui;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.football.R;
import com.example.football.adapters.TeamsAdapter;
import com.example.football.databinding.MyfavoriteFragmentBinding;
import com.example.football.model.TeamInfoResponse;
import com.example.football.model.teams.Team;
import com.example.football.model.teams.TeamsResponse;

import java.util.ArrayList;
import java.util.List;

public class MYFavoriteFragment extends Fragment {

    private MyfavoriteViewModel mViewModel;

    MyfavoriteFragmentBinding binding ;
    public static MYFavoriteFragment newInstance() {
        return new MYFavoriteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater,R.layout.myfavorite_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(MyfavoriteViewModel.class);

        fetchData();
        return binding.getRoot();
    }

    private void fetchData() {


        final TeamsAdapter adapter = new TeamsAdapter(getActivity());
        binding.rvTeams.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvTeams.setAdapter(adapter);
        binding.rvTeams.setHasFixedSize(true);

        mViewModel.getmyFavorite(getContext(),binding.nofavorite).observe(this, new Observer<List<TeamInfoResponse>>() {
            @Override
            public void onChanged(List<TeamInfoResponse> teamInfoResponses) {

                List<Team> teams = new ArrayList<>();

                for (int i = 0; i < teamInfoResponses.size(); i++) {

                    Team team = new Team();
                    String name = teamInfoResponses.get(i).getName();
                    team.setName(name);
                    String shortName = teamInfoResponses.get(i).getShortName();
                    team.setShortName(shortName);
                    Integer id = teamInfoResponses.get(i).getId();
                    team.setId(id);
                    String crestUrl = teamInfoResponses.get(i).getCrestUrl();
                    team.setCrestUrl(crestUrl);

                    teams.add(team);


                }

                adapter.setTeamArrayList((ArrayList<Team>) teams);

            }
        });
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.closeDataBase();
    }
}
