public class Role {
    boolean isAdmin;
    boolean isUser;

    Role(String s) {
        if (s.equals("Администратор")) {
            isAdmin = true;
        } else isUser = true;
    }
}
