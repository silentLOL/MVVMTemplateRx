package at.stefanirndorfer.spreadit.data.source;


import at.stefanirndorfer.spreadit.data.datatypes.response.MovieQueryResponse;
import io.reactivex.Observable;

public interface SpreadItDataSource {

    public Observable<MovieQueryResponse> getMovieByName(String name);
}
