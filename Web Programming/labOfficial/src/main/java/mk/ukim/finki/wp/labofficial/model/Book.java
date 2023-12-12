package mk.ukim.finki.wp.labofficial.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Book {
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private int year;
    private List<Author> authors;
    private BookStore bookStore;

    public Book(String isbn, String title, String genre, int year, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        authors = new ArrayList<>();
        this.id = (long) (Math.random() * 1000);
        this.bookStore = bookStore;
    }
}
