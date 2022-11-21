package dto;

import java.io.Serializable;

public class Account implements Serializable {
    private final String name;
    private String password;
    private Role role;

    public Account(String name, String password, Role role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
    public String getName() {
        return name;
    }
    public Role getRole() {
        return role;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role changeRole(Role role){
        this.role = role;
        return this.role;
    }
}
