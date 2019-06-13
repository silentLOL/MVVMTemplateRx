package at.stefanirndorfer.spreadit.utils.di;

import android.content.Context;

import androidx.annotation.NonNull;
import at.stefanirndorfer.spreadit.data.DataRepository;
import at.stefanirndorfer.spreadit.data.source.remote.SpreadItNetworkDataSource;
import at.stefanirndorfer.spreadit.utils.AppExecutors;

/**
 * Enables injection of mock implementations for
 * {@link at.stefanirndorfer.spreadit.data.source.SpreadItDataSource} at compile time. This is useful for testing, since it allows us to use
 * a fake instance of the class to isolate the dependencies and run a test hermetically.
 * TODO: No fake data sources implemented yet
 */
public class Injection {
    public static DataRepository provideDataRepository(@NonNull Context context) {
        return DataRepository.getInstance(SpreadItNetworkDataSource.getInstance(), new AppExecutors());
    }
}
