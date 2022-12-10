package exception;

public class NoSuchDirectoryException extends NullPointerException {

    private static final String MESSAGE = "There is no directory [library]";

    public NoSuchDirectoryException() {
        super(MESSAGE);
    }
}
