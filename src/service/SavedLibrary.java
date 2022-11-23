package service;

import dto.Account;
import dto.Book;

import java.io.Serializable;
import java.util.List;

public class SavedLibrary implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Account> savedAccounts;
    private List <Book> savedBooks;
    public SavedLibrary(List<Account> savedAccounts, List<Book> savedBooks) {
        this.savedAccounts = savedAccounts;
        this.savedBooks = savedBooks;
    }
    public List<Account> getSavedAccounts() {
        return savedAccounts;
    }
    public List<Book> getSavedBooks() {
        return savedBooks;
    }

}
