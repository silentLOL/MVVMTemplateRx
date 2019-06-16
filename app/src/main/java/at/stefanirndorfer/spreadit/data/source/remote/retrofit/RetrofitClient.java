package at.stefanirndorfer.spreadit.data.source.remote.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import androidx.annotation.NonNull;
import at.stefanirndorfer.spreadit.utils.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;

    static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
            .create();


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create(getMoshi()))
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(new OkHttpClient())
                    .build();
        }
        return retrofit;
    }

    @NonNull
    private static Gson getGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(getTypeAdapterFactory())
                .create();
    }

    private static Moshi getMoshi() {
        return new Moshi.Builder()
                //.add(new FormattedTimeAdapter())
                .add(getMoshiAdapterFactory())
                .build();
    }

    @NonNull
    private static JsonAdapter.Factory getMoshiAdapterFactory() {
        return MyMoshiAdapterFactory.create();
    }

    @NonNull
    private static TypeAdapterFactory getTypeAdapterFactory() {
        return MyGsonAdapterFactory.create();
    }
}


