package exception;

public class NoSuchActionException extends IllegalArgumentException {

    private static final String MESSAGE = "No such action [%s]";
    public NoSuchActionException(final String action) {
        super(MESSAGE.formatted(action));
    }
}
