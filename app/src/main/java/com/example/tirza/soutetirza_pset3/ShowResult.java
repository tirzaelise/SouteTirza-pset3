package com.example.tirza.soutetirza_pset3;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This file shows the obtained results to the user.
 *
 * Native App Studio, Tirza Soute
 */


public class ShowResult extends AppCompatActivity {
    MovieData movieData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_result);

        Bundle extras = getIntent().getExtras();
        movieData = (MovieData) extras.getSerializable("movieData");
        showData();
    }

    /** Show the data to the user */
    public void showData() {
        // Get all the obtained data
        String title = movieData.getTitle();
        String plot = movieData.getPLot();
        String year = movieData.getYear();
        String director = movieData.getDirector();
        String actors = movieData.getActors();
        String urlToPoster = movieData.getPosterUrl();
        // Get all the text and image views and the save button
        TextView titleView = (TextView) findViewById(R.id.showTitle);
        TextView plotView = (TextView) findViewById(R.id.showPlot);
        TextView yearView = (TextView) findViewById(R.id.showYear);
        TextView directorView = (TextView) findViewById(R.id.showDirector);
        TextView actorsView = (TextView) findViewById(R.id.showActors);
        ImageView posterView = (ImageView) findViewById(R.id.showPoster);
        TextView noResultsView = (TextView) findViewById(R.id.noResults);
        Button saveMovieButton = (Button) findViewById(R.id.saveMovie);

        // If there were results obtained, show them to the user
        if (!title.isEmpty()) {
            plotView.setText(plot);
            yearView.setText(year);
            directorView.setText(director);
            actorsView.setText(actors);
            PosterAsyncTask asyncTask = new PosterAsyncTask(this);
            asyncTask.execute(urlToPoster);
            titleView.setText(title);
        // If no results were obtained, hide the image and text views and the save button
        } else {
            noResultsView.setVisibility(View.VISIBLE);
            titleView.setVisibility(View.GONE);
            plotView.setVisibility(View.GONE);
            yearView.setVisibility(View.GONE);
            directorView.setVisibility(View.GONE);
            actorsView.setVisibility(View.GONE);
            saveMovieButton.setVisibility(View.GONE);
            posterView.setVisibility(View.GONE);
        }
    }

    /** Save the movie to shared preferences if the save button is clicked */
    public void saveMovie(View view) {
        String title = movieData.getTitle();
        SharedPreferences sharedPrefs = this.getSharedPreferences("watchList",
                                                                  Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPrefs.edit();
        // Use the title both as key and as value so that the same movie can't be added twice
        editor.putString(title, title);
        editor.apply();
        Toast.makeText(this, "Movie added", Toast.LENGTH_SHORT).show();
    }
}