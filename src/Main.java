import service.LibraryService;
import service.Login;
import service.SavedLibrary;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Login login = new Login();
        LibraryService library = new LibraryService(login);
        library.openLibrary();
        SavedLibrary savedLibrary = new SavedLibrary(login.getAccountList(), library.getBooks());
        FileOutputStream outputStream = new FileOutputStream(LibraryService.PATH_OF_LIBRARY + "save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(savedLibrary);
        objectOutputStream.close();
    }
}








