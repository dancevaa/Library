package exception;

public class FileIsAlreadyExist extends Exception{

    public FileIsAlreadyExist() {
    }
    public FileIsAlreadyExist(String message) {
        super("File is already exist by path " + message);
    }

}
