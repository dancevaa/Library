package service;

import dto.Book;
import exception.FileIsAlreadyExist;
import exception.NoSuchDirectoryOrFile;
import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;


@Log4j
public class LibraryServiceImpl implements LibraryService {

    private static List<Book> books;
    private final String PATH_OF_LIBRARY = "/Users/annann/Desktop/Library/";
    private final String CONTENT_FILE = "/content_of_book.txt";
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addBook() {
        try {
            log.info("Enter the name of book");
            String bookName = scanner.nextLine();
            log.info("Enter the content of book");
            String bookContent = scanner.nextLine();
            books.add(new Book(bookName, bookContent));
            log.info("Book is added");
            createDirectoryAndFile(bookName, bookContent);
        } catch (Exception e) {
            log.info("Book is already exists");
        }

    }

    @Override
    public void deleteBook() throws NoSuchDirectoryOrFile {
        log.info("Enter the name of book");
        String bookName = scanner.nextLine();
        if (books.stream().anyMatch(book -> book.getName().equals(bookName))) {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).getName().equals(bookName)) {
                    books.remove(i);
                }
            }
            log.info("Book is deleted");
        } else {
            log.info("There is no such book");
        }
        deleteDirectoryAndFile(bookName);
    }

    @Override
    public void readBook() {
        try {
            log.info("Enter the name of book");
            String bookName = scanner.nextLine();
            BufferedReader buff = new BufferedReader(new FileReader(PATH_OF_LIBRARY + bookName + CONTENT_FILE));
            log.info(buff.readLine());
            buff.close();
        } catch (Exception e) {
            log.info("Book is not found");
        }
    }

    @Override
    public void createDirectoryAndFile(String name, String content) throws FileIsAlreadyExist {
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + name);
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + name + CONTENT_FILE);
        try {
            Files.createDirectory(pathOfBook);
            Files.createFile(pathOfFile);
            Files.writeString(pathOfFile, content);
        }
        catch (IOException e){
            throw new FileIsAlreadyExist(pathOfBook.toString());
        }
    }

    @Override
    public void deleteDirectoryAndFile(String name) throws NoSuchDirectoryOrFile {
        Path pathOfFile = Path.of(PATH_OF_LIBRARY + name + CONTENT_FILE);
        Path pathOfBook = Path.of(PATH_OF_LIBRARY + name);
        try{
            Files.delete(pathOfFile);
            Files.delete(pathOfBook);
        }
        catch (IOException e){
            throw new NoSuchDirectoryOrFile(pathOfFile.toString());
        }

    }

    public static List<Book> getBooks() {
        return books;
    }

    public static void setBooks(List<Book> books) {
        LibraryServiceImpl.books = books;
    }
}
