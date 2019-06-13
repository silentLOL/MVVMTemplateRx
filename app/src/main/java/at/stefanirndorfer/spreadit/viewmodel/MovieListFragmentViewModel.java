package at.stefanirndorfer.spreadit.viewmodel;

import android.app.Application;

import at.stefanirndorfer.spreadit.R;
import at.stefanirndorfer.spreadit.data.DataRepository;
import at.stefanirndorfer.spreadit.navigation.NavigationController;
import at.stefanirndorfer.spreadit.viewmodel.base.BaseFragmentViewModel;
import at.stefanirndorfer.spreadit.viewmodel.listener.ViewModelListener;
import timber.log.Timber;

public class MovieListFragmentViewModel extends BaseFragmentViewModel<MovieListFragmentViewModel.MovieListFragmentViewModelListener> {

    public MovieListFragmentViewModel(MovieListFragmentViewModelListener listener, Application application, DataRepository dataRepository) {
        super(listener, application, dataRepository);
    }

    public interface MovieListFragmentViewModelListener extends ViewModelListener {
        void navigateToSearchFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        showExampleDialog();
    }

    private void showExampleDialog() {
        getListener().getDialogManager().showInfoDialogCustomActions(
                R.string.example_dialog_title,
                R.string.example_dialog_msg,
                R.string.ok, R.string.cancel).subscribe(() -> onDialogCompleted(), throwable -> Timber.e(throwable));
    }

    private void onDialogCompleted() {
        Timber.d("Dialog was completed");
        getListener().navigateToSearchFragment();
    }
}
