package service;

import dto.Book;

import java.io.IOException;

public class NewBook {
    private static String nameOfNewBook;
    private static String authorOfNewBook;
    private static String contentOfNewBook;
    private static String isElectronic;

    public static String getNameOfNewBook() {
        return nameOfNewBook;
    }

    public static void setNameOfNewBook(String nameOfNewBook) {
        NewBook.nameOfNewBook = nameOfNewBook;
    }

    public static String getAuthorOfNewBook() {
        return authorOfNewBook;
    }

    public static void setAuthorOfNewBook(String authorOfNewBook) {
        NewBook.authorOfNewBook = authorOfNewBook;
    }

    public static String getContentOfNewBook() {
        return contentOfNewBook;
    }

    public static void setContentOfNewBook(String contentOfNewBook) {
        NewBook.contentOfNewBook = contentOfNewBook;
    }

    public static String getIsElectronic() {
        return isElectronic;
    }

    public static void setIsElectronic(String isElectronic) {
        NewBook.isElectronic = isElectronic;
    }
    public static void readingOfBook(LibraryService library) {
        System.out.println("Which book do you want to read? Enter the name of book");
        NewBook.setNameOfNewBook(LibraryService.scanner.nextLine());
        System.out.println("Enter the author of book");
        NewBook.setAuthorOfNewBook(LibraryService.scanner.nextLine());
        library.openBook(NewBook.getNameOfNewBook(), NewBook.getAuthorOfNewBook());
    }

    public static void deletingOfBook(LibraryService library) throws IOException {
        System.out.println("Enter the name of book");
        NewBook.setNameOfNewBook(LibraryService.scanner.nextLine());
        System.out.println("Enter the author of book");
        NewBook.setAuthorOfNewBook(LibraryService.scanner.nextLine());
        if (library.getBooks().stream().anyMatch(book -> NewBook.getNameOfNewBook().equalsIgnoreCase(book.getName()) && NewBook.getAuthorOfNewBook().equalsIgnoreCase(book.getAuthor()))) {
            int indexOfBook = 0;
            for (int i = 0; i < library.getBooks().size(); i++) {
                if (NewBook.getNameOfNewBook().equalsIgnoreCase(library.getBooks().get(i).getName()) && NewBook.getAuthorOfNewBook().equalsIgnoreCase(library.getBooks().get(i).getAuthor())) {
                    indexOfBook = i;
                }
            }
            library.deleteBook(library.getBooks().get(indexOfBook), Login.getAccountFromLibrary(library, Login.getYourName(), Login.getYourSurname()));
            System.out.println("Book is deleted");
        } else {
            System.out.println("There is no such book");
        }
    }

    public static void addingNewBook(LibraryService library) throws IOException {
        System.out.println("Enter the name of book");
        NewBook.setNameOfNewBook(LibraryService.scanner.nextLine());
        System.out.println("Enter the author of book");
        NewBook.setAuthorOfNewBook(LibraryService.scanner.nextLine());
        System.out.println("Enter the content of book");
        NewBook.setContentOfNewBook(LibraryService.scanner.nextLine());
        System.out.println("Is it electronic or not? Write please YES or NO");
        NewBook.setIsElectronic(LibraryService.scanner.nextLine());
        while (!NewBook.getIsElectronic().equalsIgnoreCase("yes") && !NewBook.getIsElectronic().equalsIgnoreCase("no")) {
            System.out.println("Write YES or NO");
            NewBook.setIsElectronic(LibraryService.scanner.nextLine());
        }
        boolean isElectronic;
        if (NewBook.getIsElectronic().equalsIgnoreCase("yes")) {
            isElectronic = true;
        } else {
            isElectronic = false;
        }
        library.addBook(new Book(NewBook.getNameOfNewBook(), NewBook.getAuthorOfNewBook(), NewBook.getContentOfNewBook(), isElectronic), Login.getAccountFromLibrary(library, Login.getYourName(), Login.getYourSurname()));
    }

}
