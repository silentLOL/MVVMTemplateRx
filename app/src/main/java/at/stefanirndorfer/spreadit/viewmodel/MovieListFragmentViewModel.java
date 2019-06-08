package at.stefanirndorfer.spreadit.viewmodel;

import android.app.Application;

import at.stefanirndorfer.spreadit.data.DataRepository;
import at.stefanirndorfer.spreadit.navigation.NavigationController;
import at.stefanirndorfer.spreadit.viewmodel.base.BaseFragmentViewModel;
import at.stefanirndorfer.spreadit.viewmodel.listener.ViewModelListener;

public class MovieListFragmentViewModel extends BaseFragmentViewModel<MovieListFragmentViewModel.MovieListFragmentViewModelListener> {

    public MovieListFragmentViewModel(MovieListFragmentViewModelListener listener, Application application, DataRepository dataRepository) {
        super(listener, application, dataRepository);
    }

    public interface MovieListFragmentViewModelListener extends ViewModelListener, NavigationController {
    }
}
