package com.example.harkkatyo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonStatsViewHolder extends RecyclerView.ViewHolder {
    TextView StatsNameText, FightsText, WinsText, LossesText, TrainingDaysText;
    ImageView StatsImageView;

    public LutemonStatsViewHolder(@NonNull View itemView) {
        super(itemView);
        StatsNameText = itemView.findViewById(R.id.StatsNameText);
        FightsText = itemView.findViewById(R.id.FightsText);
        WinsText = itemView.findViewById(R.id.WinsText);
        LossesText = itemView.findViewById(R.id.LossesText);
        TrainingDaysText = itemView.findViewById(R.id.TrainingDaysText);
        StatsImageView = itemView.findViewById(R.id.StatsImageView);
    }
}
