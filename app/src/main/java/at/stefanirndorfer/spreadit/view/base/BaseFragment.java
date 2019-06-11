package at.stefanirndorfer.spreadit.view.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import at.stefanirndorfer.spreadit.BR;
import at.stefanirndorfer.spreadit.view.dialogs.DialogManager;
import at.stefanirndorfer.spreadit.view.dialogs.DialogManagerImpl;
import at.stefanirndorfer.spreadit.viewmodel.base.BaseFragmentViewModel;
import at.stefanirndorfer.spreadit.viewmodel.listener.ViewModelListener;

public abstract class BaseFragment<VIEW_BINDING extends ViewDataBinding, VIEW_MODEL extends BaseFragmentViewModel> extends Fragment implements ViewModelListener {

    private VIEW_MODEL viewModel;
    private VIEW_BINDING viewBinding;
    private DialogManager dialogManager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onDestroyView() {
        viewModel.onDestroyView();
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewBinding = createViewBinding(inflater);
        return viewBinding.getRoot();
    }

    protected abstract VIEW_BINDING createViewBinding(LayoutInflater inflater);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = createViewModel();
        viewBinding.setVariable(getViewModelId(), viewModel);
        viewModel.onCreate();
        dialogManager = new DialogManagerImpl(getActivity());
    }

    @Override
    public void onStart() {
        super.onStart();
        getViewModel().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getViewModel().onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        getViewModel().onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        getViewModel().onPause();
    }

    /**
     * ViewModel and ViewBinding for the fragment =================================================
     */
    protected abstract VIEW_MODEL createViewModel();

    protected VIEW_MODEL getViewModel() {
        return viewModel;
    }

    public VIEW_BINDING getViewBinding() {
        return viewBinding;
    }

    public int getViewModelId() {
        return BR.viewModel;
    }


    public String getStringFromRes(int resId) {
        return getAppContext().getString(resId);
    }

    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    public DialogManager getDialogManager() {
        return dialogManager;
    }
}
