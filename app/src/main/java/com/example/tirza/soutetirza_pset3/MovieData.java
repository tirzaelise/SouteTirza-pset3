/* Native App Studio: Assignment 3
 * Watch List
 * Tirza Soute
 *
 * This file creates the MovieData object.
 */

package com.example.tirza.soutetirza_pset3;

import java.io.Serializable;

class MovieData implements Serializable {
    private String title;
    private String plot;
    private String year;
    private String director;
    private String actors;
    private String urlToPoster;

    /** Creates MovieData constructor */
    MovieData(String title, String plot, String year, String director, String actors,
              String urlToPoster) {
        this.title = title;
        this.plot = plot;
        this.year = year;
        this.director = director;
        this.actors = actors;
        this.urlToPoster = urlToPoster;
    }

    String getTitle() {
        return title;
    }

    String getPLot() {
        return plot;
    }

    String getYear() {
        return year;
    }

    String getDirector() {
        return director;
    }

    String getActors() {
        return actors;
    }

    String getPosterUrl() {
        return urlToPoster;
    }
}
