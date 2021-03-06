package com.example.fitapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class AsyncImageJSONData extends AsyncTask<String, Void, JSONObject> {

    private AppCompatActivity myActivity;

    public AsyncImageJSONData(exerciceActivity mainActivity) {
        myActivity = mainActivity;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

        URL url = null;
        HttpURLConnection urlConnection = null;
        String result = null;
        try {
            url = new URL(strings[0]);
            urlConnection = (HttpURLConnection) url.openConnection(); // Open
            InputStream in = new BufferedInputStream(urlConnection.getInputStream()); // Stream

            result = readStream(in); // Read stream
        }
        catch (MalformedURLException e) { e.printStackTrace(); }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        JSONObject json = null;
        try {
            json = new JSONObject(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return json; // returns the result
    }

    @Override
    protected void onPostExecute(JSONObject s) {

        ListView list = myActivity.findViewById(R.id.listview);
        BitmapAdapter tableau = new BitmapAdapter(list.getContext());
        list.setAdapter(tableau);

        try {
            JSONArray items = s.getJSONArray("image_results");
            for (int i = 0; i<3; i++)
            {
                JSONObject image_entry = items.getJSONObject(i);
                String urlmedia = image_entry.getString("sourceUrl");
                Log.i("CIO", "URL media: " + urlmedia);

                // Downloading image
                AsyncBitmapDownloader abd = new AsyncBitmapDownloader(tableau);
                abd.execute(urlmedia);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private String readStream(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader r = new BufferedReader(new InputStreamReader(is),1000);
        for (String line = r.readLine(); line != null; line =r.readLine()){
            sb.append(line);
        }
        is.close();

        // Extracting the JSON object from the String
        String jsonextracted = sb.toString();
        //Log.i("CIO", jsonextracted);
        return jsonextracted;
    }


}