package service;

import exception.BookAlreadyExistsException;
import exception.NoSuchBookException;
import exception.NoSuchDirectoryException;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
public class StorageServiceImpl implements StorageService {
    private final String pathToLibrary;

    @Override
    public String getBook(final String bookName) {
        final Optional<String[]> allBooks = Optional.ofNullable(new File(pathToLibrary).list());
        if (allBooks.isPresent()) {
            final String requiredBook = Arrays.stream(allBooks.get())
                    .filter(bookName::equals)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchBookException(bookName));

            return getBookContent(pathToLibrary + requiredBook);
        } else {
            throw new NoSuchDirectoryException();
        }
    }

    @Override
    public String addBook(final String bookName, final String bookContent) throws BookAlreadyExistsException {
        final Path pathToBook = Path.of(pathToLibrary + bookName);
        try {
            Files.createFile(pathToBook);
            Files.writeString(pathToBook, bookContent);
        } catch (IOException e) {
            throw new BookAlreadyExistsException(bookName);
        }
        return bookName;
    }

    @Override
    public void deleteBook(final String bookName) throws NoSuchBookException {
        final Path pathToBook = Path.of(pathToLibrary + bookName);
        try {
            Files.delete(pathToBook);
        } catch (IOException e) {
            throw new NoSuchBookException(bookName);
        }
    }

    @SneakyThrows
    private String getBookContent(final String s) {
        return Files.readString(Path.of(s));
    }
}
