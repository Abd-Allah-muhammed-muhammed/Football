package com.example.football.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football.R;
import com.example.football.databinding.ItemPlayerBinding;
import com.example.football.model.Squad;

import java.util.ArrayList;


public class PlayerInfoAdapter extends RecyclerView.Adapter<PlayerInfoAdapter.PlayerInfoHolder> {

    private ArrayList<Squad>  squadArrayList;




    @NonNull
    @Override
    public PlayerInfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          ItemPlayerBinding itemPlayerBinding =
                DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                        R.layout.item_player, parent, false);
        return new PlayerInfoHolder(itemPlayerBinding);
    }


    @Override
    public void onBindViewHolder(@NonNull final PlayerInfoAdapter.PlayerInfoHolder holder, int position) {

        Squad squad = squadArrayList.get(position);
        holder.itemPlayerBinding.setPlayer(squad);





    }


    public void setSquadArrayList(ArrayList<Squad>  squads) {
        this.squadArrayList = squads;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (squadArrayList != null) {
            return squadArrayList.size();
        } else {
            return 0;
        }
    }

    public class PlayerInfoHolder extends RecyclerView.ViewHolder {
        private ItemPlayerBinding itemPlayerBinding;
        public PlayerInfoHolder(@NonNull ItemPlayerBinding itemView) {
            super(itemView.getRoot());
            this.itemPlayerBinding = itemView;
        }
    }





}
