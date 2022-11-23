package service;

import java.io.IOException;

public interface LibraryServiceInt {
    void addBook() throws IOException;

    void deleteBook() throws IOException;

    void readBook() throws IOException;

    void createDirectoryAndFile(String name, String content) throws IOException;

    void deleteDirectoryAndFile(String name) throws IOException;
}
