package service;

import java.io.IOException;

public interface StorageService {
    void openLibrary(Login login, LibraryService libraryService, Menu menu) throws IOException;

    void openOrCreateLibrary(Login login) throws IOException, ClassNotFoundException;

    void saving(Login login, LibraryService libraryService) throws IOException;
}
