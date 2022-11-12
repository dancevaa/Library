import service.LibraryService;

import java.io.IOException;

public class Main {


    public static void main(String[] args) throws IOException {
        LibraryService library = new LibraryService();
        boolean isLibraryOpen = true;
        library.openLibrary(library, isLibraryOpen);
    }


    }








