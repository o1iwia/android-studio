package com.oliwia.rejestracja;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText imieid;
    private EditText nazwiskoid;
    private EditText emailid;
    private EditText hasloid;
    private Button przyciskid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imieid = findViewById(R.id.imieid);
        nazwiskoid = findViewById(R.id.nazwiskoid);
        emailid = findViewById(R.id.emailid);
        hasloid = findViewById(R.id.hasloid);
        przyciskid = findViewById(R.id.przyciskid);
        String hasloWzor = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$";
        przyciskid.setOnClickListener(v -> {
            String imie = imieid.getText().toString().trim();//trim usuwa zbedne spacje
            String nazwisko = nazwiskoid.getText().toString().trim();
            String email = emailid.getText().toString().trim();
            String haslo = hasloid.getText().toString().trim();

            if (imie.isEmpty() || nazwisko.isEmpty() || email.isEmpty() || haslo.isEmpty()) {
                Toast.makeText(MainActivity.this, "uzupelnij wszystkie pola", Toast.LENGTH_SHORT).show();
            }else if (!email.contains("@") || !email.contains(".")) {
                Toast.makeText(MainActivity.this, "podaj poprawny adres email", Toast.LENGTH_SHORT).show();
            } else if (!haslo.matches(hasloWzor)) {
                Toast.makeText(this, "haslo nie spelnia wymagan", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "dane sa poprawne", Toast.LENGTH_SHORT).show();
            }
        });
    }
}