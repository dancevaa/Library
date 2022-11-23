import service.LibraryService;
import service.Login;
import service.Menu;
import service.Storage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Login login = new Login();
        Menu menu = new Menu();
        LibraryService library = new LibraryService();
        Storage storage = new Storage(login);
        storage.openLibrary(login, library, menu);
        storage.saving(login, library);
    }
}








