package com.example.fitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Déclaration des boutons*/
        final Button dataButton = findViewById(R.id.dataButton);
        final Button exerciceButton = findViewById(R.id.exerciceButton);

        /*Appel du layout des données*/
        dataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, dataActivity.class);
                //intent.putExtra("Music State", musicState);
                //intent.putExtra("bruitages", bruitagesState);
                //System.out.println(musicState);
                startActivityForResult(intent, 0);
            }
        });
        /*Appel du layout des exercices*/
        exerciceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, exerciceActivity.class);
                startActivityForResult(intent, 0);

            }
        });
    }
}
