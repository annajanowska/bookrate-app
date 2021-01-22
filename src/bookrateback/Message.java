package bookrateback;

import java.util.UUID;

public abstract class Message {
    private String text;
    private User author;
    private String title;
    private UUID msgUUID;

    public Message(String text, User author, String title, String strUUID) {
        this.text = text;
        this.author = author;
        this.title = title;
        this.msgUUID = UUID.fromString(strUUID);
    }

    public Message(String text, User author, String title) {
        this.text = text;
        this.author = author;
        this.title = title;
        msgUUID = UUID.randomUUID();
    }

    public String getText() {
        return text;
    }

    public User getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public UUID getMsgUUID() {
        return msgUUID;
    }
}
