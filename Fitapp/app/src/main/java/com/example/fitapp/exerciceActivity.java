package com.example.fitapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class exerciceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        AsyncFlickrJSONData task = new AsyncFlickrJSONData(exerciceActivity.this);
        task.execute("https://app.zenserp.com/api/v2/search?q=muscle+up&hl=en&gl=US&location=United%20States&search_engine=google.com&tbm=isch&apikey=ab1f1ce0-3010-11ea-bdea-9958949cd03d");
    }
}
