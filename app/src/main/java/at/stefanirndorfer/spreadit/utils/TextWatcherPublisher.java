package at.stefanirndorfer.spreadit.utils;

import android.text.Editable;
import android.text.TextWatcher;

import io.reactivex.Flowable;
import io.reactivex.processors.PublishProcessor;

/**
 * Text watcher implementation using publish processor to updateUser its values..
 */

public class TextWatcherPublisher implements TextWatcher {
    private PublishProcessor<Boolean> isEmptyPublisher = PublishProcessor.create();

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        isEmptyPublisher.onNext(s.toString().trim().length() > 0);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public Flowable<Boolean> getFlowable() {
        return isEmptyPublisher;
    }
}
