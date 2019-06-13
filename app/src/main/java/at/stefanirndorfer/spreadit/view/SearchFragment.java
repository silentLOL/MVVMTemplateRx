package at.stefanirndorfer.spreadit.view;

import android.view.LayoutInflater;

import at.stefanirndorfer.spreadit.databinding.FragmentSearchBinding;
import at.stefanirndorfer.spreadit.view.base.BaseFragment;
import at.stefanirndorfer.spreadit.viewmodel.SearchFragmentViewModel;
import at.stefanirndorfer.spreadit.viewmodel.ViewModelFactory;

public class SearchFragment extends BaseFragment implements SearchFragmentViewModel.SearchFragmentViewModelListener {
    public static final String TAG = SearchFragment.class.getSimpleName();

    @Override
    protected FragmentSearchBinding createViewBinding(LayoutInflater inflater) {
        FragmentSearchBinding binding = FragmentSearchBinding.inflate(inflater);
        return binding;
    }

    @Override
    protected SearchFragmentViewModel createViewModel() {
        return ViewModelFactory.getInstance(getActivity().getApplication()).createFragmentViewModel(SearchFragmentViewModel.class, this);
    }

    public interface SearchFragmentListener {
    }
}
