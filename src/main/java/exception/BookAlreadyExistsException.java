package exception;

public class BookAlreadyExistsException extends Exception {
    private static final String MESSAGE = "Book with name [%s] already exists";

    public BookAlreadyExistsException(String bookName) {
        super(MESSAGE.formatted(bookName));
    }
}
