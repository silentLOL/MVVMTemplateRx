package at.stefanirndorfer.spreadit.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import at.stefanirndorfer.spreadit.R;
import at.stefanirndorfer.spreadit.view.base.BaseActivity;
import at.stefanirndorfer.spreadit.view.dialogs.DialogManager;
import at.stefanirndorfer.spreadit.view.dialogs.DialogManagerImpl;
import at.stefanirndorfer.spreadit.viewmodel.MainActivityViewModel;
import at.stefanirndorfer.spreadit.viewmodel.ViewModelFactory;

public class MainActivity extends BaseActivity<MainActivityViewModel> implements MainActivityViewModel.MainViewModelListener {

    private static final String TAG = "MainActivity";
    private static final String START_KEY = "START_KEY";
    public static final String START_MOVIE_LIST = "start_movie_list";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static Intent startOnMain(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(START_KEY, MainActivityViewModel.SHOW_LIST);
        return intent;
    }


    @Override
    protected MainActivityViewModel createViewModel() {
        ViewModelFactory factory = ViewModelFactory.getInstance(this.getApplication());
        return factory.createActivityViewModel(MainActivityViewModel.class, this);
    }

    @Override
    protected int getScreenContainer() {
        return R.id.main_activity_container;
    }

    @Override
    public void onBackPressed() {
        if (isBackProcessed()) {
            return;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public String getStartValue() {
        return getIntent().getStringExtra(START_KEY);
    }

    //-----------------------------------------
    //----- NavigationController --------------
    //-----------------------------------------

    @Override
    public void navigateToMovieListFragment() {
        popStackToBegin();
        navigateToNextScreen(MovieListFragment.newInstance(null), MovieListFragment.TAG);
    }

    @Override
    public void navigateToSearchFragment() {
        navigateToNextScreen(new SearchFragment(), SearchFragment.TAG);
    }

    /**
     * @return
     */
    @Override
    public DialogManager getDialogManager() {
        return new DialogManagerImpl(this.getApplication());
    }
}
