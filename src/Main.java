import service.LibraryService;
import service.Login;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Login login = new Login();
        LibraryService library = new LibraryService(login);

        boolean isLibraryOpen = true;
        library.openLibrary(library, isLibraryOpen);
    }
}








