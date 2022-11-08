import dto.Book;
import service.LibraryService;

import java.util.ArrayList;

public class Main {
    public static ArrayList<Book> books = new ArrayList<>();
    public static void main(String[] args) {
//        Account Ann = new Account("Anna", "Provedenceva", "Администратор");
//        Account Pavel = new Account("Pavel", "Kovalyuk", "Пользователь");
//        Ann.addBook("Harry Potter", "Rowling", "http://loveread.ec/read_book.php?id=2317&p=1", true);
//        Ann.addBook("Billy Milligan", "Keyes", "http://loveread.ec/read_book.php?id=10119&p=1", true);
//        Pavel.deleteBook("Dskd", "dsd");
//        Pavel.openBook("Harry Potter", "Rowling");
//        Pavel.openBook("sdsd", "dsd");

        LibraryService libraryService = new LibraryService();


        AdministratorImpl administrator = new AdministratorImpl();
        administrator.add(new Book("ds", "ds", "ds", true), libraryService.getBooks());
        System.out.println(libraryService.getBooks());
    }
}