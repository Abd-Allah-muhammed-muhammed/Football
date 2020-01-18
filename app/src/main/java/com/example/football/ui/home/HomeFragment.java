package com.example.football.ui.home;

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
import com.example.football.adapters.CompetitionsAdapter;
import com.example.football.databinding.HomeFragmentBinding;
import com.example.football.model.Competition;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel mViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    HomeFragmentBinding binding ;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater,R.layout.home_fragment,container,false);
        mViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        binding.setLifecycleOwner(this);
        fetchData();
        return binding.getRoot();
    }




    private void fetchData() {
        final CompetitionsAdapter competitionsAdapter = new CompetitionsAdapter();
        binding.rvCompetitions.setAdapter(competitionsAdapter);
        binding.rvCompetitions.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvCompetitions.setHasFixedSize(true);
        mViewModel.getdataCompetition(getContext(),binding.progHome,binding.textChose).observe(this, new Observer<List<Competition>>() {
            @Override
            public void onChanged(List<Competition> competitions) {

                binding.progHome.setVisibility(View.GONE);
                competitionsAdapter.setCompetitionArrayList((ArrayList<Competition>) competitions);

            }
        });




    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.closeDataBase();

    }
}
