package at.stefanirndorfer.spreadit.view.dialogs;

import io.reactivex.Completable;
import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;

public interface DialogManager {

    CompletableTransformer bindCompletableProgress(int stringMessageRes);
    <U> FlowableTransformer<U, U> bindFlowableProgress(int stringMessageRes);

    /**
     * Shows to the user a dialog with a title, a message and OK action as the only option
     *
     * @param title   of the dialog shown
     * @param message of the dialog shown
     * @return a Completable
     */
    Completable showInfoDialog(String title, String message);

    /**
     * Shows to the user a dialog with a title, a message and OK action as the only option
     *
     * @param title   of the dialog shown
     * @param message of the dialog shown
     * @return a Completable
     */
    Completable showInfoDialogByResourceIds(int title, int message);

    /**
     * Shows to the user a dialog with a message and OK action as the only option
     * It doesn't show any dialog title.
     *
     * @param message of the dialog shown
     * @return a Completable
     */
    Completable showInfoDialogNoTitle(String message);


    /**
     * Shows to the user a dialog with a title, message and two customizable action texts,
     * one positive (e.g. OK) and one negative (e.g. Cancel)
     *
     * @param title          of the dialog shown
     * @param message        of the dialog shown
     * @param positiveAction of the dialog shown
     * @param negativeAction of the dialog shown
     * @return a Completable that completes when the user selects the positive action and returns
     * a RobDialogCancellationException when the user selects the negative action
     */
    Completable showInfoDialogCustomActions(int title, int message, int positiveAction, int negativeAction);

}
