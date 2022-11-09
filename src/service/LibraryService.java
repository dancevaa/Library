package service;

import dto.Account;
import dto.Book;
import dto.Role;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LibraryService {


    private List<Book> books;
    private List<Account> accounts;

    public LibraryService() throws IOException {
        books = new ArrayList<>();
        accounts = new ArrayList<>();
        books.add(new Book("sdsfsd", "ghghj", "hgj", true));
        Files.createDirectory(Path.of("/Users/annann/Desktop/Library"));
    }
    public List<Book> getBooks() {
        return books;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addBook(Book book, Account account) throws IOException {
        if (account.getRole() == Role.ADMIN) {
            books.add(book);
            Path pathOfBook = Path.of("/Users/annann/Desktop/Library/" + book.getName());
            Files.createDirectory(pathOfBook);
            Path pathOfFile = Path.of("/Users/annann/Desktop/Library/" + book.getName() + "/content_of_book.txt");
            Files.createFile(pathOfFile);
            Files.writeString(pathOfFile, book.getContent());
            System.out.printf("Book %s is added", book);
        } else {
            System.out.println("Access denied");
        }
    }

    public void deleteBook(Book book, Account account) throws IOException {
        if (account.getRole() == Role.ADMIN) {
            books.remove(book);
            Path pathOfFile = Path.of("/Users/annann/Desktop/Library/" + book.getName() + "/content_of_book.txt");
            Files.delete(pathOfFile);
            Path pathOfBook = Path.of("/Users/annann/Desktop/Library/" + book.getName());
            Files.delete(pathOfBook);
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
