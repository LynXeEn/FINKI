package mk.ukim.finki.wp.labofficial.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String isbn;
    private String title;
    private String genre;
    private int year;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Author> authors;
    @ManyToOne
    private BookStore bookStore;

    public Book(String isbn, String title, String genre, int year, BookStore bookStore) {
        this.isbn = isbn;
        this.title = title;
        this.genre = genre;
        this.year = year;
        authors = new ArrayList<>();
        this.bookStore = bookStore;
    }

    public Book() {

    }
}
