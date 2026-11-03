package com.example.zebra2;

import static android.view.View.INVISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static ArrayList<pytanie> pytania;
    private TextView textView;
    private ImageView imageView;
    private Button buttonTak, buttonNie, buttonDalej, buttonPodpowiedz;
    private int aktualne =0;
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
        dodajPytanie();
        textView = findViewById(R.id.textView2);
        buttonTak = findViewById(R.id.button);
        buttonNie = findViewById(R.id.button2);
        buttonDalej = findViewById(R.id.button4);
        buttonPodpowiedz = findViewById(R.id.button3);
        imageView = findViewById(R.id.imageView);
        wypiszPytanie(0);

        buttonDalej.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        aktualne++;
                        if(aktualne == pytania.size()) {
                            buttonDalej.setVisibility(INVISIBLE);
                            int punkty = 0;
                            for (pytanie pytania : pytania) {
                                if (pytania.isCzyPoprawnie()) {
                                    punkty++;
                                }
                            }
                            textView.setText("Koniec tekstu. Wynik: " + punkty);

                        }else {
                            wypiszPytanie(aktualne);
                        }

                    }
                }
        );

        buttonNie.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sprawdzOpdowiedz(false);
                    }
                }
        );

        buttonTak.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sprawdzOpdowiedz(true);
                    }
                }
        );
        buttonPodpowiedz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,PodpowiedzActivity.class);
                        intent.putExtra("NR", aktualne);
                        startActivity(intent);
                    }
                }
        );
    }
    private void sprawdzOpdowiedz(boolean odp){
        if(pytania.get(aktualne).isPoprawnaodpowiedz() == odp) {
            pytania .get(aktualne).setCzyPoprawnie(true);
            Toast.makeText(this, "Poprawna odpowiedź", Toast.LENGTH_SHORT).show();
        }
        else {

            pytania.get(aktualne).setCzyPoprawnie(false);
            Toast.makeText(this, "Błędna odpowiedź!", Toast.LENGTH_SHORT).show();
        }
        wypiszPytanie(aktualne);

    }
    private void wypiszPytanie(int ktore) {
        pytanie pytanie = pytania.get(ktore);
        textView.setText(pytanie.getTrescPytania());
        imageView.setImageResource((int)(pytanie.getIdZdjecia()));
    }
    protected static ArrayList<pytanie> dodajPytanie() {
        pytania = new ArrayList<>();
        pytania.add(new pytanie(R.drawable.tygrys, "czy tygrys jest królem zwierząt?", true, "a pamiętasz bajke o simbie"));
        pytania.add(new pytanie(R.drawable.zyrafa, "Czy żyrafa śpi zawsze na stojąco?", false, "Może spi może nie"));
        pytania.add(new pytanie(R.drawable.okapi, "Czy okapi było znane w 17 wieku?", false, "Okapi zaobserwowano pierwszy raz w lasach równikowych w 1985 roku."));
        return pytania;
    }

}