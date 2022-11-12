package service;

import dto.Account;
import dto.Book;
import dto.Role;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryService {
    public static Scanner scanner = new Scanner(System.in);
    public static final String PATH_OF_LIBRARY = "/Users/annann/Desktop/Library/";
    public static final String CONTENT_FILE = "/content_of_book.txt";
    private List<Book> books;
    private List<Account> accounts;

    public LibraryService() throws IOException {
        books = new ArrayList<>();
        accounts = new ArrayList<>();
        Files.createDirectory(Path.of(PATH_OF_LIBRARY));
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
            createDirectoryAndFile(book);
            System.out.printf("Book %s is added", book);
        } else {
            System.out.println("Access denied");
        }
    }

    private static void createDirectoryAndFile(Book book) throws IOException {
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + book.getName());
        Files.createDirectory(pathOfBook);
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + book.getName() + CONTENT_FILE);
        Files.createFile(pathOfFile);
        Files.writeString(pathOfFile, book.getContent());
    }

    public void deleteBook(Book book, Account account) throws IOException {
        if (account.getRole() == Role.ADMIN) {
            books.remove(book);
            deleteDirectoryAndFile(book);
            System.out.printf("Book %s is removed", book);
        } else {
            System.out.println("Access denied");
        }
    }

    private static void deleteDirectoryAndFile(Book book) throws IOException {
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + book.getName() + CONTENT_FILE);
        Files.delete(pathOfFile);
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + book.getName());
        Files.delete(pathOfBook);
    }

    public void openBook(String name, String author) {
        books.stream().filter(book -> book.getName().equals(name) && book.getAuthor().equals(author)).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("Book is not found"));
    }

    public void addAccount(String name, String surname, Role role){
        accounts.add(new Account(name, surname, role));
    }
    public Account getAccountFromLibrary(LibraryService library, String name, String surname) {
        return library.getAccounts().stream().filter(account -> name.equals(account.getName()) && surname.equals(account.getSurname())).findFirst().stream().toList().get(0);
    }

    public static void openLibrary(LibraryService library, boolean isLibraryOpen) throws IOException {
        do {Login newLogin = new Login();
            newLogin.login();
            if (newLogin.isAccountNotExist(library, newLogin.getYourName(), newLogin.getYourSurname())) {
                newLogin.addingAccount(library);
            }
            boolean doSomething = true;
            do {
                Menu menu = new Menu();
                NewBook newBook = new NewBook();
                if (newLogin.isAccountOfAdmin(library, newLogin.getYourName(), newLogin.getYourSurname(), Role.ADMIN)) {
                    menu.choosingAction();
                    if (menu.getChoosingAction().equalsIgnoreCase("add")) {
                        newBook.addingNewBook(library);
                    } else if (menu.getChoosingAction().equalsIgnoreCase("delete")) {
                        newBook.deletingOfBook(library);
                    } else if (menu.getChoosingAction().equalsIgnoreCase("read")) {
                        newBook.readingOfBook(library);
                    }

                } else if (newLogin.isAccountOfAdmin(library, newLogin.getYourName(), newLogin.getYourSurname(), Role.USER)) {
                    newBook.readingOfBook(library);
                }
                doSomething = menu.isDoSomething(doSomething);
            }
            while (doSomething == true);
        }
        while (isLibraryOpen);
    }
}
