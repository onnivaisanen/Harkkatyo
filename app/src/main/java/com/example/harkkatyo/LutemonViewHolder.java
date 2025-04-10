package com.example.harkkatyo;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LutemonViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView NameText, AttackText, DefenseText, LifeText, ExperienceText;

    public LutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        NameText = itemView.findViewById(R.id.NameText);
        AttackText = itemView.findViewById(R.id.AttackText);
        DefenseText = itemView.findViewById(R.id.DefenseText);
        LifeText = itemView.findViewById(R.id.LifeText);
        ExperienceText = itemView.findViewById(R.id.ExperienceText);
    }
}
