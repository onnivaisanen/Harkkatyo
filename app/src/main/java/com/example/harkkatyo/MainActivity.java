package com.example.harkkatyo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        context = MainActivity.this;
    }

    public void switchToAddLutemonActivity(View view) {
        Intent intent = new Intent(this, AddLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToListLutemonActivity(View view) {
        Intent intent = new Intent(this, ListLutemonActivity.class);
        startActivity(intent);
    }

    public void switchToMoveLutemonsActivity(View view) {
        Intent intent = new Intent(this, MoveLutemonsActivity.class);
        startActivity(intent);
    }

    public void switchToBattleActivity(View view) {
        Intent intent = new Intent(this, BattleActivity.class);
        startActivity(intent);
    }

    public void saveLutemons(View view) {
        Storage.getInstance().saveLutemons(context);
    }

    public void loadLutemons(View view) {
        Storage.getInstance().loadLutemons(context);
    }
}