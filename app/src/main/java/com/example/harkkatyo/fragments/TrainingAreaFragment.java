package com.example.harkkatyo.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.harkkatyo.Lutemon;
import com.example.harkkatyo.SelectableLutemonAdapter;
import com.example.harkkatyo.Storage;
import com.example.harkkatyo.R;

import java.util.ArrayList;

public class TrainingAreaFragment extends Fragment {
    private ArrayList<Lutemon> trainingLutemons;
    private SelectableLutemonAdapter adapter;

    @Override
    public void onResume() {
        super.onResume();
        adapter.updateData(Storage.getInstance().getTrainingLutemons());
        adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_training_area, container, false);

        trainingLutemons = Storage.getInstance().getTrainingLutemons();
        Log.d("TrainingAreaFragment", "Lutemonit trainingArealla: " + trainingLutemons.size());  // Lisätty logi

        RecyclerView trainingRV = rootView.findViewById(R.id.trainingRV);
        trainingRV.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new SelectableLutemonAdapter(getContext(), trainingLutemons);
        trainingRV.setAdapter(adapter);

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

            adapter.updateData(Storage.getInstance().getTrainingLutemons());
        });

        Button trainButton = rootView.findViewById(R.id.trainButton);
        trainButton.setOnClickListener(v -> {

            ArrayList<Lutemon> selectedLutemons = adapter.getSelectedLutemons();

            if (selectedLutemons.isEmpty()) {
                Toast.makeText(getContext(), "Valitse ainakin yksi Lutemon.", Toast.LENGTH_SHORT).show();
                return;
            }

            for (Lutemon lutemon : selectedLutemons) {
                lutemon.train();
                lutemon.setTrainingDays(lutemon.getTrainingDays() + 1);
            }

            Toast.makeText(getContext(), "Lutemonit treenasivat", Toast.LENGTH_SHORT).show();
            adapter.clearSelections();

            adapter.updateData(Storage.getInstance().getTrainingLutemons());
        });

        return rootView;
    }
}
