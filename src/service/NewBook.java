package service;

import dto.Book;

import java.io.IOException;

public class NewBook extends Login{
    private String nameOfNewBook;
    private String authorOfNewBook;
    private String contentOfNewBook;
    private String isElectronic;

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
        this.setNameOfNewBook(library.scanner.nextLine());
        System.out.println("Enter the author of book");
        this.setAuthorOfNewBook(library.scanner.nextLine());
        library.openBook(this.getNameOfNewBook(), this.getAuthorOfNewBook());
    }

    public void deletingOfBook(LibraryService library) throws IOException {
        System.out.println("Enter the name of book");
        this.setNameOfNewBook(library.scanner.nextLine());
        System.out.println("Enter the author of book");
        this.setAuthorOfNewBook(library.scanner.nextLine());
        if (library.getBooks().stream().anyMatch(book -> this.getNameOfNewBook().equalsIgnoreCase(book.getName()) && this.getAuthorOfNewBook().equalsIgnoreCase(book.getAuthor()))) {
            int indexOfBook = 0;
            for (int i = 0; i < library.getBooks().size(); i++) {
                if (this.getNameOfNewBook().equalsIgnoreCase(library.getBooks().get(i).getName()) && this.getAuthorOfNewBook().equalsIgnoreCase(library.getBooks().get(i).getAuthor())) {
                    indexOfBook = i;
                }
            }
            library.deleteBook(library.getBooks().get(indexOfBook), library.getAccountFromLibrary(library, this.getYourName(), this.getYourSurname()));
            System.out.println("Book is deleted");
        } else {
            System.out.println("There is no such book");
        }
    }

    public void addingNewBook(LibraryService library) throws IOException {
        System.out.println("Enter the name of book");
        this.setNameOfNewBook(library.scanner.nextLine());
        System.out.println("Enter the author of book");
        this.setAuthorOfNewBook(library.scanner.nextLine());
        System.out.println("Enter the content of book");
        this.setContentOfNewBook(library.scanner.nextLine());
        System.out.println("Is it electronic or not? Write please YES or NO");
        this.setIsElectronic(library.scanner.nextLine());
        while (!this.getIsElectronic().equalsIgnoreCase("yes") && !this.getIsElectronic().equalsIgnoreCase("no")) {
            System.out.println("Write YES or NO");
            this.setIsElectronic(library.scanner.nextLine());
        }
        boolean isElectronic;
        if (this.getIsElectronic().equalsIgnoreCase("yes")) {
            isElectronic = true;
        } else {
            isElectronic = false;
        }
        library.addBook(new Book(this.getNameOfNewBook(), this.getAuthorOfNewBook(), this.getContentOfNewBook(), isElectronic), library.getAccountFromLibrary(library, this.getYourName(), this.getYourSurname()));
    }





}
