package service;

import dto.Account;
import dto.Role;


public class Login {
    public static final String NEW_ACCOUNT_ADDED = "new account of %s is added";

    private static String yourSurname;
    private static String yourName;

    public static String getYourRole() {
        return yourRole;
    }

    public static void setYourRole(String yourRole) {
        Login.yourRole = yourRole;
    }

    private static String yourRole;
    public static void setYourSurname(String yourSurname) {
        Login.yourSurname = yourSurname;
    }
    public static void setYourName(String yourName) {
        Login.yourName = yourName;
    }
    public static String getYourName() {
        return yourName;
    }
    public static String getYourSurname() {
        return yourSurname;
    }
    public static void login() {
        System.out.println("Enter the name");
        Login.setYourName(LibraryService.scanner.nextLine());
        System.out.println("Enter the surname");
        Login.setYourSurname(LibraryService.scanner.nextLine());
    }
    public static void addingNewAccount(LibraryService library, String name, String surname, String role) {
        if (role.equalsIgnoreCase("admin")) {
            library.addAccount(name, surname, Role.ADMIN);
            System.out.println(String.format(NEW_ACCOUNT_ADDED, "admin"));
        } else {
            library.addAccount(name, surname, Role.USER);
            System.out.println(String.format(NEW_ACCOUNT_ADDED, "user"));
        }
    }

    public static void addingAccount(LibraryService library) {
        System.out.println("Are you admin or not? If you admin, write ADMIN, if not - write USER");
        Login.setYourRole(LibraryService.scanner.nextLine());
        while (!Login.getYourRole().equalsIgnoreCase("admin") && !Login.getYourRole().equalsIgnoreCase("user")) {
            System.out.println("Enter please ADMIN or USER");
            Login.setYourRole(LibraryService.scanner.nextLine());
        }
        addingNewAccount(library, Login.getYourName(), Login.getYourSurname(), Login.getYourRole());
    }

    public static Account getAccountFromLibrary(LibraryService library, String name, String surname) {
        return library.getAccounts().stream().filter(account -> name.equals(account.getName()) && surname.equals(account.getSurname())).findFirst().stream().toList().get(0);
    }

    public static boolean isAccountOfAdmin(LibraryService library, String name, String surname, Role admin) {
        return library.getAccounts().stream().anyMatch(account -> account.getName().equals(name) && account.getSurname().equals(surname) && account.getRole() == admin);
    }

    public static boolean isAccountNotExist(LibraryService library, String name, String surname) {
        return library.getAccounts().stream().noneMatch(account -> name.equals(account.getName()) && surname.equals(account.getSurname()));
    }

}
