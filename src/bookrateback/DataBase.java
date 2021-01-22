package bookrateback;

import bookrateback.Admin;

import java.sql.*;
import java.util.UUID;

public class DataBase {

    private static Connection conn;

    public DataBase() {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookratedb", "piotr", "piotr");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public double getAvg(String title) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String strSelect = String.format("SELECT r0, r1, r2, r3, r4, r5, r6, r7, r8, r9, r10 FROM books WHERE title = \"%s\"", title);
            ResultSet resultSet = stmt.executeQuery(strSelect);
            double avg = -1;
            if (resultSet.next()) {
                double sum = 0;
                int count = 0;
                for (int i = 0; i <= 10; i++) {
                    String label = "r" + i;
                    sum += resultSet.getInt(label) * i;
                    count += resultSet.getInt(label);
                }
                if (count == 0) avg = 0;
                else avg = sum/count;
            }
            return avg;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return -1;
        }
    }

    public void addRate(String title, int rate, UUID user) {
        try (Statement stmt = conn.createStatement()) {
            String label = "r" + rate;
            String strUpdate = String.format("UPDATE books set " + label + " = " + label + " + 1 WHERE title = \"%s\"", title);
            stmt.executeUpdate(strUpdate);
            String strUpdate2 = String.format("UPDATE books set rated = CONCAT(rated, \"%1$s\") WHERE title = \"%2$s\"", user.toString() + "|", title);
            System.out.println(strUpdate2);
            stmt.execute(strUpdate2);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addRate(long isbn, int rate) {
        try (Statement stmt = conn.createStatement()) {
            String label = "r" + rate;
            String strUpdate = String.format("UPDATE books set %1$s = %1$s + 1 WHERE ISBN = %2$d", label, isbn);
            stmt.executeUpdate(strUpdate);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void addBook(Book book) {
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery(String.format("SELECT COUNT(*) AS total FROM books WHERE title = \"%s\"", book.getTitle()));
            if (resultSet.next() && resultSet.getInt("total") == 0) {
                try (Statement stmt2 = conn.createStatement()) {
                    int[] ratings = book.getRatings();
                    String values = " " + ratings[0];
                    for (int i = 1; i <= 10; i++) {
                        values = values + ", " + ratings[i];
                    }
                    String sqlInsert = String.format("INSERT INTO books VALUES ( %1$d, \"%2$s\", \"%3$s\", \"%4$s\" , \"%5$s\" ,", book.getIsbn(),
                            book.getTitle(), book.getAuthor(), book.getGenre(), book.getShortInfo()) + values + ",\"\");";
                    stmt2.executeUpdate(sqlInsert);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void nullToZero() {
        for (int i = 0; i <= 10; i++) {
            try (Statement stmt = conn.createStatement()) {
                String label = "r" + i;
                String strUpdate = String.format("UPDATE books set " + label + " = 0 WHERE " + label + " IS NULL");
                stmt.executeUpdate(strUpdate);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }

    public Book getBook(String searchedTitle) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String strSelect = String.format("SELECT * FROM books WHERE title = \"%s\"", searchedTitle);
            ResultSet resultSet = stmt.executeQuery(strSelect);
            if (resultSet.next()){
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                String genre = resultSet.getString("genre");
                long isbn = resultSet.getLong("ISBN");
                String shortInfo = resultSet.getString("shortInfo");
                int[] ratings = new int[11];
                for (int i = 0; i <= 10; i++) {
                    ratings[i] = resultSet.getInt(String.format("r%d", i));
                }
                String raters = resultSet.getString("rated");
                if(raters == null) raters = "";
                return new Book(isbn, title, author, genre, ratings, shortInfo, raters);
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean addUser(User user){
        try (Statement stmt = conn.createStatement()) {
            if (!isNickTaken(user.getNick())) {
                int isAdmin = 0;
                if (user instanceof Admin) isAdmin = 1;
                String sqlInsert = String.format("INSERT INTO users VALUES ( \"%1$s\", \"%2$s\", \"%3$s\", %4$s);", user.getId().toString(),
                        user.getNick(), user.getPassword(), isAdmin);
                stmt.executeUpdate(sqlInsert);
                return true;
            }
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public  boolean userLogin(String nick, String password){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String strSelect = String.format("SELECT * FROM users WHERE nick = \"%s\";", nick);
            ResultSet resultSet = stmt.executeQuery(strSelect);
            if(resultSet.next()){
                return (password.equals(resultSet.getString("password")));
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public User getUser(String nick){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String strSelect = String.format("SELECT * FROM users WHERE nick = \"%s\";", nick);
            ResultSet resultSet = stmt.executeQuery(strSelect);
            if(resultSet.next()){
                if(resultSet.getBoolean("isAdmin"))
                    return new Admin(resultSet.getString("nick"), resultSet.getString("password"),
                            UUID.fromString(resultSet.getString("UUID")));
                return new User(resultSet.getString("nick"), resultSet.getString("password"),
                        UUID.fromString(resultSet.getString("UUID")));
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public boolean isNickTaken(String nick){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            String strSelect = String.format("SELECT * FROM users WHERE nick = \"%s\";", nick);
            ResultSet resultSet = stmt.executeQuery(strSelect);
            if(resultSet.next()){
                return true;
            }
            return false;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public void addMessage(Message message){
        try (Statement stmt = conn.createStatement()) {
            String type = "";
            if(message instanceof News) type = "news";
            if(message instanceof Review) type = "review";
            String sqlInsert = String.format("INSERT INTO messages VALUES ( \"%1$s\", \"%2$s\", \"%3$s\", \"%4$s\", \"%5$s\");", message.getMsgUUID().toString(),
                    message.getAuthor(), message.getTitle(), message.getText(), type);
            stmt.executeUpdate(sqlInsert);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public News[] getLastNews(){
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet resultSet1 = stmt.executeQuery("SELECT COUNT(*) AS total FROM messages WHERE type = \"news\";");
            int n = 0;
            if (resultSet1.next()) n = resultSet1.getInt("total") - 5;
            News[] news = new News[5];
            ResultSet resultSet2 = stmt.executeQuery("SELECT * FROM messages LIMIT 5 OFFSET " + n + ";");
            for (int i = 0; i < 5; i++) {
                if (resultSet2.next()){
                    news[i] = new News(resultSet2.getString("text"), getUser(resultSet2.getString("authorNick")), resultSet2.getString("title"), resultSet2.getString("UUIDMsg"));
                }
            }
            return news;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new News[5];
        }
    }

    public Book[] getRandomBooks() {
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM books ORDER BY RAND() LIMIT 4");
            Book[] books = new Book[4];
            for (int i = 0; i < 4; i++) {
                if (rs.next()) {
                    books[i] = new Book(rs.getLong("isbn"), rs.getString("title"), rs.getString("author"), rs.getString("genre"), rs.getString("shortInfo"));
                }
            } return books;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return new Book[4];
        }
    }

    public void deleteUser(String nick){
        Statement statement = null;
        try{
            statement = conn.createStatement();
            System.out.println("DELETE FROM users WHERE nick = " + nick);
            statement.executeUpdate(String.format("DELETE FROM users WHERE nick = '%s';", nick));
        } catch (SQLException throwables) {}
    }

    public void makeAdmin(String nick){
        Statement statement = null;
        try{
            statement = conn.createStatement();
            System.out.println("UPDATE users SET isAdmin = 1 WHERE nick = " + nick);
            statement.executeUpdate(String.format("UPDATE users SET isAdmin = 1 WHERE nick = '%s';", nick));
        } catch (SQLException throwables) {}
    }

}