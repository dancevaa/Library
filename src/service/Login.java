package service;

import dto.Account;
import dto.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Login implements LoginService{
    private static final String NEW_ACCOUNT_ADDED_TEMPATE = "new account of %s is added";
    private static List<Account> accountList;
    private final Scanner scanner;
    private String password;

    public Login() {
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

    public static List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

}
