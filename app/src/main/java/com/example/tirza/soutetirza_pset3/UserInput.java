/* Native App Studio: Assignment 3
 * Watch List
 * Tirza Soute
 *
 * This file uses the input that was given by the user for the OMDb API.
 */

package com.example.tirza.soutetirza_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class UserInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);
    }

    /**
     * Replaces the spaces by the space character and starts searching for data if input was given
     */
    public void searchTitle(View view) {
        EditText userInput = (EditText) findViewById(R.id.titleInput);
        String title = userInput.getText().toString();
        // Check if the user typed anything
        if (!(title.length() == 0)) {
            // Replace the spaces in the input with the space character
            title = title.replaceAll("\\s+","%20");
            MovieAsyncTask asyncTask = new MovieAsyncTask(this);
            asyncTask.execute(title);
        } else {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
        }
    }

    /** Passes the MovieData object to the next activity to show the search results to the user  */
    public void setData(MovieData movieData) {
        Intent goToResult = new Intent(this, ShowResult.class);
        Bundle extras = new Bundle();
        extras.putSerializable("movieData", movieData);
        goToResult.putExtras(extras);
        startActivity(goToResult);
    }

    /** Goes to the Watch List activity to show the user his watch list, if the button is clicked */
    public void showSaved(View view) {
        Intent goToWatchList = new Intent(this, WatchList.class);
        startActivity(goToWatchList);
    }
}
