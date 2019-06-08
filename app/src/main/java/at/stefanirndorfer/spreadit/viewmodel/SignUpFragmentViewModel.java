package at.stefanirndorfer.spreadit.viewmodel;

import android.app.Application;

import at.stefanirndorfer.spreadit.data.DataRepository;
import at.stefanirndorfer.spreadit.viewmodel.base.BaseFragmentViewModel;
import at.stefanirndorfer.spreadit.viewmodel.listener.ViewModelListener;

public class SignUpFragmentViewModel extends BaseFragmentViewModel<SignUpFragmentViewModel.SignUpFragmentViewModelListener> {


    public SignUpFragmentViewModel(SignUpFragmentViewModelListener listener, Application application, DataRepository dataRepository) {
        super(listener, application, dataRepository);
    }

    public interface SignUpFragmentViewModelListener extends ViewModelListener {
    }
}
