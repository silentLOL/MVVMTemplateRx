package at.stefanirndorfer.spreadit.view.dialogs;

import android.app.Application;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import at.stefanirndorfer.spreadit.R;
import at.stefanirndorfer.spreadit.exceptions.DialogCancellationException;
import io.reactivex.Completable;
import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.MaybeTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.SingleTransformer;

public class DialogManagerImpl implements DialogManager {
    private static final String TAG = "DialogManagerImpl";
    private final Context context; // ApplicationContext only!

    public DialogManagerImpl(Context context) {
        this.context = context;
    }

    @Override
    public CompletableTransformer bindCompletableProgress(int stringMessageRes) {
        return bindCompletableProgress(context, stringMessageRes);
    }

    @Override
    public <U> FlowableTransformer<U, U> bindFlowableProgress(int stringMessageRes) {
        return bindFlowableProgress(context, stringMessageRes);
    }


    @Override
    public Completable showInfoDialogNoTitle(String message) {
        return Completable.defer(
                () -> Completable.create(
                        e -> new AlertDialog.Builder(context)
                                .setMessage(message)
                                .setPositiveButton(R.string.ok, (dialog, which) -> e.onComplete())
                                .setOnDismissListener(dialog -> e.onComplete())
                                .create()
                                .show()));
    }

    @Override
    public Completable showInfoDialog(String title, String message) {
        return Completable.defer(
                () -> Completable.create(
                        e -> new AlertDialog.Builder(context)
                                .setTitle(title)
                                .setMessage(message)
                                .setPositiveButton(R.string.ok, (dialog, which) -> e.onComplete())
                                .setOnDismissListener(dialog -> e.onComplete())
                                .create()
                                .show())
        );
    }

    @Override
    public Completable showInfoDialogByResourceIds(int title, int message) {
        return Completable.defer(
                () -> Completable.create(
                        e -> new AlertDialog.Builder(context)
                                .setTitle(title)
                                .setMessage(message)
                                .setPositiveButton(R.string.ok, (dialog, which) -> e.onComplete())
                                .setOnDismissListener(dialog -> e.onComplete())
                                .create()
                                .show())
        );
    }


    @Override
    public Completable showInfoDialogCustomActions(int title, int message, int positiveText, int negativeText) {
        return Completable.defer(
                () -> Completable.create(
                        e -> new AlertDialog.Builder(context)
                                .setTitle(title)
                                .setMessage(message)
                                .setPositiveButton(positiveText, (dialog, which) -> e.onComplete())
                                .setNegativeButton(negativeText, (dialog, which) -> e.onError(new DialogCancellationException("Dialog was cancelled by user")))
                                .setCancelable(false)
                                .create()
                                .show())
        );
    }

    @NonNull
    private CompoundButton.OnCheckedChangeListener getOnCheckedChangeListener(EditText passwordInput) {
        return (buttonView, isChecked) -> {
            passwordInput.setInputType(!isChecked ? InputType.TYPE_TEXT_VARIATION_PASSWORD : InputType.TYPE_CLASS_TEXT);
            passwordInput.setTransformationMethod(!isChecked ? PasswordTransformationMethod.getInstance() : null);
        };
    }

    @NonNull
    private TextWatcher getTextWatcher(final View positiveAction) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                positiveAction.setEnabled(s.toString().trim().length() > 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
    }

    private static <U> MaybeTransformer<U, U> bindMaybeProgress(Context context, int messageRes) {
        return upstream -> {
            final MaterialDialog progressDialog = createDialog(context, messageRes);

            return upstream
                    .doOnSubscribe(disposable -> progressDialog.show())
                    .doOnSuccess(u -> progressDialog.dismiss())
                    .doOnComplete(progressDialog::dismiss)
                    .doOnDispose(progressDialog::dismiss)
                    .doOnError(throwable -> progressDialog.dismiss());
        };
    }

    private static <U> SingleTransformer<U, U> bindSingleProgress(Context context, int messageRes) {
        return upstream -> {
            final MaterialDialog progressDialog = createDialog(context, messageRes);

            return upstream
                    .doOnSubscribe(disposable -> progressDialog.show())
                    .doOnSuccess(u -> progressDialog.dismiss())
                    .doOnDispose(progressDialog::dismiss)
                    .doOnError(throwable -> progressDialog.dismiss());
        };
    }

    private static CompletableTransformer bindCompletableProgress(Context context, int messageRes) {
        return upstream -> {
            final MaterialDialog progressDialog = createDialog(context, messageRes);

            return upstream
                    .doOnSubscribe(disposable -> progressDialog.show())
                    .doOnComplete(progressDialog::dismiss)
                    .doOnDispose(progressDialog::dismiss)
                    .doOnError(throwable -> progressDialog.dismiss());
        };
    }

    public static <U> ObservableTransformer<U, U> bindToLifecycleObservable(Context context, int messageRes) {
        return upstream -> {
            final MaterialDialog progressDialog = createDialog(context, messageRes);

            return upstream
                    .doOnSubscribe(disposable -> progressDialog.show())
                    .doOnNext(u -> progressDialog.dismiss())
                    .doOnDispose(progressDialog::dismiss)
                    .doOnError(throwable -> progressDialog.dismiss());
        };
    }


    public static <U> FlowableTransformer<U, U> bindFlowableProgress(Context context, int messageRes) {
        return upstream -> {
            final MaterialDialog progressDialog = createDialog(context, messageRes);

            return upstream
                    .doOnSubscribe(disposable -> progressDialog.show())
                    .doOnNext(u -> progressDialog.dismiss())
                    .doOnError(throwable -> progressDialog.dismiss());
        };
    }

    private static MaterialDialog createDialog(Context context, int messageRes) {
        return new MaterialDialog.Builder(context)
                .content(messageRes)
                .progress(true, 0)
                .cancelable(false)
                .build();
    }
}
