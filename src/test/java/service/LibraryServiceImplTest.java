package service;

import exception.BookAlreadyExistsException;
import exception.NoSuchBookException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceImplTest {
    private static final String TEST_BOOK_NAME = "testBookName";
    private static final String TEST_BOOK_CONTENT = "testBookContent";

    @Mock
    StorageServiceImpl storageService;
    @Mock
    Scanner scanner;

    private LibraryService libraryService;

    @BeforeEach
    public void setup() {
    }

    @Test
    void addBook() throws BookAlreadyExistsException {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        when(storageService.addBook(TEST_BOOK_NAME, TEST_BOOK_NAME)).thenReturn(TEST_BOOK_NAME);
        libraryService = new LibraryServiceImpl(scanner, storageService);

        libraryService.addBook();
    }

    @Test
    void deleteBook() {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        storageService.deleteBook(TEST_BOOK_NAME);
        libraryService = new LibraryServiceImpl(scanner, storageService);
        libraryService.deleteBook();
    }

    @Test
    void readBook() {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        when(storageService.getBook(TEST_BOOK_NAME)).thenReturn(TEST_BOOK_CONTENT);
        libraryService = new LibraryServiceImpl(scanner, storageService);
        libraryService.readBook();
    }
}