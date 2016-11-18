package com.example.tirza.soutetirza_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * This file uses the data data that was received from the HttpRequest file to create a MovieData
 * object.
 *
 * Native App Studio, Tirza Soute
 */

class MovieAsyncTask extends AsyncTask<String, String, String> {
    private UserInput activity;
    private Context context;

    /** Create a MovieAsyncTask constructor */
    MovieAsyncTask(UserInput activity) {
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

//    I deleted this, because I thought it was redundant.
//    protected void onPreExecute() {
//        Toast.makeText(context, "Getting data from server", Toast.LENGTH_LONG).show();
//    }

    /** Receive the data that was obtained using the HttpRequest file */
    protected String doInBackground(String... params) {
        return HttpRequest.downloadFromAPI(params);
    }

    /** Create a MovieData object */
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        String title = "";
        String plot = "";
        String year = "";
        String director = "";
        String actors = "";
        String urlToPoster = "";

        // Check if there were any search results
        if (!(result.equals("{\"Response\":\"False\",\"Error\":\"Movie not found!\"}"))) {
            // Try to create strings from the received results
            try {
                JSONObject readObject = new JSONObject(result);
                title = readObject.getString("Title");
                plot = readObject.getString("Plot");
                year = readObject.getString("Year");
                director = readObject.getString("Director");
                actors = readObject.getString("Actors");
                urlToPoster = readObject.getString("Poster");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(context, "No data was found", Toast.LENGTH_LONG).show();
        }
        MovieData movieData = new MovieData(title, plot, year, director, actors, urlToPoster);
        this.activity.setData(movieData);
    }
}