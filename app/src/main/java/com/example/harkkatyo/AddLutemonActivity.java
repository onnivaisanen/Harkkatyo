package com.example.harkkatyo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AddLutemonActivity extends AppCompatActivity {
    private EditText LutemonNameEditText;
    private RadioGroup AddLutemonRadioGroup;
    private Storage storage;
    private Button ConfirmAddLutemonBtn;
    private TextView StatusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_lutemon);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        LutemonNameEditText = findViewById(R.id.LutemonNameEditText);
        AddLutemonRadioGroup = findViewById(R.id.AddLutemonRadioGroup);
        ConfirmAddLutemonBtn = findViewById(R.id.ConfirmAddLutemonBtn);
        StatusText = findViewById(R.id.StatusText);

        storage = Storage.getInstance();

        ConfirmAddLutemonBtn.setOnClickListener(v -> {
            String name = LutemonNameEditText.getText().toString();
            int selectedColorId = AddLutemonRadioGroup.getCheckedRadioButtonId();
            if (name.isEmpty() || selectedColorId == -1) return;
            RadioButton selectedColor = findViewById(selectedColorId);
            String color = selectedColor.getText().toString();
            Lutemon newLutemon = null;
            Log.d("AddLutemonActivity", "Lutemon name: " + name + ", Color: " + color);
            switch (color) {
                case "Valkoinen (Armatuuri)":
                    newLutemon = new White(name);
                    break;
                case "Vihreä (Sätky)":
                    newLutemon = new Green(name);
                    break;
                case "Pinkki (Lateksii)":
                    newLutemon = new Pink(name);
                    break;
                case "Oranssi (KRK)":
                    newLutemon = new Orange(name);
                    break;
                case "Musta (KeTeK)":
                    newLutemon = new Black(name);
                    break;
                default:
                    Log.d("AddLutemonActivity", "Invalid color selected");
            }
            if (newLutemon != null) {
                storage.addLutemon(newLutemon);
                StatusText.setText("Lutemon lisättiin onnistuneesti!");
                StatusText.setVisibility(View.VISIBLE);

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                StatusText.setVisibility(View.GONE);
                            }
                        },
                        3000
                );
            }
        });
    }
}