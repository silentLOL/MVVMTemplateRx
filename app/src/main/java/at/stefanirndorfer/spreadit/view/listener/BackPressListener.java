package at.stefanirndorfer.spreadit.view.listener;

public interface BackPressListener {
    /**
     * Has an action for the back button
     *
     * @return true when the listener can provide an action for the back button
     */
    boolean hasBackAction();

    /**
     * On back button action
     *
     * @return true when the back button was process by the listener
     */
    boolean onBackPressed();
}
