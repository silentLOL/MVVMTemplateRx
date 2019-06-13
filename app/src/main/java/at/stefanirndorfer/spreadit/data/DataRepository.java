package at.stefanirndorfer.spreadit.data;

import at.stefanirndorfer.spreadit.data.datatypes.response.MovieQueryResponse;
import at.stefanirndorfer.spreadit.data.source.SpreadItDataSource;
import at.stefanirndorfer.spreadit.utils.AppExecutors;
import io.reactivex.Observable;

public class DataRepository implements SpreadItDataSource {

    private volatile static DataRepository INSTANCE = null;
    private final SpreadItDataSource remoteDataSource;
    private final AppExecutors executors;
    private Object lock = new Object();


    private DataRepository(SpreadItDataSource spreadItDataSource, AppExecutors executors) {
        this.remoteDataSource = spreadItDataSource;
        this.executors = executors;
    }

    public static DataRepository getInstance(SpreadItDataSource remoteSessionsDataSource, AppExecutors executors) {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(remoteSessionsDataSource, executors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public Observable<MovieQueryResponse> getMovieByName(String name) {
        return remoteDataSource.getMovieByName(name);
    }
}
