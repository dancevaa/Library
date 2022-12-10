package service;

import exception.BookAlreadyExistsException;

public interface StorageService {

    String getBook(String bookName);

    String addBook(String bookName, String bookContent) throws BookAlreadyExistsException;

    void deleteBook(String bookName);
}
