package service;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.read.ListAppender;
import exception.BookAlreadyExistsException;
import exception.NoSuchActionException;
import exception.NoSuchBookException;
import exception.NoSuchDirectoryException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import util.MemoryAppender;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryServiceImplTest {
    private static final String TEST_BOOK_NAME = "testBookName";
    private static final String TEST_BOOK_CONTENT = "testBookContent";

    @Mock
    StorageServiceImpl storageService;
    @Mock
    Scanner scanner;

    private LibraryService libraryService;
    private MemoryAppender memoryAppender;

    @BeforeEach
    public void setup() {
        libraryService = new LibraryServiceImpl(scanner, storageService);
//        configureLogListener();
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

    @Test
    void shouldThrowExceptionIfBookIsAlreadyExist() throws BookAlreadyExistsException {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        when(storageService.addBook(TEST_BOOK_NAME, TEST_BOOK_NAME)).thenThrow(new BookAlreadyExistsException(TEST_BOOK_NAME));
        libraryService.addBook();

        verify(scanner, times(2)).nextLine();
        verify(storageService).addBook(TEST_BOOK_NAME, TEST_BOOK_NAME);
    }

    @Test
    void shouldDeleteBook() {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        storageService.deleteBook(TEST_BOOK_NAME);
        libraryService.deleteBook();
        verify(scanner).nextLine();
        verify(storageService, times(2)).deleteBook(TEST_BOOK_NAME);
    }

    @Test
    void shouldThrowExceptionIfBookIsNotExists(){
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        Mockito.doThrow(new NoSuchBookException(TEST_BOOK_NAME)).doNothing().when(storageService).deleteBook(TEST_BOOK_NAME);
        libraryService.deleteBook();
        verify(scanner).nextLine();
        verify(storageService).deleteBook(TEST_BOOK_NAME);
    }

    @Test
    void shouldReadBook() {
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        when(storageService.getBook(TEST_BOOK_NAME)).thenReturn(TEST_BOOK_CONTENT);
        libraryService.readBook();
        verify(scanner).nextLine();
        verify(storageService).getBook(TEST_BOOK_NAME);
    }

    @Test
    void shouldThrowExceptionIfNoSuchDirectory(){
        when(scanner.nextLine()).thenReturn(TEST_BOOK_NAME);
        when(storageService.getBook(TEST_BOOK_NAME)).thenThrow(new NoSuchDirectoryException());
        libraryService.readBook();
        verify(scanner).nextLine();
        verify(storageService).getBook(TEST_BOOK_NAME);
    }



//    private void configureLogListener() {
//        Logger logger = (Logger) LoggerFactory.getLogger(LibraryServiceImpl.class);
//        memoryAppender = new MemoryAppender();
//        memoryAppender.setContext((LoggerContext) LoggerFactory.getILoggerFactory());
//        logger.setLevel(Level.DEBUG);
//        logger.addAppender(memoryAppender);
//        memoryAppender.start();
//    }
}