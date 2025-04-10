package com.example.harkkatyo;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectableLutemonViewHolder extends RecyclerView.ViewHolder {
    CheckBox checkBox;
    TextView nameText;

    public SelectableLutemonViewHolder(@NonNull View itemView) {
        super(itemView);
        checkBox = itemView.findViewById(R.id.lutemonCheckbox);
        nameText = itemView.findViewById(R.id.lutemonNameText);
    }
}
