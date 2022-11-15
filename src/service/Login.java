package service;

import dto.Account;
import dto.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Login {
    private static final String NEW_ACCOUNT_ADDED_TEMPATE = "new account of %s is added";

    private final List<Account> accountList;

    private final Scanner scanner;

    private String surname;
    private String name;
    private String role;

    public Login() {
        this.scanner = new Scanner(System.in);
        this.accountList = new ArrayList<>();
    }

    public Account login() {
        System.out.println("Enter the name");
        this.setName(scanner.nextLine());
        System.out.println("Enter the surname");
        this.setSurname(scanner.nextLine());

        return getExistedAccountOrAddNewAccount(this.name, this.surname);
    }

    private Account addingAccount() {
        System.out.println("Are you admin or not? If you admin, write ADMIN, if not - write USER");
        this.setRole(scanner.nextLine());
        while (!this.getRole().equalsIgnoreCase(Role.ADMIN.getRoleName()) && !this.getRole().equalsIgnoreCase(Role.USER.getRoleName())) {
            System.out.println("Enter please ADMIN or USER");
            this.setRole(scanner.nextLine());
        }
        return new Account(name, surname, Role.findRole(role));
    }

    public boolean isAccountOfAdmin(LibraryService library, String name, String surname, Role admin) {
        return library.getAccounts().stream()
                .anyMatch(account -> account.getName().equals(name) && account.getSurname().equals(surname) && account.getRole() == admin);
    }

    private Account getExistedAccountOrAddNewAccount(String name, String surname) {
        return accountList.stream()
                .filter(account -> name.equals(account.getName()) && surname.equals(account.getSurname()))
                .findFirst()
                .orElse(addingAccount());
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

}
