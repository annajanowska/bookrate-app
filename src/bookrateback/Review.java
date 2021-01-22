package bookrateback;

public class Review extends Message {
    private Book book;
    public Review(String text, User author, String title, String strUUID, Book book) {
        super(text, author, title, strUUID);
        this.book = book;
    }
    public Review(String text, User author, String title, Book book) {
        super(text, author, title);
        this.book = book;
    }
}
