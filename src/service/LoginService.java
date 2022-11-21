package service;

import dto.Account;
import dto.Role;

import java.util.Scanner;

public interface LoginService {
    Scanner scanner = new Scanner(System.in);
    String NEW_ACCOUNT_ADDED_TEMPATE = "new account of %s is added";

    void logIn(String name);

    default Account entering() {
        System.out.println("Enter the name");
        String name = scanner.nextLine();
        return logInOrLogUp(name);
    }
    default Account logUp(String name) {
        System.out.println("Enter the password");
        String password = scanner.nextLine();
        System.out.println("Are you admin or not? If you admin, write ADMIN, if not - write USER");
        String role = scanner.nextLine();
        while (role.equalsIgnoreCase(Role.ADMIN.getRoleName()) && !role.equalsIgnoreCase(Role.USER.getRoleName())) {
            System.out.println("Enter please ADMIN or USER");
            role = scanner.nextLine();
        }
        Login.getAccountList().add(new Account(name, password, Role.findRole(role.toUpperCase())));
        System.out.println(String.format(NEW_ACCOUNT_ADDED_TEMPATE, role));
        return new Account(name, password, Role.findRole(role.toUpperCase()));
    }

    default Account logInOrLogUp(String name) {
        if (Login.getAccountList().stream().anyMatch(account -> name.equals(account.getName()))) {
            logIn(name);
            return Login.getAccountList().stream()
                    .filter(account -> name.equals(account.getName()))
                    .findFirst().get();
        } else {
            return logUp(name);
        }

    }
}
