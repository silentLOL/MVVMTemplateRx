package at.stefanirndorfer.spreadit.data.datatypes.response;

import android.os.Parcel;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

import androidx.annotation.Nullable;

@AutoValue
public abstract class MovieQueryResponse extends BaseResponse {
    @Nullable
    @Json(name = "page")
    @SerializedName("page")
    abstract Integer page();

    @Nullable
    @Json(name = "total_results")
    @SerializedName("total_results")
    abstract Integer totalResults();

    @Nullable
    @Json(name = "total_pages")
    @SerializedName("total_pages")
    abstract Integer totalPages();

    @Nullable
    @Json(name = "results")
    @SerializedName("results")
    abstract List<MovieResponse> movies();


    public abstract Builder newBuilder();

    public static Builder builder() {
        return new $$AutoValue_MovieQueryResponse.Builder();
    }

    public static MovieQueryResponse createFromParcel(Parcel source) {
        return AutoValue_MovieQueryResponse.CREATOR.createFromParcel(source);
    }

    public static JsonAdapter<MovieQueryResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_MovieQueryResponse.MoshiJsonAdapter(moshi);
    }

    public static TypeAdapter<MovieQueryResponse> typeAdapter(Gson gson) {
        return new AutoValue_MovieQueryResponse.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder extends BaseResponseBuilder {
        public abstract Builder page(Integer page);

        public abstract Builder totalResults(Integer totalResults);

        public abstract Builder totalPages(Integer totalPages);

        public abstract Builder movies(List<MovieResponse> movies);

        public abstract MovieQueryResponse build();
    }

    public Integer getPage() {
        return page();
    }

    public Integer getTotalResults() {
        return totalResults();
    }

    public Integer getTotalPages() {
        return totalPages();
    }

    public List<MovieResponse> getMovies() {
        return movies();
    }
}
