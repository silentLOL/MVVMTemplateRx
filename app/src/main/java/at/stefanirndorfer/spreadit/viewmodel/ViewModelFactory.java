package at.stefanirndorfer.spreadit.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.annotation.VisibleForTesting;
import at.stefanirndorfer.spreadit.data.DataRepository;
import at.stefanirndorfer.spreadit.utils.di.Injection;
import at.stefanirndorfer.spreadit.viewmodel.base.BaseActivityViewModel;
import at.stefanirndorfer.spreadit.viewmodel.base.BaseFragmentViewModel;
import at.stefanirndorfer.spreadit.viewmodel.listener.ViewModelListener;

/**
 * A creator is used to inject the product ID into the ViewModel
 */
public class ViewModelFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application application;

    private final DataRepository dataRepository;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(application,
                            Injection.provideDataRepository(application.getApplicationContext()));
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application, DataRepository repository) {
        this.application = application;
        dataRepository = repository;
    }

    public <T extends BaseActivityViewModel> T createActivityViewModel(Class<T> modelClass, ViewModelListener listener) {
        if (modelClass.isAssignableFrom(MainActivityViewModel.class)) {
            //noinspection unchecked
            return (T) new MainActivityViewModel((MainActivityViewModel.MainViewModelListener) listener,
                    application,
                    dataRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

    public <T extends BaseFragmentViewModel> T createFragemntViewModel(Class<T> modelClass, ViewModelListener listener) {
        if (modelClass.isAssignableFrom(SignUpFragmentViewModel.class)) {
            //noinspection unchecked
            return (T) new SignUpFragmentViewModel((SignUpFragmentViewModel.SignUpFragmentViewModelListener) listener,
                    application,
                    dataRepository);
        } else if (modelClass.isAssignableFrom(MovieListFragmentViewModel.class)) {
            //noinspection unchecked
            return (T) new MovieListFragmentViewModel((MovieListFragmentViewModel.MovieListFragmentViewModelListener) listener,
                    application,
                    dataRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

}
