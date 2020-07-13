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

class StateAdapter extends RecyclerView.Adapter<StateAdapter.StateViewHolder> {
    ArrayList<StateData> stateDataList;
    Context paren;

    public StateAdapter(Context c,ArrayList<StateData> sdl){
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
        StateData sd=stateDataList.get(position);
        holder.state.setText(sd.getState());
        holder.active.setText(String.valueOf(sd.getActive()));
        holder.confirmed.setText(String.valueOf(sd.getConfirmed()));
        holder.recovered.setText(String.valueOf(sd.getRecovered()));
        holder.deceased.setText(String.valueOf(sd.getDeceased()));
        holder.state.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    Toast.makeText(paren,""+stateDataList.get(position).listdistrictData,Toast.LENGTH_LONG).show();
                Intent intent=new Intent(paren,District.class);
               intent.putExtra("pos",position);
                paren.startActivity(intent);

            }
        });
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
