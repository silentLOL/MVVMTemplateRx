package at.stefanirndorfer.spreadit.viewmodel;

import android.content.SharedPreferences;
import android.os.Bundle;

import at.stefanirndorfer.spreadit.viewmodel.base.BaseActivityViewModel;
import at.stefanirndorfer.spreadit.viewmodel.listener.ActivityViewModelListener;

public class MainActivityViewModel extends BaseActivityViewModel<MainActivityViewModel.MainActivityViewModelListener> {


    public MainActivityViewModel(MainActivityViewModelListener listener) {
        super(listener);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestUserBasedOnStartValue(savedInstanceState);
    }

    private void requestUserBasedOnStartValue(Bundle savedInstanceState) {

    }

    public interface MainActivityViewModelListener extends ActivityViewModelListener {
        SharedPreferences getSharedPreferences(String name, int mode);
    }
}
