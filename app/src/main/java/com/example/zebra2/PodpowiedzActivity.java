package com.example.zebra2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class PodpowiedzActivity extends AppCompatActivity {
    ImageView imageViewPodp;

    TextView textViewPodp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_podpowiedz);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewPodp = findViewById(R.id.textViewPodp);
        imageViewPodp = findViewById(R.id.imageViewPodp);
        ArrayList<pytanie> pytanieArrayList = MainActivity.dodajPytanie();
        Intent intent = getIntent();
        int ktore = intent.getIntExtra("NR", 0);
        Toast.makeText(this, pytanieArrayList.get(ktore).getPodpowiedz(), Toast.LENGTH_SHORT).show();
        textViewPodp.setText(pytanieArrayList.get(ktore).getPodpowiedz());
        imageViewPodp.setImageResource(int)pytanieArrayList.get(ktore).getIdZdjecia();
    }
}