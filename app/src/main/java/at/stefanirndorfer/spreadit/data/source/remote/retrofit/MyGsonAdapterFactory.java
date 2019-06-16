package at.stefanirndorfer.spreadit.data.source.remote.retrofit;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by atoader on 29/05/2017.
 */

@GsonTypeAdapterFactory
public abstract class MyGsonAdapterFactory implements TypeAdapterFactory {

    // Static factory method to access the package
    // private generated implementation
    public static TypeAdapterFactory create() {
        return new AutoValueGson_MyGsonAdapterFactory();
    }

}