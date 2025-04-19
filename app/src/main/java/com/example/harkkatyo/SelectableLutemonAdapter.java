package com.example.harkkatyo;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SelectableLutemonAdapter extends RecyclerView.Adapter<SelectableLutemonViewHolder> {
    private ArrayList<Lutemon> lutemons;
    private Context context;

    public SelectableLutemonAdapter(Context context, ArrayList<Lutemon> lutemons) {
        this.context = context;
        this.lutemons = lutemons;
    }

    @NonNull
    @Override
    public SelectableLutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.selectable_lutemon_view, parent, false);
        return new SelectableLutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SelectableLutemonViewHolder holder, int position) {
        Lutemon l = lutemons.get(position);
        holder.nameText.setText(l.getName() + " (" + l.getColor() + ")");
        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(l.isSelected());
        holder.checkBox.setOnCheckedChangeListener(((buttonView, isChecked) -> {
            l.setSelected(isChecked);
        }));
    }

    public int getItemCount() {
        return lutemons.size();
    }

    public void updateData(ArrayList<Lutemon> newData) {
        this.lutemons = newData;
        notifyDataSetChanged();
    }

    public ArrayList<Lutemon> getSelectedLutemons() {
        ArrayList<Lutemon> selected = new ArrayList<>();
        for (Lutemon lutemon : lutemons) {
            if (lutemon.isSelected()) {
                selected.add(lutemon);
            }
        }
        return selected;
    }

    public void clearSelections() {
        for (Lutemon lutemon : lutemons) {
            lutemon.setSelected(false);
        }
        notifyDataSetChanged();
    }

}
