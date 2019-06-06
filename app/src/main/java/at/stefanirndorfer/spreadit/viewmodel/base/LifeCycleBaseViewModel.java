package at.stefanirndorfer.spreadit.viewmodel.base;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

class LifeCycleBaseViewModel<T> extends BaseObservable {

    private BehaviorSubject<T> lifecycleSubject = BehaviorSubject.create();

    @NonNull
    @CheckResult
    public final Observable<T> lifecycle() {
        return this.lifecycleSubject.hide();
    }

    @NonNull
    public <U> LifecycleTransformer<U> bindUntilEvent(@NonNull T event) {
        return RxLifecycle.bindUntilEvent(this.lifecycleSubject, event);
    }

    public BehaviorSubject<T> getLifecycleSubject() {
        return this.lifecycleSubject;
    }
}
