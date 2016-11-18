package com.example.tirza.soutetirza_pset3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;

/**
 * This file uses the URL to the poster that is saved in the MovieData object to create a Bitmap
 * image and sets this in the image view for the poster.
 *
 * Native App Studio, Tirza Soute
 */

class PosterAsyncTask extends AsyncTask<String, Void, Bitmap> {
    private ShowResult activity;

    /** Create PosterAsyncTask constructor */
    PosterAsyncTask(ShowResult activity) {
        this.activity = activity;
    }

    /** Create a Bitmap image from the url */
    protected Bitmap doInBackground(String... urls) {
        String urlToPoster = urls[0];
        Bitmap poster = null;

        // Try to create a Bitmap
        try {
            InputStream in = new java.net.URL(urlToPoster).openStream();
            poster = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return poster;
    }

    /** Set the poster in the poster image view */
    protected void onPostExecute(Bitmap poster) {
        ImageView posterView = (ImageView) activity.findViewById(R.id.showPoster);
        posterView.setImageBitmap(poster);
    }
}