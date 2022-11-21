package service;

import dto.Book;

import java.io.IOException;
import java.util.List;

public class LibraryService implements LibraryServiceInt, MenuService{
    private static List<Book> books;
    public static boolean isLibraryOpen = true;
    public LibraryService(Login login)  throws IOException, ClassNotFoundException {
        openOrCreateLibrary(login);
    }
    public static boolean isIsLibraryOpen() {
        return isLibraryOpen;
    }
    public static void setLibraryOpen(boolean libraryOpen) {
        LibraryService.isLibraryOpen = libraryOpen;
    }
    public static List<Book> getBooks() {
        return books;
    }
    public static void setBooks(List<Book> books) {
        LibraryService.books = books;
    }


}
