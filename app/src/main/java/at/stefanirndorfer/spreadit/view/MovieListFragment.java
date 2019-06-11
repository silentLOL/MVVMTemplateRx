package at.stefanirndorfer.spreadit.view;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;

import androidx.databinding.ViewDataBinding;
import at.stefanirndorfer.spreadit.databinding.FragmentMovieListBinding;
import at.stefanirndorfer.spreadit.view.base.BaseFragment;
import at.stefanirndorfer.spreadit.viewmodel.MovieListFragmentViewModel;
import at.stefanirndorfer.spreadit.viewmodel.ViewModelFactory;

@SuppressLint("ValidFragment")
class MovieListFragment extends BaseFragment implements MovieListFragmentViewModel.MovieListFragmentViewModelListener {

    public static final String TAG = "MovieListFragment";

    public static MovieListFragment newInstance(Object o) {
        return new MovieListFragment();
    }

    @Override
    protected ViewDataBinding createViewBinding(LayoutInflater inflater) {
        FragmentMovieListBinding binding = FragmentMovieListBinding.inflate(inflater);
        return binding;
    }

    @Override
    protected MovieListFragmentViewModel createViewModel() {
        return ViewModelFactory.getInstance(getActivity().getApplication()).createFragemntViewModel(MovieListFragmentViewModel.class, this);
    }

    @Override
    public void navigateToMovieListFragment() {

    }
}
