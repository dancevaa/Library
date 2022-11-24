package service;

import dto.Account;
import dto.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LoginServiceImpl implements LoginService{
    private final String NEW_ACCOUNT_ADDED_TEMPATE = "new account of %s is added";
    private static List<Account> accountList;
    private final Scanner scanner;
    private String password;

    public LoginServiceImpl() {
        this.scanner = new Scanner(System.in);
        this.accountList = new ArrayList<>();
    }
    public void logIn(String name) {
        System.out.println("Enter the password");
        this.password = scanner.nextLine();
        while (!accountList.stream().anyMatch(account -> name.equals(account.getName()) && this.password.equals(account.getPassword()))){
            System.out.println("Invalid password! Try again");
            this.password = scanner.nextLine();
        }
    }
    public Account entering() {
        System.out.println("Enter the name");
        String name = scanner.nextLine();
        return logInOrLogUp(name);
    }
    public Account logUp(String name) {
        System.out.println("Enter the password");
        String password = scanner.nextLine();
        System.out.println("Are you admin or not? If you admin, write ADMIN, if not - write USER");
        String role = scanner.nextLine();
        while (!role.equalsIgnoreCase(Role.ADMIN.getRoleName()) && !role.equalsIgnoreCase(Role.USER.getRoleName())) {
            System.out.println("Enter please ADMIN or USER");
            role = scanner.nextLine();
        }
        accountList.add(new Account(name, password, Role.findRole(role.toUpperCase())));
        System.out.println(String.format(NEW_ACCOUNT_ADDED_TEMPATE, role));
        return new Account(name, password, Role.findRole(role.toUpperCase()));
    }

    public Account logInOrLogUp(String name) {
        if (accountList.stream().anyMatch(account -> name.equals(account.getName()))) {
            logIn(name);
            return accountList.stream()
                    .filter(account -> name.equals(account.getName()))
                    .findFirst().get();
        } else {
            return logUp(name);
        }
    }
    public static List<Account> getAccountList() {
        return accountList;
    }

    public static void setAccountList(List<Account> accountList) {
        LoginServiceImpl.accountList = accountList;
    }


}
