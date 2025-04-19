package com.example.harkkatyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LutemonStatsAdapter extends RecyclerView.Adapter<LutemonStatsViewHolder> {

    private Context context;
    private ArrayList<Lutemon> lutemons;

    public LutemonStatsAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public LutemonStatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonStatsViewHolder(LayoutInflater.from(context).inflate(R.layout.statistics_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonStatsViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.StatsNameText.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
        holder.FightsText.setText("Taistelut: " + lutemon.getFights());
        holder.WinsText.setText("Voitot: " + lutemon.getWins());
        holder.LossesText.setText("Häviöt: " + lutemon.getLosses());
        holder.TrainingDaysText.setText("Treenipäivät: " + lutemon.getTrainingDays());
        holder.StatsImageView.setImageResource(lutemon.getImageResource());

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }
}
