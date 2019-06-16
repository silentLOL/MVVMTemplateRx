package at.stefanirndorfer.spreadit.data.source.remote.retrofit;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * Created by atoader on 28.11.17.
 */
@GsonTypeAdapterFactory
public abstract class MyGsonConvertorFactory implements TypeAdapterFactory {

    // Static factory method to access the package
    // private generated implementation
    public static TypeAdapterFactory create() {
        return new AutoValueGson_MyGsonConvertorFactory();
    }
}
