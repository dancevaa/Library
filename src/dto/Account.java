package dto;

public class Account {
    private final String name;
    private final String surname;
    private Role role;

    public Account(String name, String surname, Role role) {
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role=" + role +
                '}';
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Role getRole() {
        return role;
    }

    public Role changeRole(Role role){
        this.role = role;
        return this.role;
    }
}
