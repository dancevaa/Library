package exception;

import java.io.IOException;

public class FileIsAlreadyExist extends IOException {

    private static final String EXCEPTION_MESSAGE = "File is already exist by path [%s]";

    public FileIsAlreadyExist(String message) {
        super(EXCEPTION_MESSAGE.formatted(message));
    }

}
