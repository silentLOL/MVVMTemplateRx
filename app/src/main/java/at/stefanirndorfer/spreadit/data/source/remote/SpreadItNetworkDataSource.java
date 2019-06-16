package at.stefanirndorfer.spreadit.data.source.remote;

import at.stefanirndorfer.spreadit.data.datatypes.response.MovieQueryResponse;
import at.stefanirndorfer.spreadit.data.source.SpreadItDataSource;
import at.stefanirndorfer.spreadit.data.source.remote.retrofit.RetrofitClient;
import at.stefanirndorfer.spreadit.utils.ApiConstants;
import io.reactivex.Observable;

public class SpreadItNetworkDataSource implements SpreadItDataSource {

    private static SpreadItNetworkDataSource instance;

    public static SpreadItNetworkDataSource getInstance() {
        if (instance == null) {
            instance = new SpreadItNetworkDataSource();
        }
        return instance;
    }

    private SpreadItNetworkDataSource() {
    }

    @Override
    public Observable<MovieQueryResponse> getMovieByName(String name) {
        SpreadItNetworkService spreadItNetworkService = RetrofitClient.getRetrofitInstance().create(SpreadItNetworkService.class);
        return spreadItNetworkService.getMovies(ApiConstants.API_KEY, name, 1);
    }
}
