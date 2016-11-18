/* Native App Studio: Assignment 3
 * Watch List
 * Tirza Soute
 *
 * This file uses the data that was received from the HttpRequest file to create a MovieData object.
 */

package com.example.tirza.soutetirza_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

class MovieAsyncTask extends AsyncTask<String, String, String> {
    private UserInput activity;
    private Context context;

    /** Creates a MovieAsyncTask constructor */
    MovieAsyncTask(UserInput activity) {
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    /** Receives the data that was obtained with the HttpRequest file */
    protected String doInBackground(String... params) {
        return HttpRequest.downloadFromAPI(params);
    }

    /** Creates a MovieData object */
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
