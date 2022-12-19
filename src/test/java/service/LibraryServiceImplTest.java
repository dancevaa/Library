package service;

import exception.BookAlreadyExistsException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Scanner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class LibraryServiceImplTest {
    private static final String TEST_BOOK_NAME = "testBookName";
    private static final String TEST_BOOK_CONTENT = "testBookContent";

    @Mock
    private StorageServiceImpl storageService;
    @Mock
    private Scanner scanner;
    private LibraryService libraryService;


    @Test
    void addBook() throws BookAlreadyExistsException {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        when(storageService.addBook(TEST_BOOK_NAME, TEST_BOOK_NAME)).thenReturn(TEST_BOOK_NAME);
        libraryService = new LibraryServiceImpl(scanner, storageService);

    }

    @Test
    void deleteBook() {
    }

    @Test
    void readBook() {
    }
}