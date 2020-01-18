package com.example.football.adapters;

import android.app.Activity;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.football.R;
import com.example.football.databinding.ItemTeamsBinding;

import com.example.football.helper.Utils;
import com.example.football.model.teams.Team;
import com.example.football.ui.team.TeamFragment;

import java.util.ArrayList;

import static com.example.football.helper.HelperMethods.replace;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.TeamsViewHolder> {

    private ArrayList<Team> teamArrayList;



    Activity activity ;

    public TeamsAdapter(Activity activity) {
        this.activity = activity;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          ItemTeamsBinding itemTeamsBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_teams, parent, false);
        return new TeamsViewHolder(itemTeamsBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull final TeamsAdapter.TeamsViewHolder holder, int position) {

        final Team team = teamArrayList.get(position);
        holder.itemTeamsBinding.setTeams(team);

        String crestUrl = team.getCrestUrl();


        if (crestUrl!=null){

            try {

                if (!crestUrl.endsWith("svg")){
                    Glide.with(holder.itemView.getContext()).load(crestUrl).into(holder.itemTeamsBinding.imageTeam);

                }else {



                        Utils.fetchSvg(holder.itemView.getContext(), crestUrl,holder.itemTeamsBinding.imageTeam , activity);



                }
            }catch (Exception e){



            }


        }


        holder.itemTeamsBinding.imageTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = team.getId();
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                TeamFragment fragment = new TeamFragment();
                fragment.setArguments(bundle);

                replace(fragment,R.id.container_home,((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction(),v.getContext().getString(R.string.tag_team));


            }
        });



    }


    public void setTeamArrayList(ArrayList<Team> teams) {
        this.teamArrayList = teams;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (teamArrayList != null) {
            return teamArrayList.size();
        } else {
            return 0;
        }
    }

    public class TeamsViewHolder extends RecyclerView.ViewHolder {
        private ItemTeamsBinding itemTeamsBinding;
        public TeamsViewHolder(@NonNull ItemTeamsBinding itemView) {
            super(itemView.getRoot());
            this.itemTeamsBinding = itemView;
        }
    }





}
