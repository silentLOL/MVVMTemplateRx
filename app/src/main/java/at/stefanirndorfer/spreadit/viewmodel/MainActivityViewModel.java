package at.stefanirndorfer.spreadit.viewmodel;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.Bundle;

import at.stefanirndorfer.spreadit.data.DataRepository;
import at.stefanirndorfer.spreadit.data.datatypes.User;
import at.stefanirndorfer.spreadit.navigation.NavigationController;
import at.stefanirndorfer.spreadit.viewmodel.base.BaseActivityViewModel;
import at.stefanirndorfer.spreadit.viewmodel.listener.ViewModelListener;
import io.reactivex.Maybe;
import timber.log.Timber;

import static at.stefanirndorfer.spreadit.view.MainActivity.START_MOVIE_LIST;

public class MainActivityViewModel extends BaseActivityViewModel<MainActivityViewModel.MainViewModelListener> {

    public static final String SHOW_LIST = "show_list";

    public MainActivityViewModel(MainViewModelListener listener, Application application, DataRepository repository) {
        super(listener, application, repository);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            requestUserBasedOnStartValue(savedInstanceState);
        }
    }

    /**
     * method to find out if a registration was already done
     *
     * @param savedInstanceState
     */
    @SuppressLint("CheckResult")
    private void requestUserBasedOnStartValue(Bundle savedInstanceState) {
        if (hasStartValue()) {
            // if a user is given we go to the start screen defined by the Activities Intent
            requestUser(savedInstanceState)
                    .subscribe(user -> goToStartScreen(), this::onUserLoginError, this::onNoUserLogin);
            return;
        }
        goToMovieListFragment();
//        // we start on the MovieListFragment
//        requestUser(savedInstanceState)
//                .subscribe(user -> goToMovieListFragment(), this::onUserLoginError, this::onNoUserLogin);
    }

    /**
     * we have a logged in user
     */
    private void goToMovieListFragment() {
        Timber.d("user is logged in, we move to MovieListFragment");
        getListener().navigateToMovieListFragment();
    }

    /**
     * we need to navigate to the UserRegistrationFragment
     */
    private void onNoUserLogin() {
        //todo
    }

    private void onUserLoginError(Throwable throwable) {
        Timber.d(throwable);
    }

    private void goToStartScreen() {
        String startValue = getListener().getStartValue();
        switch (startValue) {
            case START_MOVIE_LIST:
                goToMovieListFragment();
                break;
            default:
                String msg = "Unknown start value. Value =" + startValue;
                Timber.e(new RuntimeException(msg));
        }
    }

    protected Maybe<User> requestUser(Bundle savedInstanceState) {
        return super.requestUser(savedInstanceState)
                .compose(binTransform());
    }

    private boolean hasStartValue() {
        return getListener().getStartValue() != null;
    }

    public interface MainViewModelListener extends ViewModelListener, NavigationController {
        SharedPreferences getSharedPreferences(String name, int mode);

        String getStartValue();
    }
}
