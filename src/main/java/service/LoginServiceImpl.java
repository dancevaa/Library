package service;

import dto.Account;
import dto.Role;
import lombok.extern.log4j.Log4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Log4j
public class LoginServiceImpl implements LoginService{
    private final String NEW_ACCOUNT_ADDED_TEMPATE = "new account of %s is added";
    private static List<Account> accountList;
    private final Scanner scanner;
    private String password;

    public LoginServiceImpl() {
        this.scanner = new Scanner(System.in);
        this.accountList = new ArrayList<>();
    }
    @Override
    public void logIn(String name) {
        log.info("Enter the password");
        this.password = scanner.nextLine();
        while (!accountList.stream().anyMatch(account -> name.equals(account.getName()) && this.password.equals(account.getPassword()))){
            log.info("Invalid password! Try again");
            this.password = scanner.nextLine();
        }
    }
    @Override
    public Account entering() {
        log.info("Enter the name");
        String name = scanner.nextLine();
        return logInOrLogUp(name);
    }
    @Override
    public Account logUp(String name) {
        log.info("Enter the password");
        String password = scanner.nextLine();
        log.info("Are you admin or not? If you admin, write ADMIN, if not - write USER");
        String role = scanner.nextLine();
        while (!role.equalsIgnoreCase(Role.ADMIN.getRoleName()) && !role.equalsIgnoreCase(Role.USER.getRoleName())) {
            log.info("Enter please ADMIN or USER");
            role = scanner.nextLine();
        }
        accountList.add(new Account(name, password, Role.findRole(role.toUpperCase())));
        log.info(String.format(NEW_ACCOUNT_ADDED_TEMPATE, role));
        return new Account(name, password, Role.findRole(role.toUpperCase()));
    }

    @Override
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
