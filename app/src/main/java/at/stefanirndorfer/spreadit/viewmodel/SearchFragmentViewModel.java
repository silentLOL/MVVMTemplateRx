package at.stefanirndorfer.spreadit.viewmodel;

import android.app.Application;

import androidx.databinding.Bindable;
import at.stefanirndorfer.spreadit.BR;
import at.stefanirndorfer.spreadit.data.DataRepository;
import at.stefanirndorfer.spreadit.utils.TextWatcherPublisher;
import at.stefanirndorfer.spreadit.viewmodel.base.BaseFragmentViewModel;
import at.stefanirndorfer.spreadit.viewmodel.listener.ViewModelListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SearchFragmentViewModel extends BaseFragmentViewModel<SearchFragmentViewModel.SearchFragmentViewModelListener> {

    private String queryInput;
    private final TextWatcherPublisher queryWatcher;
    private String searchResult;


    public SearchFragmentViewModel(SearchFragmentViewModelListener listener, Application application, DataRepository dataRepository) {
        super(listener, application, dataRepository);
        queryInput = "";
        queryWatcher = new TextWatcherPublisher();
    }

    @Override
    public void onStart() {
        super.onStart();
        initTextWatcher();
    }

    private void initTextWatcher() {
        queryWatcher.getFlowable()
                .compose(bindToLifecycle())
                .subscribe(this::queryInputHasChanged, throwable -> Timber.e(throwable));
    }

    private void queryInputHasChanged(Boolean isEmpty) {
        if (queryInput.length() > 1) {
            getDataRepository()
                    .getMovieByName(queryInput)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(movieQueryResponse -> setSearchResult(movieQueryResponse.toString()), throwable -> Timber.e(throwable));
        }
    }

    private void setSearchResult(String result) {
        this.searchResult = result;
        notifyPropertyChanged(BR.searchResult);
    }

    @Bindable
    public String getSearchResult() {
        return searchResult;
    }

    @Bindable
    public TextWatcherPublisher getQueryWatcher() {
        return queryWatcher;
    }

    @Bindable
    public String getQueryInput() {
        return queryInput;
    }

    public void setQueryInput(String queryInput) {
        this.queryInput = queryInput;
        notifyPropertyChanged(BR.queryInput);
    }

    public interface SearchFragmentViewModelListener extends ViewModelListener {
    }
}
