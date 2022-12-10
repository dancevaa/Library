package exception;

import java.io.IOException;

public class DirectoryIsAlreadyExistException extends IOException {

    private static final String EXCEPTION_MESSAGE = "Directory is already exist by path [%s]";

    public DirectoryIsAlreadyExistException(String message) {
        super(EXCEPTION_MESSAGE.formatted(message));
    }

}
