package service;

import Exception.*;

public interface LibraryService {
    void addBook();

    void deleteBook() throws NoSuchDirectoryOrFile;

    void readBook();

    void createDirectoryAndFile(String name, String content) throws FileIsAlreadyExist;

    void deleteDirectoryAndFile(String name) throws NoSuchDirectoryOrFile;
}
