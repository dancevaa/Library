package service;

public class NewBook {
    private static String nameOfNewBook;
    private static String authorOfNewBook;
    private static String contentOfNewBook;
    private static String isElectronic;

    public static String getNameOfNewBook() {
        return nameOfNewBook;
    }

    public static void setNameOfNewBook(String nameOfNewBook) {
        NewBook.nameOfNewBook = nameOfNewBook;
    }

    public static String getAuthorOfNewBook() {
        return authorOfNewBook;
    }

    public static void setAuthorOfNewBook(String authorOfNewBook) {
        NewBook.authorOfNewBook = authorOfNewBook;
    }

    public static String getContentOfNewBook() {
        return contentOfNewBook;
    }

    public static void setContentOfNewBook(String contentOfNewBook) {
        NewBook.contentOfNewBook = contentOfNewBook;
    }

    public static String getIsElectronic() {
        return isElectronic;
    }

    public static void setIsElectronic(String isElectronic) {
        NewBook.isElectronic = isElectronic;
    }


}
