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
        library.openLibrary(login);
        SavedLibrary savedLibrary = new SavedLibrary(login.getAccountList(), library.getBooks());
        FileOutputStream outputStream = new FileOutputStream("/Users/annann/Desktop/Library/save.ser");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(savedLibrary);
        objectOutputStream.close();
    }
}








