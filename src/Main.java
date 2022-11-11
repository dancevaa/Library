import dto.Account;
import dto.Book;
import dto.Role;
import service.LibraryService;
import service.Login;
import service.Menu;
import service.NewBook;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static final String NEW_ACCOUNT_ADDED = "new account of %s is added";
    public static final boolean ADD = Menu.getChoosingAction().equalsIgnoreCase("add");
    public static final boolean DELETE = Menu.getChoosingAction().equalsIgnoreCase("delete");
    public static final boolean READ = Menu.getChoosingAction().equalsIgnoreCase("read");
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        LibraryService library = new LibraryService();

        boolean isLibraryOpen = true;

        OpenLibrary(library, isLibraryOpen);
    }

    private static void OpenLibrary(LibraryService library, boolean isLibraryOpen) throws IOException {
        do {
            login();
            if (isAccountNotExist(library, Login.getYourName(), Login.getYourSurname())) {
                addingAccount(library);
            }
            boolean doSomething = true;
            do {
                if (isAccountOfAdmin(library, Login.getYourName(), Login.getYourSurname(), Role.ADMIN)) {
                    choosingAction();
                    if (ADD) {
                        addingNewBook(library);
                    } else if (DELETE) {
                        deletingOfBook(library);
                    } else if (READ) {
                        readingOfBook(library);
                    }

                } else if (isAccountOfAdmin(library, Login.getYourName(), Login.getYourSurname(), Role.USER)) {
                    readingOfBook(library);
                }
                doSomething = isDoSomething(doSomething);
            }
            while (doSomething == true);
        }
        while (isLibraryOpen);
    }

    private static boolean isDoSomething(boolean doSomething) {
        System.out.println(" ");
        System.out.println("Do you want to do something else? Write YES or NO");
        Menu.setChoosingAction(scanner.nextLine());
        while (!Menu.getChoosingAction().equalsIgnoreCase("yes") && !Menu.getChoosingAction().equalsIgnoreCase("no")) {
            System.out.println("Write YES or NO");
            Menu.setChoosingAction(scanner.nextLine());
        }
        if (Menu.getChoosingAction().equalsIgnoreCase("yes")) {
            doSomething = true;
        } else if (Menu.getChoosingAction().equalsIgnoreCase("no")) {
            doSomething = false;
            System.out.println("The next one");
        }
        return doSomething;
    }

    private static void readingOfBook(LibraryService library) {
        System.out.println("Which book do you want to read? Enter the name of book");
        NewBook.setNameOfNewBook(scanner.nextLine());
        System.out.println("Enter the author of book");
        NewBook.setAuthorOfNewBook(scanner.nextLine());
        library.openBook(NewBook.getNameOfNewBook(), NewBook.getAuthorOfNewBook());
    }

    private static void deletingOfBook(LibraryService library) throws IOException {
        System.out.println("Enter the name of book");
        NewBook.setNameOfNewBook(scanner.nextLine());
        System.out.println("Enter the author of book");
        NewBook.setAuthorOfNewBook(scanner.nextLine());
        if (library.getBooks().stream().anyMatch(book -> NewBook.getNameOfNewBook().equalsIgnoreCase(book.getName()) && NewBook.getAuthorOfNewBook().equalsIgnoreCase(book.getAuthor()))) {
            int indexOfBook = 0;
            for (int i = 0; i < library.getBooks().size(); i++) {
                if (NewBook.getNameOfNewBook().equalsIgnoreCase(library.getBooks().get(i).getName()) && NewBook.getAuthorOfNewBook().equalsIgnoreCase(library.getBooks().get(i).getAuthor())) {
                    indexOfBook = i;
                }
            }
            library.deleteBook(library.getBooks().get(indexOfBook), getAccountFromLibrary(library, Login.getYourName(), Login.getYourSurname()));
            System.out.println("Book is deleted");
        } else {
            System.out.println("There is no such book");
        }
    }

    private static void addingNewBook(LibraryService library) throws IOException {
        System.out.println("Enter the name of book");
        NewBook.setNameOfNewBook(scanner.nextLine());
        System.out.println("Enter the author of book");
        NewBook.setAuthorOfNewBook(scanner.nextLine());
        System.out.println("Enter the content of book");
        NewBook.setContentOfNewBook(scanner.nextLine());
        System.out.println("Is it electronic or not? Write please YES or NO");
        NewBook.setIsElectronic(scanner.nextLine());
        while (!NewBook.getIsElectronic().equalsIgnoreCase("yes") && !NewBook.getIsElectronic().equalsIgnoreCase("no")) {
            System.out.println("Write YES or NO");
            NewBook.setIsElectronic(scanner.nextLine());
        }
        boolean isElectronic;
        if (NewBook.getIsElectronic().equalsIgnoreCase("yes")) {
            isElectronic = true;
        } else {
            isElectronic = false;
        }
        library.addBook(new Book(NewBook.getNameOfNewBook(), NewBook.getAuthorOfNewBook(), NewBook.getContentOfNewBook(), isElectronic), getAccountFromLibrary(library, Login.getYourName(), Login.getYourSurname()));
    }

    private static void choosingAction() {
        System.out.println("Do you want to add book, delete or read? Write please ADD, DELETE, or READ");
        Menu.setChoosingAction(scanner.nextLine());
        while (!Menu.getChoosingAction().equalsIgnoreCase("add") && !Menu.getChoosingAction().equalsIgnoreCase("delete") && !Menu.getChoosingAction().equalsIgnoreCase("read")) {
            System.out.println("Write correct action");
            Menu.setChoosingAction(scanner.nextLine());
        }
    }

    private static void addingAccount(LibraryService library) {
        System.out.println("Are you admin or not? If you admin, write ADMIN, if not - write USER");
        Login.setYourRole(scanner.nextLine());
        while (!Login.getYourRole().equalsIgnoreCase("admin") && !Login.getYourRole().equalsIgnoreCase("user")) {
            System.out.println("Enter please ADMIN or USER");
            Login.setYourRole(scanner.nextLine());
        }
        addingNewAccount(library, Login.getYourName(), Login.getYourSurname(), Login.getYourRole());
    }

    private static void login() {
        System.out.println("Enter the name");
        Login.setYourName(scanner.nextLine());
        System.out.println("Enter the surname");
        Login.setYourSurname(scanner.nextLine());
    }

    private static Account getAccountFromLibrary(LibraryService library, String name, String surname) {
        return library.getAccounts().stream().filter(account -> name.equals(account.getName()) && surname.equals(account.getSurname())).findFirst().stream().toList().get(0);
    }

    private static boolean isAccountOfAdmin(LibraryService library, String name, String surname, Role admin) {
        return library.getAccounts().stream().anyMatch(account -> account.getName().equals(name) && account.getSurname().equals(surname) && account.getRole() == admin);
    }

    private static boolean isAccountNotExist(LibraryService library, String name, String surname) {
        return library.getAccounts().stream().noneMatch(account -> name.equals(account.getName()) && surname.equals(account.getSurname()));
    }

    private static void addingNewAccount(LibraryService library, String name, String surname, String role) {
        if (role.equalsIgnoreCase("admin")) {
            library.addAccount(name, surname, Role.ADMIN);
            System.out.println(String.format(NEW_ACCOUNT_ADDED, "admin"));
        } else {
            library.addAccount(name, surname, Role.USER);
            System.out.println(String.format(NEW_ACCOUNT_ADDED, "user"));
        }
    }
}
