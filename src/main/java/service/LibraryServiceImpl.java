package service;

import exception.BookAlreadyExistsException;
import exception.NoSuchBookException;
import exception.NoSuchDirectoryException;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class LibraryServiceImpl implements LibraryService {
    private final Scanner scanner;
    private final StorageService storageService;

    public LibraryServiceImpl(final Scanner scanner, final StorageService storageService) {
        this.storageService = storageService;
        this.scanner = scanner;
    }

    @Override
    public void addBook() {
        log.info("Enter the name of book");
        final String bookName = scanner.nextLine();
        log.info("Enter the content of book");
        final String bookContent = scanner.nextLine();
        try {
            final String book = storageService.addBook(bookName, bookContent);
            log.info("Book [%s] was successfully added".formatted(book));
        } catch (BookAlreadyExistsException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void deleteBook() {
        log.info("Enter the name of book to delete");
        final String bookName = scanner.nextLine();
        try {
            storageService.deleteBook(bookName);
            log.info("Book [%s] was deleted".formatted(bookName));
        } catch (NoSuchBookException e) {
            log.warn(e.getMessage());
        }
    }

    @Override
    public void readBook() {
        log.info("Enter the name of book");
        final String bookName = scanner.nextLine();
        try {
            final String bookContent = storageService.getBook(bookName);
            log.info(bookContent);
        } catch (NoSuchDirectoryException | NoSuchBookException e) {
            log.warn(e.getMessage());
        }
    }
}
