package service;

import dto.Book;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public interface LibraryServiceInt {
    String PATH_OF_LIBRARY = "/Users/annann/Desktop/Library/";
    String CONTENT_FILE = "/content_of_book.txt";
    Scanner scanner = new Scanner(System.in);
    default void addBook() throws IOException {
        System.out.println("Enter the name of book");
        String bookName = scanner.nextLine();
        System.out.println("Enter the content of book");
        String bookContent = scanner.nextLine();
        LibraryService.getBooks().add(new Book(bookName, bookContent));
        System.out.println("Book is added");
        createDirectoryAndFile(bookName, bookContent);
    }

    default void deleteBook() throws IOException {
        System.out.println("Enter the name of book");
        String bookName = scanner.nextLine();
        if(LibraryService.getBooks().stream().anyMatch(book -> book.getName().equals(bookName))){
            for (int i = 0; i < LibraryService.getBooks().size(); i++) {
                if (LibraryService.getBooks().get(i).getName().equals(bookName)) {
                    LibraryService.getBooks().remove(i);
                }
            }
            System.out.println("Book is deleted");
        } else {
            System.out.println("There is no such book");
        }
        deleteDirectoryAndFile(bookName);
    }

    default void readBook() {
        System.out.println("Enter the name of book");
        String bookName = scanner.nextLine();
        LibraryService.getBooks().stream().filter(book -> book.getName().equals(bookName)).findFirst().ifPresentOrElse(System.out::println, () -> System.out.println("Book is not found"));
    }

    default void createDirectoryAndFile(String name, String content) throws IOException {
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + name);
        Files.createDirectory(pathOfBook);
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + name + CONTENT_FILE);
        Files.createFile(pathOfFile);
        Files.writeString(pathOfFile, content);
    }
    default void deleteDirectoryAndFile(String name) throws IOException {
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + name + CONTENT_FILE);
        Files.delete(pathOfFile);
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + name);
        Files.delete(pathOfBook);
    }
}
