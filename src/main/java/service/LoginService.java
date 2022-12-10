package service;

import dto.Account;

public interface LoginService {
    Account logIn(String name);

    Account logUp(String name);

    Account logInOrLogUp(String name);
}
