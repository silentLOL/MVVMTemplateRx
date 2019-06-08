package at.stefanirndorfer.spreadit.data.source;

import android.database.Observable;

import at.stefanirndorfer.spreadit.data.datatypes.Movie;

public interface SpreadItDataSource {

    public Observable<Movie> getMovieByName(String name);
}
