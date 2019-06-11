package at.stefanirndorfer.spreadit.exceptions;

public class DialogCancellationException extends Throwable {
    public DialogCancellationException(String msg) {
        super(msg);
    }

    public DialogCancellationException(Throwable throwable) {
        super(throwable);
    }
}
