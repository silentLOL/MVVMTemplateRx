package at.stefanirndorfer.spreadit.data.source.remote;

import android.database.Observable;

import androidx.annotation.NonNull;
import at.stefanirndorfer.spreadit.data.datatypes.Movie;
import at.stefanirndorfer.spreadit.data.source.SpreadItDataSource;
import at.stefanirndorfer.spreadit.utils.AppExecutors;

public class SpreadItNetworkDataSource implements SpreadItDataSource {

    private static volatile SpreadItNetworkDataSource instance;
    private final AppExecutors appExecutors;

    public static SpreadItNetworkDataSource getInstance(@NonNull AppExecutors appExecutors) {
        if (instance == null) {
            instance = new SpreadItNetworkDataSource(appExecutors);
        }
        return instance;
    }

    private SpreadItNetworkDataSource(AppExecutors appExecutors) {
        this.appExecutors = appExecutors;
    }

    @Override
    public Observable<Movie> getMovieByName(String name) {
        return null;
    }
}
