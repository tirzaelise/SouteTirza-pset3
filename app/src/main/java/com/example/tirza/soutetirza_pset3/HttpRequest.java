package com.example.tirza.soutetirza_pset3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This file uses the title that was given as input by the user to make a url request.
 *
 * Native App Studio, Tirza Soute
 */

class HttpRequest {

    /** Use the url that was given by the user to make a request to the OMDb API */
    static synchronized String downloadFromAPI(String... params) {
        String urlToApi = "http://www.omdbapi.com/?t=";
        String urlSettings = "&y=&plot=short&r=json";
        String title = params[0];
        String urlRequest = urlToApi + title + urlSettings;
        URL url;
        String result = "";

        // Try to form a URL from the urlRequest string
        try {
            url = new URL(urlRequest);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return result;
        }

        // Try to make a connection using the given url
        HttpURLConnection connection;
        if (url != null) {
            try {
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                Integer responseCode = connection.getResponseCode();
                if (200 >= responseCode && responseCode <= 299) {
                    InputStreamReader input = new InputStreamReader(connection.getInputStream());
                    BufferedReader br = new BufferedReader(input);
                    String line;
                    // Read the data on the website
                    while ((line = br.readLine()) != null) {
                        result = result + line;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}