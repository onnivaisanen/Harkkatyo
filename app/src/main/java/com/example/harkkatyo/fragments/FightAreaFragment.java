package com.example.harkkatyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.harkkatyo.Lutemon;
import com.example.harkkatyo.R;
import com.example.harkkatyo.SelectableLutemonAdapter;
import com.example.harkkatyo.Storage;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FightAreaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FightAreaFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Lutemon> fightLutemons;
    private SelectableLutemonAdapter adapter;

    public FightAreaFragment() {}

    @Override
    public void onResume() {
        super.onResume();
        adapter.updateData(Storage.getInstance().getFightLutemons());
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_fight_area, container, false);

        fightLutemons = Storage.getInstance().getFightLutemons();
        RecyclerView FightRV = rootView.findViewById(R.id.FightRV);
        FightRV.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SelectableLutemonAdapter(getContext(), fightLutemons);
        FightRV.setAdapter(adapter);

        RadioGroup transferLocationRadioGroup = rootView.findViewById(R.id.transferLocationRadioGroup);
        Button transferButton = rootView.findViewById(R.id.transferButton);
        transferButton.setOnClickListener(v -> {
            int selectedId = transferLocationRadioGroup.getCheckedRadioButtonId();

            if (selectedId == -1) {
                Toast.makeText(getContext(), "Valitse sijainti ensin.", Toast.LENGTH_SHORT).show();
                return;
            }

            RadioButton selectedRadioButton = rootView.findViewById(selectedId);
            String selectedLocation = selectedRadioButton.getText().toString();

            ArrayList<Lutemon> selectedLutemons = adapter.getSelectedLutemons();

            if (selectedLutemons.isEmpty()) {
                Toast.makeText(getContext(), "Valitse ainakin yksi Lutemon.", Toast.LENGTH_SHORT).show();
                return;
            }

            for (Lutemon lutemon : selectedLutemons) {
                Storage.getInstance().moveLutemonToLocation(lutemon, selectedLocation);
            }

            Toast.makeText(getContext(), "Lutemonit siirrettiin " + selectedLocation, Toast.LENGTH_SHORT).show();

            // Päivitä lista
            adapter.updateData(Storage.getInstance().getFightLutemons());
        });

        return rootView;


    }
}