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

    /**  */
    public void searchTitle(View view) {
        EditText userInput = (EditText) findViewById(R.id.titleInput);
        String title = userInput.getText().toString();
        if (!(title.length() == 0)) {
            title = title.replaceAll("\\s+","%20");
            MovieAsyncTask asyncTask = new MovieAsyncTask(this);
            asyncTask.execute(title);
        } else {
            Toast.makeText(this, "Please enter a title", Toast.LENGTH_SHORT).show();
        }
    }

    public void setData(MovieData movieData) {
        Intent goToResult = new Intent(this, ShowResult.class);
        Bundle extras = new Bundle();
        extras.putSerializable("movieData", movieData);
        goToResult.putExtras(extras);
        startActivity(goToResult);
    }

    public void showSaved(View view) {
        Intent goToWatchList = new Intent(this, WatchList.class);
        startActivity(goToWatchList);
    }
}
