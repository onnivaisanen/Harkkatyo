package com.example.harkkatyo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class LutemonListAdapter extends RecyclerView.Adapter<LutemonViewHolder> {
    private Context context;
    private ArrayList<Lutemon> lutemons = new ArrayList<>();

    public LutemonListAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LutemonViewHolder(LayoutInflater.from(context).inflate(R.layout.lutemon_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemons.get(position);
        holder.NameText.setText(lutemon.getName() + " (" + lutemon.getColor() + ")");
        holder.AttackText.setText("Hyökkäys: " + String.valueOf(lutemon.getAttack()));
        holder.DefenseText.setText("Puolustus: " + String.valueOf(lutemon.getDefense()));
        holder.LifeText.setText("Elämä: " + String.valueOf(lutemon.getLife()) + "/" + String.valueOf(lutemons.get(position).getMaxLife()));
        holder.ExperienceText.setText("Kokemus: " + String.valueOf(lutemon.getExperience()));

        holder.imageView.setImageResource(lutemon.getImageResource());

    }

    @Override
    public int getItemCount() {
        return lutemons.size();
    }

    public void updateData(ArrayList<Lutemon> lutemons) {
        this.lutemons = lutemons;
        notifyDataSetChanged();
    }
}
