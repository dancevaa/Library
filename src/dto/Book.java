package dto;

public class Book {
    private static final String YES = "yes";

    private String name;
    private String author;
    private String content;
    private boolean isElectronic;

    public Book (String name, String author, String content, boolean isElectronic){
        this.name = name;
        this.author = author;
        this.content = content;
        this.isElectronic = isElectronic;
    }

    public Book (String name, String author, String content, String isElectronic){
        this.name = name;
        this.author = author;
        this.content = content;
        this.isElectronic = YES.equalsIgnoreCase(isElectronic);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", isElectronic=" + isElectronic +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isElectronic() {
        return isElectronic;
    }

    public void setElectronic(boolean electronic) {
        isElectronic = electronic;
    }
}
