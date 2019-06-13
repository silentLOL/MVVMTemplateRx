package at.stefanirndorfer.spreadit.viewmodel.binding;

import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;

/**
 * Adapter bindings for EditText.
 */

public class EditTextBindingAdapters {
    private EditTextBindingAdapters() {
    }

    @BindingAdapter({"android:drawableStart"})
    public static void drawableStart(EditText view, int value) {
        view.setCompoundDrawablesWithIntrinsicBounds(value, 0, 0, 0);
        //view.setDrawableStart(value);
    }

    @BindingAdapter({"inputType"})
    public static void setInputType(EditText view, int value) {
        view.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
    }

    @BindingAdapter({"textWatcher"})
    public static void setTextWatcher(EditText editText, TextWatcher watcher) {
        editText.addTextChangedListener(watcher);
    }
}
