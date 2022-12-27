package service;

import exception.BookAlreadyExistsException;
import exception.NoSuchActionException;
import exception.NoSuchBookException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceImplTest {
    private static final String TEST_BOOK_NAME = "testBookName";
    private static final String TEST_BOOK_CONTENT = "testBookContent";
    private static final String EXIST_BOOK = "existBook";
    private static final String EXIST_BOOK_CONTENT = "existBookContent";


    @Mock
    StorageServiceImpl storageService;
    @Mock
    Scanner scanner;

    private LibraryService libraryService;

    @BeforeEach
    public void setup() {
        libraryService = new LibraryServiceImpl(scanner, storageService);
    }

    @AfterEach
    void afterEach(){
        verifyNoMoreInteractions(scanner, storageService);
    }

    @Test
    void shouldAddBook() throws BookAlreadyExistsException {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        when(storageService.addBook(TEST_BOOK_NAME, TEST_BOOK_NAME)).thenReturn(TEST_BOOK_NAME);
        libraryService.addBook();
        verify(scanner, times(2)).nextLine();
        verify(storageService).addBook(TEST_BOOK_NAME, TEST_BOOK_NAME);
    }

//    @Test
//    void shouldThrowExceptionIfBookIsAlreadyExist() throws BookAlreadyExistsException {
//        when(scanner.nextLine()).thenReturn(EXIST_BOOK);
//        when(storageService.addBook(EXIST_BOOK, EXIST_BOOK_CONTENT)).thenReturn(EXIST_BOOK);
//        assertThrows(BookAlreadyExistsException.class, ()-> libraryService.addBook());
//    }

    @Test
    void shouldDeleteBook() {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        storageService.deleteBook(TEST_BOOK_NAME);
        libraryService.deleteBook();
        verify(scanner).nextLine();
        verify(storageService, times(2)).deleteBook(TEST_BOOK_NAME);
    }

    @Test
    void shouldReadBook() {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        when(storageService.getBook(TEST_BOOK_NAME)).thenReturn(TEST_BOOK_CONTENT);
        libraryService.readBook();
        verify(scanner).nextLine();
        verify(storageService).getBook(TEST_BOOK_NAME);
    }
}