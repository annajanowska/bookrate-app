package bookrateback;

import java.util.UUID;

public class Admin  extends User {

    public Admin(String nick, String password) {
        super(nick, password);
    }

    public Admin(String nick, String password, UUID id) {
        super(nick, password, id);
    }

    public void AddNews (String News) { System.out.println(News); }

    public void AddQuiz (String Quiz) { System.out.println(Quiz); }

}
