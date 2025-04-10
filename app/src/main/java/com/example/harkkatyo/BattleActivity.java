package com.example.harkkatyo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BattleActivity extends AppCompatActivity {
    private ArrayList<Lutemon> fightLutemons;
    private Lutemon lutemon1, lutemon2;
    private TextView battleLogView;
    private Button startBattleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_battle);

        fightLutemons = Storage.getInstance().getFightLutemons();
        battleLogView = findViewById(R.id.battleLogText);
        startBattleButton = findViewById(R.id.startBattleButton);

        RecyclerView recyclerView = findViewById(R.id.battleLutemonRecyclerView);
        BattleLutemonAdapter adapter = new BattleLutemonAdapter(this, fightLutemons, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        startBattleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lutemon1 != null && lutemon2 != null) {
                    startBattle();
                } else {
                    battleLogView.append("Valitse kaksi lutemonia\n");
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setSelectedLutemons(Lutemon lutemon1, Lutemon lutemon2) {
        this.lutemon1 = lutemon1;
        this.lutemon2 = lutemon2;

        if (lutemon1 != null && lutemon2 != null) {
            battleLogView.append("Valitsit " + lutemon1.getName() + " ja " + lutemon2.getName() + " taisteluun.\n");
        }
    }

    public void startBattle() {
        if (lutemon1 != null && lutemon2 != null) {
            String battleResult = Battle.fight(lutemon1, lutemon2);
            battleLogView.append(battleResult + "\n");
            removeLutemonsFromView();
        }
    }

    private void removeLutemonsFromView() {
        if (lutemon1 != null) {
            fightLutemons.remove(lutemon1);
        }
        if (lutemon2 != null) {
            fightLutemons.remove(lutemon2);
        }

        RecyclerView recyclerView = findViewById(R.id.battleLutemonRecyclerView);
        BattleLutemonAdapter adapter = (BattleLutemonAdapter) recyclerView.getAdapter();

        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }

    }

}