package at.stefanirndorfer.spreadit.data;

import android.database.Observable;

import at.stefanirndorfer.spreadit.data.datatypes.Movie;
import at.stefanirndorfer.spreadit.data.source.SpreadItDataSource;
import at.stefanirndorfer.spreadit.data.source.remote.SpreadItNetworkDataSource;
import at.stefanirndorfer.spreadit.utils.AppExecutors;

public class DataRepository implements SpreadItDataSource {

    public static DataRepository getInstance(SpreadItNetworkDataSource instance) {
        return null;
    }

    @Override
    public Observable<Movie> getMovieByName(String name) {
        return null;
    }
}
