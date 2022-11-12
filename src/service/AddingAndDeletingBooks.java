package service;

import dto.Account;
import dto.Book;

import java.io.IOException;

public interface AddingAndDeletingBooks {
    void addBook(Book book, Account account) throws IOException;
    void deleteBook(Book book, Account account) throws IOException;
}
