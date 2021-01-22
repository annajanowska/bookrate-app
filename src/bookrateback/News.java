package bookrateback;

public class News extends Message{

    public News(String text, User author, String title, String strUUID) {
        super(text, author, title, strUUID);
    }

    public News(String text, User author, String title) {
        super(text, author, title);
    }
}
