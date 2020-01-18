package com.example.football.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football.R;
import com.example.football.databinding.ItemCompetitionsBinding;
import com.example.football.model.Competition;
import com.example.football.ui.teams.TeamsFragment;

import java.util.ArrayList;

import static com.example.football.helper.HelperMethods.replace;


public class CompetitionsAdapter extends RecyclerView.Adapter<CompetitionsAdapter.CompetitionViewHolder> {

    private ArrayList<Competition> competitionArrayList;


    @NonNull
    @Override
    public CompetitionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          ItemCompetitionsBinding itemTripsBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_competitions, parent, false);
        return new CompetitionViewHolder(itemTripsBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull CompetitionsAdapter.CompetitionViewHolder holder, int position) {

        final Competition competition = competitionArrayList.get(position);
        holder.itemCompetitionsBinding.setComp(competition);

        holder.itemCompetitionsBinding.itemComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Long id = competition.getArea().getId();
                Bundle bundle = new Bundle();
                bundle.putLong("id",id);
                TeamsFragment fragment = new TeamsFragment();
                fragment.setArguments(bundle);
                replace(fragment,R.id.container_home,((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction(),v.getContext().getString(R.string.tag_teams));



            }
        });

    }


    public void setCompetitionArrayList(ArrayList<Competition> competitions) {
        this.competitionArrayList = competitions;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (competitionArrayList != null) {
            return competitionArrayList.size();
        } else {
            return 0;
        }
    }

    public class CompetitionViewHolder extends RecyclerView.ViewHolder {
        private ItemCompetitionsBinding  itemCompetitionsBinding;
        public CompetitionViewHolder(@NonNull ItemCompetitionsBinding itemView) {
            super(itemView.getRoot());
            this.itemCompetitionsBinding = itemView;
        }
    }





}
