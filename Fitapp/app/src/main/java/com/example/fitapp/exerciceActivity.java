package com.example.fitapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class exerciceActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercice);
        AsyncFlickrJSONData task = new AsyncFlickrJSONData(exerciceActivity.this);
        task.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=luke_skywalker&format=json");
    }
}
