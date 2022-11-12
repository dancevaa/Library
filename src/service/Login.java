package service;

import dto.Account;
import dto.Book;
import dto.Role;

import java.io.IOException;


public class Login implements getNameAndSurname{
    private static final String NEW_ACCOUNT_ADDED = "new account of %s is added";

    private String yourSurname;
    private String yourName;

    public String getYourRole() {
        return this.yourRole;
    }

    public void setYourRole(String yourRole) {
        this.yourRole = yourRole;
    }

    private String yourRole;
    public void setYourSurname(String yourSurname) {
        this.yourSurname = yourSurname;
    }
    public void setYourName(String yourName) {
        this.yourName = yourName;
    }
    public String getYourName() {
        return this.yourName;
    }
    public String getYourSurname() {
        return this.yourSurname;
    }
    public void login(LibraryService library) {
        System.out.println("Enter the name");
        this.setYourName(library.scanner.nextLine());
        System.out.println("Enter the surname");
        this.setYourSurname(library.scanner.nextLine());
    }
    public void addingNewAccount(LibraryService library, String name, String surname, String role) {
        if (role.equalsIgnoreCase("admin")) {
            library.addAccount(name, surname, Role.ADMIN);
            System.out.println(String.format(NEW_ACCOUNT_ADDED, "admin"));
        } else {
            library.addAccount(name, surname, Role.USER);
            System.out.println(String.format(NEW_ACCOUNT_ADDED, "user"));
        }
    }

    public void addingAccount(LibraryService library) {
        System.out.println("Are you admin or not? If you admin, write ADMIN, if not - write USER");
        this.setYourRole(library.scanner.nextLine());
        while (!this.getYourRole().equalsIgnoreCase("admin") && !this.getYourRole().equalsIgnoreCase("user")) {
            System.out.println("Enter please ADMIN or USER");
            this.setYourRole(library.scanner.nextLine());
        }
        addingNewAccount(library, this.getYourName(), this.getYourSurname(), this.getYourRole());
    }



    public boolean isAccountOfAdmin(LibraryService library, String name, String surname, Role admin) {
        return library.getAccounts().stream().anyMatch(account -> account.getName().equals(name) && account.getSurname().equals(surname) && account.getRole() == admin);
    }

    public boolean isAccountNotExist(LibraryService library, String name, String surname) {
        return library.getAccounts().stream().noneMatch(account -> name.equals(account.getName()) && surname.equals(account.getSurname()));
    }



}
