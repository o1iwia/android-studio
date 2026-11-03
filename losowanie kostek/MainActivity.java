package com.example.losowaniekostek;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextLiczbaKostek;
    private Button buttonLosuj;
    private Button buttonRosnaco;
    private Button buttonMalejaco;
    private GridLayout gridKostki;
    private TextView textViewWyniki;

    private final List<Integer> wyniki = new ArrayList<>();
    private final Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        buttonLosuj = findViewById(R.id.buttonLosuj);
        buttonRosnaco = findViewById(R.id.buttonRosnaco);
        buttonMalejaco = findViewById(R.id.buttonMalejaco);
        gridKostki = findViewById(R.id.gridKostki);
        textViewWyniki = findViewById(R.id.textViewWyniki);

        buttonLosuj.setOnClickListener(v -> losuj());

        buttonRosnaco.setOnClickListener(v -> {
            if (wyniki.isEmpty()) {
                Toast.makeText(this, "najpierw wylosuj kostki", Toast.LENGTH_SHORT).show();
            } else {
                sortujRosnaco();
                odswiezWidok();
            }
        });

        buttonMalejaco.setOnClickListener(v -> {
            if (wyniki.isEmpty()) {
                Toast.makeText(this, "najpierw wylosuj kostki", Toast.LENGTH_SHORT).show();
            } else {
                sortujMalejaco();
                odswiezWidok();
            }
        });
    }

    private void losuj() {
        int liczba;
        try {
            liczba = Integer.parseInt(editTextLiczbaKostek.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "bledne dane", Toast.LENGTH_SHORT).show();
            return;
        }
        if (liczba <= 0) {
            Toast.makeText(this, "podaj poprawna liczbe kostek", Toast.LENGTH_SHORT).show();
            return;
        }
        if (liczba > 100) {
            Toast.makeText(this, "max 100 kostek", Toast.LENGTH_SHORT).show();
            return;
        }
        wyniki.clear();

        for (int i = 0; i < liczba; i++) {
            wyniki.add(random.nextInt(6) + 1);
        }
        odswiezWidok();
    }

    private void sortujRosnaco() {
        for (int i = 0; i < wyniki.size() - 1; i++) {
            for (int j = 0; j < wyniki.size() - 1 - i; j++) {
                if (wyniki.get(j) > wyniki.get(j + 1)) {
                    int temp = wyniki.get(j);
                    wyniki.set(j, wyniki.get(j + 1));
                    wyniki.set(j + 1, temp);
                }
            }
        }
    }

    private void sortujMalejaco() {
        for (int i = 0; i < wyniki.size() - 1; i++) {
            for (int j = 0; j < wyniki.size() - 1 - i; j++) {
                if (wyniki.get(j) < wyniki.get(j + 1)) {
                    int temp = wyniki.get(j);
                    wyniki.set(j, wyniki.get(j + 1));
                    wyniki.set(j + 1, temp);
                }
            }
        }
    }

    private void odswiezWidok() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wyniki.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(wyniki.get(i));
        }
        textViewWyniki.setText(sb.toString());
        gridKostki.removeAllViews();

        int kolumny = 5;
        gridKostki.setColumnCount(kolumny);

    }
}
