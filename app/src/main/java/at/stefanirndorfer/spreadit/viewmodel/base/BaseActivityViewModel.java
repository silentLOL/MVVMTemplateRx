package at.stefanirndorfer.spreadit.viewmodel.base;

import android.os.Bundle;
import android.util.Log;

import com.trello.rxlifecycle2.android.ActivityEvent;

import androidx.annotation.CallSuper;
import at.stefanirndorfer.spreadit.viewmodel.listener.ActivityViewModelListener;

public abstract class BaseActivityViewModel<VIEW_MODEL_LISTENER extends ActivityViewModelListener> extends LifeCycleBaseViewModel<ActivityEvent> {

    private static final String TAG = BaseActivityViewModel.class.getSimpleName();
    private static final String USER_KEY = "user";
    private VIEW_MODEL_LISTENER listener;

    public BaseActivityViewModel(VIEW_MODEL_LISTENER listener) {
        this.listener = listener;
    }

    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        this.getLifecycleSubject().onNext(ActivityEvent.CREATE);
    }

    @CallSuper
    public void onStart() {
        this.getLifecycleSubject().onNext(ActivityEvent.START);
    }

    @CallSuper
    public void onStop() {
        this.getLifecycleSubject().onNext(ActivityEvent.STOP);
    }

    @CallSuper
    public void onResume() {
        this.getLifecycleSubject().onNext(ActivityEvent.RESUME);
    }

    @CallSuper
    public void onPause() {
        this.getLifecycleSubject().onNext(ActivityEvent.PAUSE);
    }

    @CallSuper
    public void onDestroy() {
        this.getLifecycleSubject().onNext(ActivityEvent.DESTROY);
    }

    public void restoreState(Bundle savedInstanceState) {
    }

    public void saveState(Bundle outState) {
        Log.d(TAG, "saveState() called with: outState = [" + outState + "]");
        // decide what to do
    }

}
