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

    private final Login login;

    public static final String PATH_OF_LIBRARY = "/Users/annann/Desktop/Library/";
    public static final String CONTENT_FILE = "/content_of_book.txt";

    private List<Book> books;
    private List<Account> accounts;
    private Scanner scanner;

    public LibraryService(Login login) throws IOException {
        this.login = login;
        books = new ArrayList<>();
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
        Files.createDirectory(Path.of(PATH_OF_LIBRARY));
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void addBook() {
        System.out.println("Enter the name of book");
        String bookName = scanner.nextLine();
        System.out.println("Enter the author of book");
        String bookAuthor = scanner.nextLine();
        System.out.println("Enter the content of book");
        String bookContent = scanner.nextLine();
        System.out.println("Is it electronic or not? Write please YES or NO");
        String bookElectronic = scanner.nextLine();
        books.add(new Book(bookName, bookAuthor, bookContent, bookElectronic));
    }

    private void createDirectoryAndFile(Book book) throws IOException {
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

    private void deleteDirectoryAndFile(Book book) throws IOException {
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

    public void openLibrary(LibraryService library, boolean isLibraryOpen) throws IOException {
        do {
            Account account = login.login();

            boolean doSomething = true;
            do {
                Menu menu = new Menu();
                NewBook newBook = new NewBook();
                if (Role.ADMIN.equals(account.getRole())) {
                    switch (menu.choosingAction()) {
                        case ADD -> addBook();
                        case READ -> newBook.readingOfBook(library);
                        case DELETE -> newBook.deletingOfBook(library);
                    }
                } else {
                    newBook.readingOfBook(library);
                }
                doSomething = menu.isDoSomething(doSomething, library);
            }
            while (doSomething == true);
        }
        while (isLibraryOpen);
    }
}
