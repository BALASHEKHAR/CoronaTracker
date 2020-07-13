package com.example.coronatracker;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.StateViewHolder> {
    ArrayList<DistrictData> stateDataList;
    Context paren;

    public DistrictAdapter(Context c,ArrayList<DistrictData> sdl){
        stateDataList=sdl;
        paren=c;

    }
    @NonNull
    @Override
    public StateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(paren).inflate(R.layout.card_row,parent,false);
        return new StateViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull StateViewHolder holder, final int position) {
        DistrictData sd=stateDataList.get(position);
        holder.state.setText(sd.getDistrictname());
        holder.active.setText(String.valueOf(sd.getActive()));
        holder.confirmed.setText(String.valueOf(sd.getConfirmed()));
        holder.recovered.setText(String.valueOf(sd.getRecovered()));
        holder.deceased.setText(String.valueOf(sd.getDeceased()));

    }

    @Override
    public int getItemCount() {
        return stateDataList.size();
    }

    public static class StateViewHolder extends RecyclerView.ViewHolder
    {
        TextView state;
        TextView active;
        TextView confirmed;
        TextView recovered;
        TextView deceased;

        public StateViewHolder(@NonNull View itemView) {
            super(itemView);
            state=itemView.findViewById(R.id.state1);
            active=itemView.findViewById(R.id.active1);
            confirmed=itemView.findViewById(R.id.confirmed1);
            recovered=itemView.findViewById(R.id.recovered1);
            deceased=itemView.findViewById(R.id.deceased1);
        }
    }
}
