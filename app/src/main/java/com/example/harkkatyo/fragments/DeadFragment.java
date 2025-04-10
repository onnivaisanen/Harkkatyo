package com.example.harkkatyo.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harkkatyo.Lutemon;
import com.example.harkkatyo.LutemonListAdapter;
import com.example.harkkatyo.R;
import com.example.harkkatyo.Storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DeadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeadFragment extends Fragment {
    private RecyclerView recyclerView;
    private LutemonListAdapter adapter;

    public DeadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_dead, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerViewDead);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<Lutemon> deadLutemons = new ArrayList<>(getDeadLutemons(Storage.getInstance().getAllLutemons()));
        adapter = new LutemonListAdapter(getContext(), deadLutemons);
        recyclerView.setAdapter(adapter);

        return rootView;
    }

    public void onResume() {
        super.onResume();
        ArrayList<Lutemon> deadLutemons = new ArrayList<>(getDeadLutemons(Storage.getInstance().getAllLutemons()));
        adapter.updateData(deadLutemons);
    }

    private List<Lutemon> getDeadLutemons(List<Lutemon> lutemons) {
        return lutemons.stream().filter(l -> l.getLife() <= 0).collect(Collectors.toList());
    }
}