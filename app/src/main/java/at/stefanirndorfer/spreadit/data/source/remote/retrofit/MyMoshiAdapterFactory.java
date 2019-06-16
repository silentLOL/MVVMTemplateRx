package at.stefanirndorfer.spreadit.data.source.remote.retrofit;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * Moshi adapter factory
 */
@MoshiAdapterFactory
public abstract class MyMoshiAdapterFactory implements JsonAdapter.Factory {

    // Static factory method to access the responses
    // private generated implementation
    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_MyMoshiAdapterFactory();
    }

}
