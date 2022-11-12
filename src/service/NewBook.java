package service;

import java.io.IOException;
import java.util.Scanner;

public class NewBook {
    private String nameOfNewBook;
    private String authorOfNewBook;
    private String contentOfNewBook;
    private String isElectronic;
    
    private final Scanner scanner;

    public NewBook() {
        this.scanner = new Scanner(System.in);
    }

    public String getNameOfNewBook() {
        return this.nameOfNewBook;
    }

    public void setNameOfNewBook(String nameOfNewBook) {
        this.nameOfNewBook = nameOfNewBook;
    }

    public String getAuthorOfNewBook() {
        return this.authorOfNewBook;
    }

    public void setAuthorOfNewBook(String authorOfNewBook) {
        this.authorOfNewBook = authorOfNewBook;
    }

    public String getContentOfNewBook() {
        return this.contentOfNewBook;
    }

    public void setContentOfNewBook(String contentOfNewBook) {
        this.contentOfNewBook = contentOfNewBook;
    }

    public String getIsElectronic() {
        return this.isElectronic;
    }

    public void setIsElectronic(String isElectronic) {
        this.isElectronic = isElectronic;
    }

    public void readingOfBook(LibraryService library) {
        System.out.println("Which book do you want to read? Enter the name of book");
        this.setNameOfNewBook(scanner.nextLine());
        System.out.println("Enter the author of book");
        this.setAuthorOfNewBook(scanner.nextLine());
        library.openBook(this.getNameOfNewBook(), this.getAuthorOfNewBook());
    }

    public void deletingOfBook(LibraryService library) throws IOException {
        System.out.println("Enter the name of book");
        this.setNameOfNewBook(scanner.nextLine());
        System.out.println("Enter the author of book");
        this.setAuthorOfNewBook(scanner.nextLine());
        if (library.getBooks().stream().anyMatch(book -> this.getNameOfNewBook().equalsIgnoreCase(book.getName()) && this.getAuthorOfNewBook().equalsIgnoreCase(book.getAuthor()))) {
            int indexOfBook = 0;
            for (int i = 0; i < library.getBooks().size(); i++) {
                if (this.getNameOfNewBook().equalsIgnoreCase(library.getBooks().get(i).getName()) && this.getAuthorOfNewBook().equalsIgnoreCase(library.getBooks().get(i).getAuthor())) {
                    indexOfBook = i;
                }
            }
            library.deleteBook(library.getBooks().get(indexOfBook), library.getAccountFromLibrary(library, this.getName(), this.getSurname()));
            System.out.println("Book is deleted");
        } else {
            System.out.println("There is no such book");
        }
    }
}
