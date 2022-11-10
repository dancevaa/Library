import dto.Account;
import dto.Book;
import dto.Role;
import service.LibraryService;
import service.Login;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
        LibraryService library = new LibraryService();

        boolean isLibraryOpen = true;

        do {
            login();
            if (isAccountNotExist(library, Login.getYourName(), Login.getYourSurname())) {
                addingAccount(library);
            }
            boolean doSomething = true;
            do {
                if (isAccountOfAdmin(library, Login.getYourName(), Login.getYourSurname(), Role.ADMIN)) {
                    System.out.println("Do you want to add book, delete or read? Write please ADD, DELETE, or READ");
                    String answer = scanner.nextLine();
                    while (!answer.equalsIgnoreCase("add") && !answer.equalsIgnoreCase("delete") && !answer.equalsIgnoreCase("read")) {
                        System.out.println("Write correct action");
                        answer = scanner.nextLine();
                    }
                    if (answer.equalsIgnoreCase("add")) {
                        System.out.println("Enter the name of book");
                        String nameOfBook = scanner.nextLine();
                        System.out.println("Enter the author of book");
                        String authorOfBook = scanner.nextLine();
                        System.out.println("Enter the content of book");
                        String contentOfBook = scanner.nextLine();
                        System.out.println("Is it electronic or not? Write please YES or NO");
                        String isBookElectronic = scanner.nextLine();
                        while (!isBookElectronic.equalsIgnoreCase("yes") && !isBookElectronic.equalsIgnoreCase("no")) {
                            System.out.println("Write YES or NO");
                            isBookElectronic = scanner.nextLine();
                        }
                        boolean isElectronic;
                        if (isBookElectronic.equalsIgnoreCase("yes")) {
                            isElectronic = true;
                        } else {
                            isElectronic = false;
                        }
                        library.addBook(new Book(nameOfBook, authorOfBook, contentOfBook, isElectronic), getAccountFromLibrary(library, Login.getYourName(), Login.getYourSurname()));
                    } else if (answer.equalsIgnoreCase("delete")) {
                        System.out.println("Enter the name of book");
                        String nameOfBook = scanner.nextLine();
                        System.out.println("Enter the author of book");
                        String authorOfBook = scanner.nextLine();
                        if (library.getBooks().stream().anyMatch(book -> nameOfBook.equalsIgnoreCase(book.getName()) && authorOfBook.equalsIgnoreCase(book.getAuthor()))) {
                            int indexOfBook = 0;
                            for (int i = 0; i < library.getBooks().size(); i++) {
                                if (nameOfBook.equalsIgnoreCase(library.getBooks().get(i).getName()) && authorOfBook.equalsIgnoreCase(library.getBooks().get(i).getAuthor())) {
                                    indexOfBook = i;
                                }
                            }
                            library.deleteBook(library.getBooks().get(indexOfBook), getAccountFromLibrary(library, Login.getYourName(), Login.getYourSurname()));
                            System.out.println("Book is deleted");
                        } else {
                            System.out.println("There is no such book");
                        }
                    } else if (answer.equalsIgnoreCase("read")) {
                        System.out.println("Enter the name of book");
                        String nameOfBook = scanner.nextLine();
                        System.out.println("Enter the author of book");
                        String authorOfBook = scanner.nextLine();
                        library.openBook(nameOfBook, authorOfBook);
                    }

                } else if (isAccountOfAdmin(library, Login.getYourName(), Login.getYourSurname(), Role.USER)) {
                    System.out.println("Which book do you want to read? Enter the name of book");
                    String nameOfBook = scanner.nextLine();
                    System.out.println("Enter the author of book");
                    String authorOfBook = scanner.nextLine();
                    library.openBook(nameOfBook, authorOfBook);
                }
                System.out.println(" ");
                System.out.println("Do you want to do something else? Write YES or NO");
                String somethingElse = scanner.nextLine();
                while (!somethingElse.equalsIgnoreCase("yes") && !somethingElse.equalsIgnoreCase("no")) {
                    System.out.println("Write YES or NO");
                    somethingElse = scanner.nextLine();
                }
                if (somethingElse.equalsIgnoreCase("yes")) {
                    doSomething = true;
                } else if (somethingElse.equalsIgnoreCase("no")) {
                    doSomething = false;
                    System.out.println("The next one");
                }
            }
            while (doSomething == true);
        }
        while (isLibraryOpen);
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
            System.out.println("new account of admin is added");
        } else {
            library.addAccount(name, surname, Role.USER);
            System.out.println("new account of user is added");
        }
    }
}
