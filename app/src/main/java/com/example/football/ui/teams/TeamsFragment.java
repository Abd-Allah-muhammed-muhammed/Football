package com.example.football.ui.teams;

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
import com.example.football.databinding.TeamsFragmentBinding;
import com.example.football.model.teams.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamsFragment extends Fragment {

    private TeamsViewModel mViewModel;



    public static TeamsFragment newInstance() {
        return new TeamsFragment();
    }

    TeamsFragmentBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.teams_fragment, container, false);
        binding.setLifecycleOwner(this);
        mViewModel = ViewModelProviders.of(this).get(TeamsViewModel.class);

        if (getArguments() != null) {

            long id = getArguments().getLong("id");
            fetchData(id);

        }

        return binding.getRoot();
    }

    private void fetchData(long id) {

        final TeamsAdapter adapter = new TeamsAdapter(getActivity());
        binding.rvTeams.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvTeams.setAdapter(adapter);
        binding.rvTeams.setHasFixedSize(true);


            mViewModel.getTeams(getContext(), id,binding.progTeams)
                    .observe(this, new Observer<List<Team>>() {

                @Override
                public void onChanged(List<Team> teams) {
                    binding.progTeams.setVisibility(View.GONE);
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
