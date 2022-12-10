package exception;

import java.io.IOException;

public class NoSuchDirectoryOrFile extends IOException {

    private static final String EXCEPTION_MESSAGE = "No such directory or file by path [%s]";

    public NoSuchDirectoryOrFile(String message) {
        super(EXCEPTION_MESSAGE.formatted(message));
    }
}
