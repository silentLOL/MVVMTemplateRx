package at.stefanirndorfer.spreadit.viewmodel.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import at.stefanirndorfer.spreadit.data.DataRepository;
import at.stefanirndorfer.spreadit.data.datatypes.User;
import at.stefanirndorfer.spreadit.viewmodel.listener.ViewModelListener;
import io.reactivex.Maybe;
import io.reactivex.MaybeTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseFragmentViewModel<VIEW_MODEL_LISTENER extends ViewModelListener> extends LifeCycleBaseViewModel<FragmentEvent> {

    private static final String TAG = BaseFragmentViewModel.class.getSimpleName();
    private static final String USER_KEY = "user";
    private VIEW_MODEL_LISTENER listener;

    @SuppressLint("StaticFieldLeak")
    private final Context context; /* Application Context only */
    private final DataRepository dataRepository;
    private User user;

    public BaseFragmentViewModel(VIEW_MODEL_LISTENER listener, Application application, DataRepository dataRepository) {
        this.listener = listener;
        context = application.getApplicationContext();
        this.dataRepository = dataRepository;
    }


    public void saveState(Bundle outState) {
        Log.d(TAG, "saveState() called with: outState = [" + outState + "]");
        // decide what to do
    }

    protected <T> MaybeTransformer<T, T> binTransform() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindToLifecycle());
    }

    @NonNull
    public <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindFragment(this.getLifecycleSubject());
    }

    protected Maybe<User> requestUser(Bundle savedInstanceState) {
        return Maybe
                .defer(() -> {
                    if (savedInstanceState == null) {
                        return requestUser();
                    } else {
                        return loadUserFromSave(savedInstanceState);
                    }
                })
                .doOnSuccess(user -> this.user = user);
    }

    /**
     * todo: consider UserViewModel
     *
     * @param savedInstanceState
     * @return
     */
    private Maybe<User> loadUserFromSave(Bundle savedInstanceState) {
        return null;
    }

    /**
     * TODO: Implement the call for the user
     *
     * @return
     */
    private Maybe<User> requestUser() {
        return null;
    }

    protected Context getContext() {
        return context;
    }

    protected DataRepository getDataRepository() {
        return dataRepository;
    }

    protected VIEW_MODEL_LISTENER getListener() {
        return listener;
    }

    @CallSuper
    public void onCreate() {
        this.getLifecycleSubject().onNext(FragmentEvent.CREATE);
    }

    @CallSuper
    public void onStart() {
        this.getLifecycleSubject().onNext(FragmentEvent.START);
    }

    @CallSuper
    public void onResume() {
        this.getLifecycleSubject().onNext(FragmentEvent.RESUME);
    }

    @CallSuper
    public void onPause() {
        this.getLifecycleSubject().onNext(FragmentEvent.PAUSE);
    }

    @CallSuper
    public void onStop() {
        this.getLifecycleSubject().onNext(FragmentEvent.STOP);
    }

    @CallSuper
    public void onAttached() {
        this.getLifecycleSubject().onNext(FragmentEvent.ATTACH);
    }

    @CallSuper
    public void onDetached() {
        this.getLifecycleSubject().onNext(FragmentEvent.DETACH);
    }

    @CallSuper
    public void onDestroyView() {
        this.getLifecycleSubject().onNext(FragmentEvent.DESTROY_VIEW);
    }

    @CallSuper
    public void onDestroy() {
        this.getLifecycleSubject().onNext(FragmentEvent.DESTROY);
    }

}
