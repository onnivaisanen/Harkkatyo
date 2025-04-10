package com.example.harkkatyo;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListLutemonActivity extends AppCompatActivity {
    private RecyclerView LutemonListRV;
    private LutemonListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_lutemon);

        LutemonListRV = findViewById(R.id.LutemonListRV);
        LutemonListRV.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<Lutemon> aliveLutemons = new ArrayList<>(getAliveLutemons(Storage.getInstance().getAllLutemons()));
        adapter = new LutemonListAdapter(getApplicationContext(), aliveLutemons);
        LutemonListRV.setAdapter(adapter);

        Storage storage = Storage.getInstance();
        LutemonListRV = findViewById(R.id.LutemonListRV);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Log.d("ListLutemonActivity", "Lutemoneja listassa: " + Storage.getInstance().getAllLutemons().size());
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Lutemon> aliveLutemons = new ArrayList<>(getAliveLutemons(Storage.getInstance().getAllLutemons()));
        if (LutemonListRV.getAdapter() instanceof LutemonListAdapter) {
            ((LutemonListAdapter) LutemonListRV.getAdapter()).updateData(aliveLutemons);
        }
    }

    private List<Lutemon> getAliveLutemons(List<Lutemon> lutemons) {
        return lutemons.stream().filter(lutemon -> lutemon.getLife() > 0).collect(Collectors.toList());
    }
}