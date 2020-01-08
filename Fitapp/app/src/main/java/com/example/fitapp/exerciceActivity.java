package com.example.fitapp;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class exerciceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        AsyncImageJSONData task = new AsyncImageJSONData(exerciceActivity.this);

        DBHandler dbHandler = new DBHandler(this, null, null, 14);
        if ( dbHandler.getProfilesCount() == 0){
            dbHandler.loadAll();
        }


        String exercices[] ={"push+up","abs+crunch","squats","crunch+fitness","plank","skipping+rope","running"};
        String exo= exercices[(int)(Math.random()*exercices.length)];
        task.execute("https://app.zenserp.com/api/v2/search?q="+exo+"&hl=en&gl=US&location=United%20States&search_engine=google.com&tbm=isch&apikey=ab1f1ce0-3010-11ea-bdea-9958949cd03d");

        TextView t1 = (TextView) findViewById(R.id.textView);
        t1.setText("Aujourd'hui : "+exo+ " ! Faites 3 séries de 10 répétions");

        //Text de la citation

        TextView q = (TextView) findViewById(R.id.quoteZone);
        Quote qu = dbHandler.findHandlerById((int)(Math.random()*exercices.length));
        q.setText(qu.getQuoteText());

        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();





    }


}
