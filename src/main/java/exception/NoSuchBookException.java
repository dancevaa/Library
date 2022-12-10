package exception;

public class NoSuchBookException extends NullPointerException{
    private static final String MESSAGE = "There is no such book with name [%s]";

    public NoSuchBookException(final String bookName) {
        super(MESSAGE.formatted(bookName));
    }
}
