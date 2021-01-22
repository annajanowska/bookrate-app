import bookrateback.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void getTitle() {
        assertEquals("Harry Potter", new Book(156, "Harry Potter", "J.K. Rowling", "Fantasy", "bookrateback.Book about Harry Potter - wizard.").getTitle());
        assertEquals("Dune",  new Book( 179, "Dune", "Frank Herbert", "Sci-Fi", "The best scifi book ever written by american author.").getTitle());
    }

    @Test
    void getAuthor() {
        assertEquals("J.K. Rowling", new Book(156, "Harry Potter", "J.K. Rowling", "Fantasy", "bookrateback.Book about Harry Potter - wizard.").getAuthor());
        assertEquals("Frank Herbert",  new Book( 179, "Dune", "Frank Herbert", "Sci-Fi", "The best scifi book ever written by american author.").getAuthor());
    }

    @Test
    void getGenre() {
        assertEquals("Fantasy", new Book(156, "Harry Potter", "J.K. Rowling", "Fantasy", "bookrateback.Book about Harry Potter - wizard.").getGenre());
        assertEquals("Sci-Fi",  new Book( 179, "Dune", "Frank Herbert", "Sci-Fi", "The best scifi book ever written by american author.").getGenre());
    }

    @Test
    void getShortInfo() {
        assertEquals("bookrateback.Book about Harry Potter - wizard.", new Book(156, "Harry Potter", "J.K. Rowling", "Fantasy", "bookrateback.Book about Harry Potter - wizard.").getShortInfo());
        assertEquals("The best scifi book ever written by american author.",  new Book( 179, "Dune", "Frank Herbert", "Sci-Fi", "The best scifi book ever written by american author.").getShortInfo());
    }
}