package service;

import dto.Account;

public interface LoginService {
    void logIn(String name);
    Account entering();
    Account logUp(String name);
    Account logInOrLogUp(String name);
}
