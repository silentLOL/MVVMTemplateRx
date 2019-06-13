package at.stefanirndorfer.spreadit.data.source.remote;

import at.stefanirndorfer.spreadit.data.datatypes.response.MovieQueryResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SpreadItNetworkService {

    @GET("3/search/movie")
    Observable<MovieQueryResponse> getMovies(
            @Query("api_key") String api_key,
            @Query("query") String query,
            @Query("page") Integer page
    );
}
