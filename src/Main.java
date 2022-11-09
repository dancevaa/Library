import dto.Account;
import dto.Book;
import dto.Role;
import service.LibraryService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        LibraryService library = new LibraryService();
        boolean isLibraryOpen = true;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the name");
            String name = scanner.nextLine();
            System.out.println("Enter the surname");
            String surname = scanner.nextLine();
            if (library.getAccounts().stream().anyMatch(account -> name.equals(account.getName()) && surname.equals(account.getSurname())) == false) {
                System.out.println("Are you admin or not? If you admin, write ADMIN, if not - write USER");
                String role = scanner.nextLine();
                while (role.equalsIgnoreCase("admin") == false && role.equalsIgnoreCase("user") == false) {
                    System.out.println("Enter please ADMIN or USER");
                    role = scanner.nextLine();
                }
                if (role.equalsIgnoreCase("admin")) {
                    library.addAccount(name, surname, Role.ADMIN);
                    System.out.println("new account of admin is added");
                } else {
                    library.addAccount(name, surname, Role.USER);
                    System.out.println("new account of user is added");
                }
            }
            boolean doSomething = true;
            do {
                if (library.getAccounts().stream().anyMatch(account -> account.getName().equals(name) && account.getSurname().equals(surname) && account.getRole() == Role.ADMIN)) {
                    System.out.println("Do you want to add book, delete or read? Write please ADD, DELETE, or READ");
                    String answer = scanner.nextLine();
                    while (answer.equalsIgnoreCase("add") == false && answer.equalsIgnoreCase("delete") == false && answer.equalsIgnoreCase("read") == false) {
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
                        while (isBookElectronic.equalsIgnoreCase("yes") == false && isBookElectronic.equalsIgnoreCase("no") == false) {
                            System.out.println("Write YES or NO");
                            isBookElectronic = scanner.nextLine();
                        }
                        boolean isElectronic;
                        if (isBookElectronic.equalsIgnoreCase("yes")) {
                            isElectronic = true;
                        } else {
                            isElectronic = false;
                        }
                        library.addBook(new Book(nameOfBook, authorOfBook, contentOfBook, isElectronic), library.getAccounts().stream().filter(account -> name.equals(account.getName()) && surname.equals(account.getSurname())).findFirst().stream().toList().get(0));
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
                            library.deleteBook(library.getBooks().get(indexOfBook), library.getAccounts().stream().filter(account -> name.equals(account.getName()) && surname.equals(account.getSurname())).findFirst().stream().toList().get(0));
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

                } else if (library.getAccounts().stream().anyMatch(account -> account.getName().equals(name) && account.getSurname().equals(surname) && account.getRole() == Role.USER)) {
                    System.out.println("Which book do you want to read? Enter the name of book");
                    String nameOfBook = scanner.nextLine();
                    System.out.println("Enter the author of book");
                    String authorOfBook = scanner.nextLine();
                    library.openBook(nameOfBook, authorOfBook);
                }
                System.out.println(" ");
                System.out.println("Do you want to do something else? Write YES or NO");
                String somethingElse = scanner.nextLine();
                while (somethingElse.equalsIgnoreCase("yes") == false && somethingElse.equalsIgnoreCase("no") == false) {
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
}
