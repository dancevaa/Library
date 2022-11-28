package exception;

public class NoSuchDirectoryOrFile extends Exception{
    public NoSuchDirectoryOrFile() {
    }

    public NoSuchDirectoryOrFile(String message) {
        super("No such directory or file by path " + message);
    }
}
