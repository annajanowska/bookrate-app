package bookrateback;

import java.util.HashMap;
import java.util.UUID;

public class Book {
    public Book( long isbn, String title, String author, String genre, String shortInfo) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.shortInfo = shortInfo;
        this.ratings = new int[11];
        this.isbn = isbn;
    }

    public Book(long isbn, String title, String author, String genre, int[] ratings, String shortInfo, String UUIDs) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.ratings = ratings;
        this.shortInfo = shortInfo;
        raters = UUIDs;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int[] getRatings() {
        return this.ratings;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public long getIsbn() {
        return isbn;
    }

    public void rateBook(int rating){
        ratings[rating]++;
    }

    public String getRaters() {
        return raters;
    }

    private long isbn;
    private String title;
    private String author;
    private String genre;
    private int[] ratings;
    private String shortInfo;
    private String raters;
}
