package service;

import dto.Account;
import dto.Book;
import dto.Role;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryService {

    private static boolean isLibraryOpen = true;
    private final Login login;

    public static final String PATH_OF_LIBRARY = "/Users/annann/Desktop/Library/";
    public static final String CONTENT_FILE = "/content_of_book.txt";

    private List<Book> books;
    private List<Account> accounts;
    private Scanner scanner;

    public LibraryService(Login login) throws IOException, ClassNotFoundException {
        this.login = login;
        scanner = new Scanner(System.in);
        if(!Files.exists(Path.of(PATH_OF_LIBRARY))){
            Files.createDirectory(Path.of(PATH_OF_LIBRARY));
            Files.createFile(Path.of(PATH_OF_LIBRARY + "save.ser"));
            books = new ArrayList<>();
            login.setAccountList(new ArrayList<>());
        }
        else {
            FileInputStream fileInputStream = new FileInputStream(PATH_OF_LIBRARY + "save.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            SavedLibrary savedLibrary = (SavedLibrary) objectInputStream.readObject();
            books = savedLibrary.getSavedBooks();
            login.setAccountList(savedLibrary.getSavedAccounts());
        }
    }

    public void addBook() throws IOException {
        System.out.println("Enter the name of book");
        String bookName = scanner.nextLine();
        System.out.println("Enter the author of book");
        String bookAuthor = scanner.nextLine();
        System.out.println("Enter the content of book");
        String bookContent = scanner.nextLine();
        System.out.println("Is it electronic or not? Write please YES or NO");
        String bookElectronic = scanner.nextLine();
        while (!bookElectronic.equalsIgnoreCase("yes") && !bookElectronic.equalsIgnoreCase("no")){
            System.out.println("Write Yes or NO");
            bookElectronic = scanner.nextLine();
        }
        books.add(new Book(bookName, bookAuthor, bookContent, bookElectronic));
        System.out.println("Book is added");
        createDirectoryAndFile(bookName, bookContent);
    }


    public void deleteBook() throws IOException {
        System.out.println("Enter the name of book");
        String bookName = scanner.nextLine();
        System.out.println("Enter the author of book");
        String bookAuthor = scanner.nextLine();
        if(books.stream().anyMatch(book -> book.getName().equals(bookName) && book.getAuthor().equals(bookAuthor))){
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getName().equals(bookName) && books.get(i).getAuthor().equals(bookAuthor)) {
                    books.remove(i);
                }
            }
            System.out.println("Book is deleted");
        } else {
            System.out.println("There is no such book");
        }
        deleteDirectoryAndFile(bookName);
    }

    private void createDirectoryAndFile(String name, String content) throws IOException {
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + name);
        Files.createDirectory(pathOfBook);
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + name + CONTENT_FILE);
        Files.createFile(pathOfFile);
        Files.writeString(pathOfFile, content);
    }
    private void deleteDirectoryAndFile(String name) throws IOException {
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + name + CONTENT_FILE);
        Files.delete(pathOfFile);
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + name);
        Files.delete(pathOfBook);
    }

    public void readBook() {
        System.out.println("Enter the name of book");
        String bookName = scanner.nextLine();
        System.out.println("Enter the author of book");
        String bookAuthor = scanner.nextLine();
        books.stream().filter(book -> book.getName().equals(bookName) && book.getAuthor().equals(bookAuthor)).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("Book is not found"));
    }

    public void openLibrary() throws IOException {
        do {
            Account account = login.login();
            boolean doSomething = true;
            do {
                Menu menu = new Menu();
                if (Role.ADMIN.equals(account.getRole())) {
                    switch (menu.choosingAction()) {
                        case ADD -> addBook();
                        case READ -> readBook();
                        case DELETE -> deleteBook();
                    }
                } else {
                    readBook();
                }
                doSomething = menu.isDoSomething(doSomething);
            }
            while (doSomething == true);
        }
        while (this.isLibraryOpen);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
    public List<Book> getBooks() {
        return books;
    }
    public static void setLibraryOpen(boolean libraryOpen) {
        LibraryService.isLibraryOpen = libraryOpen;
    }
}
