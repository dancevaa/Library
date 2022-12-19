package service;

import exception.BookAlreadyExistsException;
import exception.NoSuchBookException;
import exception.NoSuchDirectoryException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StorageServiceImplTest {

    private static final String TEST_BOOK_NAME = "testBookName";
    private static final String TEST_BOOK_CONTENT = "testBookContent";
    private static final String UNIX_FILE_PATH_DELIMITER = "/";
    private static final String WINDOWS_FILE_PATH_DELIMITER = "\\";
    private static final String UNIX_TEST_LIBRARY_DIRECTORY = "/testLibrary/";
    private static final String WINDOWS_TEST_LIBRARY_DIRECTORY = "\\testLibrary\\";

    private String libraryPath;
    private StorageService storageService;

    @BeforeEach
    void setUp() {
        final String libraryPath = isFileSystemIsUnix() ? UNIX_TEST_LIBRARY_DIRECTORY : WINDOWS_TEST_LIBRARY_DIRECTORY;
        final String absolutePathToLibrary = new File("").getAbsolutePath() + libraryPath;
        createLibraryDirectory(absolutePathToLibrary);
        this.libraryPath = absolutePathToLibrary;
    }

    @AfterEach
    void removeLibraryFolder() {
        removeLibraryDirectory(libraryPath);
    }

    @Test
    void shouldGetBook() {
        storageService = new StorageServiceImpl(libraryPath);
        createBook(libraryPath + TEST_BOOK_NAME, TEST_BOOK_CONTENT);

        final String actualContent = storageService.getBook(TEST_BOOK_NAME);
        assertEquals(TEST_BOOK_CONTENT, actualContent);
    }

    @Test
    void shouldThrowExceptionIfBookDoesNotExists() {
        storageService = new StorageServiceImpl(libraryPath);
        assertThrows(NoSuchBookException.class, () -> storageService.getBook(TEST_BOOK_NAME));
    }

    @Test
    void shouldThrowExceptionIfLibraryDirectoryDoesNotExists() {
        storageService = new StorageServiceImpl("NotLibraryPath");
        assertThrows(NoSuchDirectoryException.class, () -> storageService.getBook(TEST_BOOK_NAME));

    }

    @Test
    void shouldAddBook() throws BookAlreadyExistsException {
        storageService = new StorageServiceImpl(libraryPath);
        final String actual = storageService.addBook(TEST_BOOK_NAME, TEST_BOOK_CONTENT);
        verifyBookWasCreatedWithRequiredContent(actual, TEST_BOOK_CONTENT);
    }

    @Test
    void shouldThrowBookAlreadyExistsException() {
        storageService = new StorageServiceImpl(libraryPath);
        createBook(libraryPath + TEST_BOOK_NAME, TEST_BOOK_CONTENT);
        assertThrows(BookAlreadyExistsException.class, () -> storageService.addBook(TEST_BOOK_NAME, TEST_BOOK_CONTENT));
    }

    @Test
    void shouldDeleteBook() {
        storageService = new StorageServiceImpl(libraryPath);
        createBook(libraryPath + TEST_BOOK_NAME, TEST_BOOK_CONTENT);
        storageService.deleteBook(TEST_BOOK_NAME);
        verifyBookDoesNotExists(TEST_BOOK_NAME);
    }

    @Test
    void shouldThrowNoSuchBookException() {
        storageService = new StorageServiceImpl(libraryPath);
        assertThrows(NoSuchBookException.class, () -> storageService.deleteBook(TEST_BOOK_NAME));
    }

    @SneakyThrows
    private void verifyBookWasCreatedWithRequiredContent(final String bookName, final String TEST_BOOK_CONTENT) {
        final Optional<String[]> allBooks = Optional.ofNullable(new File(libraryPath).list());
        assertTrue(allBooks.isPresent());

        final Optional<String> actual = Arrays.stream(allBooks.get()).filter(bookName::equals).findFirst();
        assertTrue(actual.isPresent());

        final String actualContent = Files.readString(Path.of(libraryPath + bookName));
        assertEquals(TEST_BOOK_CONTENT, actualContent);
    }

    private void verifyBookDoesNotExists(final String bookName) {
        final Optional<String[]> allBooks = Optional.ofNullable(new File(libraryPath).list());
        final Optional<String> actual = Arrays.stream(allBooks.get()).filter(bookName::equals).findFirst();
        assertTrue(actual.isEmpty());
    }

    @SneakyThrows
    private void createBook(final String bookPath, final String bookContent) {
        Path path = Path.of(bookPath);
        Files.createFile(path);
        Files.writeString(path, bookContent);
    }

    @SneakyThrows
    private void createLibraryDirectory(final String directoryPath) {
        String pathToCreateDirectory = directoryPath.formatted(new File("").getAbsolutePath());
        Files.createDirectories(Path.of(pathToCreateDirectory));
    }

    @SneakyThrows
    private void removeLibraryDirectory(final String directoryPath) {
        final String[] allBooks = new File(directoryPath).list();
        final String fileDelimiter = isFileSystemIsUnix() ? UNIX_FILE_PATH_DELIMITER : WINDOWS_FILE_PATH_DELIMITER;
        for (String bookName : allBooks) {
            Files.delete(Path.of(directoryPath + fileDelimiter + bookName));
        }
        Files.delete(Path.of(directoryPath));
    }

    private static boolean isFileSystemIsUnix() {
        final String absolutePath = new File("").getAbsolutePath();
        return absolutePath.contains(UNIX_FILE_PATH_DELIMITER);
    }
}