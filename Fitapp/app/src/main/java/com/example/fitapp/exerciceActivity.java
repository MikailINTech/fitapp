package com.example.fitapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class exerciceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        AsyncImageJSONData task = new AsyncImageJSONData(exerciceActivity.this);

        String exercices[] ={"push+up","abs","squats","crunch+fitness","plank","skipping+rope","running"};
        String exo= exercices[(int)(Math.random()*exercices.length)];
        task.execute("https://app.zenserp.com/api/v2/search?q="+exo+"&hl=en&gl=US&location=United%20States&search_engine=google.com&tbm=isch&apikey=ab1f1ce0-3010-11ea-bdea-9958949cd03d");

        TextView t1 = (TextView) findViewById(R.id.textView);
        t1.setText("Aujourd'hui : "+exo+ " ! Faites 3 séries de 10 répétions");


    }


}
