package service;

public class Login {
    private static String yourSurname;
    private static String yourName;

    public static String getYourRole() {
        return yourRole;
    }

    public static void setYourRole(String yourRole) {
        Login.yourRole = yourRole;
    }

    private static String yourRole;
    public static void setYourSurname(String yourSurname) {
        Login.yourSurname = yourSurname;
    }
    public static void setYourName(String yourName) {
        Login.yourName = yourName;
    }
    public static String getYourName() {
        return yourName;
    }
    public static String getYourSurname() {
        return yourSurname;
    }

}
