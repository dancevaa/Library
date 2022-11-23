package service;

import dto.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class LibraryService implements LibraryServiceInt {
    private static List<Book> books;
    private final String PATH_OF_LIBRARY = "/Users/annann/Desktop/Library/";
    private final String CONTENT_FILE = "/content_of_book.txt";
    private final Scanner scanner = new Scanner(System.in);

    public void addBook() {
        try {
            System.out.println("Enter the name of book");
            String bookName = scanner.nextLine();
            System.out.println("Enter the content of book");
            String bookContent = scanner.nextLine();
            books.add(new Book(bookName, bookContent));
            System.out.println("Book is added");
            createDirectoryAndFile(bookName, bookContent);
        } catch (Exception e) {
            System.out.println("Book is already exists");
        }

    }

    @Override
    public void deleteBook() throws IOException {
        System.out.println("Enter the name of book");
        String bookName = scanner.nextLine();
        if (books.stream().anyMatch(book -> book.getName().equals(bookName))) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getName().equals(bookName)) {
                    books.remove(i);
                }
            }
            System.out.println("Book is deleted");
        } else {
            System.out.println("There is no such book");
        }
        deleteDirectoryAndFile(bookName);
    }

    @Override
    public void readBook() {
        try {
            System.out.println("Enter the name of book");
            String bookName = scanner.nextLine();
            BufferedReader buff = new BufferedReader(new FileReader(PATH_OF_LIBRARY + bookName + CONTENT_FILE));
            System.out.println(buff.readLine());
            buff.close();
        } catch (Exception e) {
            System.out.println("Book is not found");
        }
    }

    @Override
    public void createDirectoryAndFile(String name, String content) throws IOException {
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + name);
        Files.createDirectory(pathOfBook);
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + name + CONTENT_FILE);
        Files.createFile(pathOfFile);
        Files.writeString(pathOfFile, content);
    }

    @Override
    public void deleteDirectoryAndFile(String name) throws IOException {
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + name + CONTENT_FILE);
        Files.delete(pathOfFile);
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + name);
        Files.delete(pathOfBook);
    }

    public static List<Book> getBooks() {
        return books;
    }

    public static void setBooks(List<Book> books) {
        LibraryService.books = books;
    }
}
