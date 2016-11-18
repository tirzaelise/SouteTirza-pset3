package com.example.tirza.soutetirza_pset3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

/**
 * This file shows the watch list to the user.
 *
 * Native App Studio, Tirza Soute
 */

public class WatchList extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        SharedPreferences sharedPrefs = this.getSharedPreferences("watchList",
                                                                  Context.MODE_PRIVATE);
        ArrayList<String> savedMovies = getSavedMovies(sharedPrefs);
        createWatchList(savedMovies);
    }

    /** Get the movies that are saved in the user's shared preferences and save them in an
     *  ArrayList*/
    public ArrayList<String> getSavedMovies(SharedPreferences sharedPrefs) {
        Map<String, ?> keys = sharedPrefs.getAll();
        ArrayList<String> savedMovies = new ArrayList<>();

        // Put all movies in an ArrayList
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            String movie = sharedPrefs.getString(entry.getKey(), "");
            savedMovies.add(movie);
        }
        return savedMovies;
    }

    /** Create a ListView that holds the saved movies */
    public void createWatchList(ArrayList<String> savedMovies) {
        listView = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row_view,
                                                          R.id.listViewItem, savedMovies);
        listView.setAdapter(adapter);
    }

    /** Delete the movie from the watch list if the delete button is clicked and restart the current
     *  activity to refresh the watch list */
    public void deleteSaved(View view) {
        // Find out what movie was clicked
        RelativeLayout layout = (RelativeLayout) view.getParent();
        TextView textView = (TextView) layout.getChildAt(0);
        String selectedMovie = textView.getText().toString();
        // Delete clicked movie from watch list
        SharedPreferences sharedPrefs = this.getSharedPreferences("watchList",
                Context.MODE_PRIVATE);
        sharedPrefs.edit().remove(selectedMovie).apply();
        Toast.makeText(this, "Movie deleted", Toast.LENGTH_SHORT).show();
        // Restart intent
        Intent currentIntent = getIntent();
        finish();
        startActivity(currentIntent);
}
}
