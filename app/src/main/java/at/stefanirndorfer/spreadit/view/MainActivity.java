package at.stefanirndorfer.spreadit.view;

import android.os.Bundle;

import at.stefanirndorfer.spreadit.R;
import at.stefanirndorfer.spreadit.viewmodel.MainActivityViewModel;

public class MainActivity extends BaseActivity<MainActivityViewModel> implements MainActivityViewModel.MainActivityViewModelListener {

    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected MainActivityViewModel createViewModel() {
        return new MainActivityViewModel(this);
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

}
