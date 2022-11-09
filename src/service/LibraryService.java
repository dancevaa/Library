package service;

import dto.Account;
import dto.Book;
import dto.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryService {
    private List<Book> books;
    private List<Account> accounts;

    public LibraryService() {
        books = new ArrayList<>();
        accounts = new ArrayList<>();
        books.add(new Book("sdsfsd", "ghghj", "hgj", true));
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addBook(Book book, Account account) {
        if (account.getRole() == Role.ADMIN) {
            books.add(book);
            System.out.printf("Book %s is added", book);
        } else {
            System.out.println("Access denied");
        }
    }

    public void deleteBook(Book book, Account account) {
        if (account.getRole() == Role.ADMIN) {
            books.remove(book);
            System.out.printf("Book %s is removed", book);
        } else {
            System.out.println("Access denied");
        }
    }

    public void openBook(String name, String author) {
        books.stream().filter(book -> book.getName().equals(name) && book.getAuthor().equals(author)).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("Book is not found"));
    }

    public void addAccount(String name, String surname, Role role){
        accounts.add(new Account(name, surname, role));
    }


}
