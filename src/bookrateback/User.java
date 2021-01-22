package bookrateback;

import java.util.UUID;

public class User {

    public User (String nick, String password) {
        this.nick = nick;
        this.password = password;
        this.id = UUID.randomUUID();
    }

    public User(String nick, String password, UUID id) {
        this.nick = nick;
        this.password = password;
        this.id = id;
    }

    public void addReview (String Review) { System.out.println(Review); }

    public String getNick() { return nick; }

    public String getPassword () { return  password; }

    public UUID getId () { return id; }

    private String nick;
    private String password;
    private UUID id;
}
