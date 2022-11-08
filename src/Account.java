interface CanAddBooks{
    void addBook(String name, String author,String content, boolean isElectronic);
}
interface CanDeleteBooks{
    void deleteBook(String name, String author);
}

interface CanOpenBook{
    void openBook(String name, String author);
}
public class Account implements CanAddBooks, CanDeleteBooks, CanOpenBook{
    String userName;
    String userSurname;
    Role role;
    Account(String userName, String userSurname, String role){
        this.userName = userName;
        this.userSurname = userSurname;
        this.role = new Role(role);
    }

    @Override
    public void addBook(String name, String author,String content, boolean isElectronic) {
        if (role.isAdmin){
        Main.books.add(new Book(name, author, content, isElectronic));
            System.out.println("Книга добавлена");
        }
        else System.out.println("Недостаточно прав");
    }

    @Override
    public void deleteBook(String name, String author) {
        if (role.isAdmin) {
            for (Book book : Main.books) {
                if (book.name.equals(name) && book.author.equals(author)) {
                    Main.books.remove(book);
                    System.out.println("Книга удалена");
                }
            }
        }
        else System.out.println("Недостаточно прав");
    }

    @Override
    public void openBook(String name, String author) {
        if (Main.books.stream().anyMatch(book -> book.name.equals(name) && book.author.equals(author))){
            for (Book book: Main.books){
                if(book.name.equals(name) && book.author.equals(author)) {
                    System.out.println(book.content);
                }
            }
        }
        else System.out.println("Книга не найдена");
    }
}
