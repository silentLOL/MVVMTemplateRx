package at.stefanirndorfer.spreadit.view;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import at.stefanirndorfer.spreadit.view.listener.BackPressListener;
import at.stefanirndorfer.spreadit.viewmodel.base.BaseActivityViewModel;
import at.stefanirndorfer.spreadit.viewmodel.listener.ActivityViewModelListener;

public abstract class BaseActivity<VIEW_MODEL extends BaseActivityViewModel> extends AppCompatActivity implements ActivityViewModelListener {

    private static final String TAG = "BaseActivity";

    private VIEW_MODEL viewModel;
    private boolean isResumed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = createViewModel();
        viewModel.onCreate(savedInstanceState);
    }

    protected abstract VIEW_MODEL createViewModel();

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        getViewModel().restoreState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getViewModel().saveState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    protected void onStart() {
        super.onStart();
        getViewModel().onStart();
    }

    @Override
    protected void onStop() {
        getViewModel().onStop();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
        getViewModel().onResume();
    }

    @Override
    protected void onPause() {
        getViewModel().onPause();
        isResumed = false;
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        getViewModel().onDestroy();
        super.onDestroy();
    }

    protected boolean isBackProcessed() {
        Fragment fragment = getMainScreen();
        return fragment instanceof BackPressListener && ((BackPressListener) fragment).onBackPressed();
    }

    protected boolean hasBackAction() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            return true;
        }
        Fragment fragment = getMainScreen();
        return fragment instanceof BackPressListener && ((BackPressListener) fragment).hasBackAction();
    }

    protected Fragment getMainScreen() {
        return getSupportFragmentManager().findFragmentById(getScreenContainer());
    }

    protected void popStackToBegin() {
        if (isResumed) {
            getSupportFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
    }

    protected void popBackStackToTag(String tag) {
        getSupportFragmentManager().popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    protected void navigateToNextScreen(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(getScreenContainer(), fragment)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    }

    protected void navigateToNextScreen(Fragment fragment, String tag) {
        Log.d(TAG, "Navigating to " + fragment.getClass().getSimpleName());
        getSupportFragmentManager().beginTransaction()
                .replace(getScreenContainer(), fragment)
                .addToBackStack(tag)
                .commitAllowingStateLoss();
    }

    protected void navigateToNextScreenNotOnBackStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(getScreenContainer(), fragment)
                .commitAllowingStateLoss();
    }

    protected abstract int getScreenContainer();

    public VIEW_MODEL getViewModel() {
        return viewModel;
    }

}
