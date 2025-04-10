package com.example.harkkatyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BattleLutemonAdapter extends RecyclerView.Adapter<BattleLutemonAdapter.BattleViewHolder> {
    private ArrayList<Lutemon> lutemons;
    private Context context;
    private BattleActivity battleActivity;
    private ArrayList<Lutemon> selected = new ArrayList<>();

    public BattleLutemonAdapter(Context context, ArrayList<Lutemon> lutemons, BattleActivity battleActivity) {
        this.context = context;
        this.lutemons = lutemons;
        this.battleActivity = battleActivity;
    }

    @NonNull
    @Override
    public BattleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.selectable_lutemon_view, parent, false);
        return new BattleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BattleLutemonAdapter.BattleViewHolder holder, int position) {
        Lutemon l = lutemons.get(position);
        holder.nameText.setText(l.getName() + " (" + l.getColor() + ") HP: " + l.getLife() + "/" + l.getMaxLife());
        holder.checkBox.setChecked(selected.contains(l));
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                if (selected.size() < 2) {
                    selected.add(l);
                } else {
                    buttonView.setChecked(false);
                    Toast.makeText(context, "Valitse vain kaksi lutemonia", Toast.LENGTH_SHORT).show();
                }

                if (selected.size() == 2) {
                    battleActivity.setSelectedLutemons(selected.get(0), selected.get(1));
                } else {
                    battleActivity.setSelectedLutemons(null, null);
                }
            }
        });
    }

    public int getItemCount() {
        return lutemons.size();
    }

    public static class BattleViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView nameText;

        public BattleViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.lutemonCheckbox);
            nameText = itemView.findViewById(R.id.lutemonNameText);
        }
    }
}
