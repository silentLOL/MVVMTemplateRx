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
public abstract class MovieResponse extends BaseResponse {

    @Nullable
    @Json(name = "vote_count")
    @SerializedName("vote_count")
    abstract Integer voteCount();

    @Nullable
    @Json(name = "id")
    @SerializedName("id")
    abstract Integer id();

    @Nullable
    @Json(name = "video")
    @SerializedName("video")
    abstract Boolean video();

    @Nullable
    @Json(name = "vote_average")
    @SerializedName("vote_average")
    abstract Double voteAverage();

    @Nullable
    @Json(name = "title")
    @SerializedName("title")
    abstract String title();

    @Nullable
    @Json(name = "popularity")
    @SerializedName("popularity")
    abstract Double popularity();

    @Nullable
    @Json(name = "poster_path")
    @SerializedName("poster_path")
    abstract String posterPath();

    @Nullable
    @Json(name = "original_language")
    @SerializedName("original_language")
    abstract String originalLanguage();

    @Nullable
    @Json(name = "original_title")
    @SerializedName("original_title")
    abstract String originalTitle();

    @Nullable
    @Json(name = "genre_ids")
    @SerializedName("genre_ids")
    abstract List<Integer> genreIds();

    @Nullable
    @Json(name = "backdrop_path")
    @SerializedName("backdrop_path")
    abstract String backdropPath();

    @Nullable
    @Json(name = "adult")
    @SerializedName("adult")
    abstract Boolean adult();

    @Nullable
    @Json(name = "overview")
    @SerializedName("overview")
    abstract String overview();

    @Nullable
    @Json(name = "release_date")
    @SerializedName("release_date")
    abstract String releaseDate();

    public abstract MovieResponse.Builder newBuilder();

    public static MovieResponse.Builder builder() {
        return new $$AutoValue_MovieResponse.Builder();
    }

    public static MovieResponse createFromParcel(Parcel source) {
        return AutoValue_MovieResponse.CREATOR.createFromParcel(source);
    }

    public static JsonAdapter<MovieResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_MovieResponse.MoshiJsonAdapter(moshi);
    }

    public static TypeAdapter<MovieResponse> typeAdapter(Gson gson) {
        return new AutoValue_MovieResponse.GsonTypeAdapter(gson);
    }

    @AutoValue.Builder
    public static abstract class Builder extends BaseResponseBuilder {
        public abstract Builder voteCount(Integer voteCount);

        public abstract Builder id(Integer id);

        public abstract Builder video(Boolean video);

        public abstract Builder voteAverage(Double voteAverage);

        public abstract Builder title(String title);

        public abstract Builder popularity(Double popularity);

        public abstract Builder posterPath(String posterPath);

        public abstract Builder originalLanguage(String originalLanguage);

        public abstract Builder originalTitle(String originalTitle);

        public abstract Builder genreIds(List<Integer> genreIds);

        public abstract Builder backdropPath(String backdropPath);

        public abstract Builder adult(Boolean adult);

        public abstract Builder overview(String overview);

        public abstract Builder releaseDate(String releaseDate);

        public abstract MovieResponse build();
    }

    public Integer getVoteCount() {
        return voteCount();
    }

    public Integer getId() {
        return id();
    }

    public Boolean getVideo() {
        return video();
    }

    public Double getVoteAverage() {
        return voteAverage();
    }

    public String getTitle() {
        return title();
    }

    public Double getPopularity() {
        return popularity();
    }

    public String getPosterPath() {
        return posterPath();
    }

    public String getOriginalLanguage() {
        return originalLanguage();
    }

    public String getOriginalTitle() {
        return originalTitle();
    }

    public List<Integer> getGenreIds() {
        return genreIds();
    }

    public String getBackdropPath() {
        return backdropPath();
    }

    public Boolean getAdult() {
        return adult();
    }

    public String getOverview() {
        return overview();
    }

    public String getReleaseDate() {
        return releaseDate();
    }
}
