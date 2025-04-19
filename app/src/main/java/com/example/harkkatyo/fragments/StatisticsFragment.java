package com.example.harkkatyo.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.harkkatyo.Lutemon;
import com.example.harkkatyo.LutemonStatsAdapter;
import com.example.harkkatyo.R;
import com.example.harkkatyo.Storage;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StatisticsFragment extends Fragment {
    private RecyclerView recyclerView;
    private BarChart barChart;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_statistics, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerViewStats);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        barChart = rootView.findViewById(R.id.barChart);
        updateStats();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateStats();
    }

    private void updateStats() {
        LutemonStatsAdapter adapter = new LutemonStatsAdapter(getContext(), Storage.getInstance().getAllLutemons());
        recyclerView.setAdapter(adapter);
        HashMap<String, Integer> winsByColor = new HashMap<>();
        HashMap<String, Integer> lossesByColor = new HashMap<>();

        for (Lutemon lutemon : Storage.getInstance().getAllLutemons()) {
            String color = lutemon.getColor();
            winsByColor.put(color, winsByColor.getOrDefault(color, 0) + lutemon.getWins());
            lossesByColor.put(color, lossesByColor.getOrDefault(color, 0) + lutemon.getLosses());
        }

        ArrayList<BarEntry> winEntries = new ArrayList<>();
        ArrayList<BarEntry> lossEntries = new ArrayList<>();
        ArrayList<String> colors = new ArrayList<>();

        int index = 0;
        for (String color : winsByColor.keySet()) {
            winEntries.add(new BarEntry(index, winsByColor.getOrDefault(color, 0)));
            lossEntries.add(new BarEntry(index, lossesByColor.getOrDefault(color, 0)));
            colors.add(color);
            index++;
        }

        BarDataSet winDataSet = new BarDataSet(winEntries, "Voitot");
        winDataSet.setColor(Color.GREEN);
        winDataSet.setValueTypeface(Typeface.MONOSPACE);

        BarDataSet lossDataSet = new BarDataSet(lossEntries, "Häviöt");
        lossDataSet.setColor(Color.RED);
        lossDataSet.setValueTypeface(Typeface.MONOSPACE);

        BarData data = new BarData(winDataSet, lossDataSet);
        data.setBarWidth(0.35f);
        barChart.setData(data);

        XAxis xAxis = barChart.getXAxis();
        barChart.getXAxis().setAxisMinimum(0f);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(colors));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setGranularityEnabled(true);
        xAxis.setLabelRotationAngle(0f);

        xAxis.setLabelCount(colors.size());
        xAxis.setDrawGridLines(false);
        float groupWidth = data.getGroupWidth(0.2f, 0.05f);
        xAxis.setAxisMaximum(0f + groupWidth * colors.size());

        YAxis leftAxis = barChart.getAxisLeft();
        YAxis rightAxis = barChart.getAxisRight();
        rightAxis.setEnabled(false);
        leftAxis.setTypeface(Typeface.MONOSPACE);

        barChart.getDescription().setEnabled(false);
        barChart.setFitBars(true);
        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(3);
        barChart.groupBars(0f, 0.2f, 0.05f);
        barChart.invalidate();
    }
}